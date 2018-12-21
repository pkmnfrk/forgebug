package com.mike_caron.forgebug.tileentity;

import com.google.common.collect.ImmutableMap;
import com.mike_caron.forgebug.ForgeBug;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.animation.TimeValues;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nullable;

public class GrabberTileEntity
    extends TileEntity
{
    private IAnimationStateMachine asm;
    private final TimeValues.VariableValue anim_cycle = new TimeValues.VariableValue(1f);

    public GrabberTileEntity()
    {
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            loadAsm();
        }
        else
        {
            asm = null;
        }
    }

    public void loadAsm()
    {
        asm = ModelLoaderRegistry.loadASM(new ResourceLocation(ForgeBug.modId, "asms/block/grabber1.json"), ImmutableMap.of("cycle_length", anim_cycle));

        asm.transition("grab");
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY)
            return true;

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY)
        {
            return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        }
        return super.getCapability(capability, facing);
    }

    public void handleAnimationEvent(float time, Iterable<Event> pastEvents)
    {
        for(Event event : pastEvents)
        {
            ForgeBug.logger.info("Got animation event {} at {}", event.event(), time);
        }
    }

    public void reverse()
    {
        if(asm.currentState().equals("grab"))
        {
            asm.transition("insert");
        }
        else
        {
            asm.transition("grab");
        }
    }
}
