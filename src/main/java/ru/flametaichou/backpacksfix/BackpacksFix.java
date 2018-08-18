package ru.flametaichou.backpacksfix;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod (modid = "backpacksfix", name = "Backpacks Fix", version = "0.1", acceptableRemoteVersions = "*")

public class BackpacksFix {

	@EventHandler
	public void initialize(FMLInitializationEvent event)
	{
		PlayerInteractHandler eventHandler = new PlayerInteractHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
		FMLCommonHandler.instance().bus().register(eventHandler);
	}
	
}
