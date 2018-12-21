package com.mike_caron.forgebug;

import com.mike_caron.forgebug.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CreativeTab
    extends CreativeTabs
{
    public CreativeTab()
    {
        super(ForgeBug.modId);
    }

    @Override
    @Nonnull
    public ItemStack createIcon()
    {
        return new ItemStack(ModBlocks.grabber1);
    }
}
