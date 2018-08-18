package ru.flametaichou.backpacksfix;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

import java.util.*;

public class PlayerInteractHandler {

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            Block block = event.world.getBlock(event.x,event.y,event.z);
            if (block == Blocks.chest && event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem().getUnlocalizedName().toLowerCase().contains("backpack")) {
                event.entityPlayer.closeScreen();
                event.setCanceled(true);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onInteract(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui != null && event.gui.toString().toLowerCase().contains("brad16840")) {
            MovingObjectPosition objectMouseOver = Minecraft.getMinecraft().objectMouseOver;
            if (objectMouseOver != null) {
                if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                } else if (objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ);
                    if (te != null) {
                        if (te instanceof TileEntityChest) {
                            Minecraft.getMinecraft().displayGuiScreen(null);
                        }
                    }
                }
            }
        }
    }
}
