package BatteryBufferDriver;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import li.cil.oc.api.Driver;

@Mod(modid = BatteryBufferDriverMod.MODID, name = BatteryBufferDriverMod.NAME, version = BatteryBufferDriverMod.VERSION, dependencies = "required-after:opencomputers@[1.7.0,)")
public class BatteryBufferDriverMod
{

	public static final String MODID = "batterybufferdriver";
	public static final String NAME = "BatteryBufferDriver";
	public static final String VERSION = "1.2";


	private static Logger logger;

	public static Logger getLogger() { return logger; }

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Driver.add(new DriverBatteryBuffer());
	}
}
