package util;

import java.util.Properties;

public class ConfigManager {

    private Properties configFile;

    public ConfigManager()
    {
        configFile = new Properties();
        try
        {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("resources/config.cfg"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public String getConfigValue(String name)
    {
        return this.configFile.getProperty(name);
    }

}
