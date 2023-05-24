package com.constants;

import com.utils.ConfigReader;

public class Constants extends ConfigReader{
	
	public static String url=prop.getProperty("url");
	public static String browserType=prop.getProperty("browserType");
	public static long ImplicitWaitTime = Long.parseLong(prop.getProperty("ImplicitWait"));

}
