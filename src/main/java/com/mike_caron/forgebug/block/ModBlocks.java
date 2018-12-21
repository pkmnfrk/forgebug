package com.mike_caron.forgebug.block;

import com.mike_caron.forgebug.ForgeBug;
import com.mike_caron.forgebug.tileentity.GrabberTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
@GameRegistry.ObjectHolder (ForgeBug.modId)
public class ModBlocks
{
    private ModBlocks(){}

    @GameRegistry.ObjectHolder("grabber1")
    public static final GrabberBlock grabber1 = null;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(new GrabberBlock("grabber1"));

        GameRegistry.registerTileEntity(GrabberTileEntity.class, new ResourceLocation(ForgeBug.modId, "grabber"));

    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(new ItemBlock(grabber1).setRegistryName(grabber1.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        grabber1.initModel();

        ClientRegistry.bindTileEntitySpecialRenderer(GrabberTileEntity.class, new AnimationTESR<GrabberTileEntity>()
        {
            @Override
            public void handleEvents(GrabberTileEntity te, float time, Iterable<Event> pastEvents)
            {
                super.handleEvents(te, time, pastEvents);

                te.handleAnimationEvent(time, pastEvents);
            }
        });
    }

}
