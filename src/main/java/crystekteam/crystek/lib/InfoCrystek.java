package crystekteam.crystek.lib;

import reborncore.common.IModInfo;

/**
 * Created by McKeever on 09-Nov-16.
 */
public class InfoCrystek implements IModInfo {
	public static final String MOD_NAME = "Crystek";
	public static final String MOD_ID = "crystek";
	public static final String MOD_VERSION = "%version%";
	public static final String MOD_DEPENDENCIES = "required-after:reborncore;after:JEI@[3.13,);";
	public static final String SERVER_PROXY_CLASS = "crystekteam.crystek.proxy.CrystekServer";
	public static final String CLIENT_PROXY_CLASS = "crystekteam.crystek.proxy.CrystekClient";

	@Override
	public String MOD_NAME() {
		return MOD_NAME;
	}

	@Override
	public String MOD_ID() {
		return MOD_ID;
	}

	@Override
	public String MOD_VERSION() {
		return MOD_VERSION;
	}

	@Override
	public String MOD_DEPENDENCIES() {
		return MOD_DEPENDENCIES;
	}
}