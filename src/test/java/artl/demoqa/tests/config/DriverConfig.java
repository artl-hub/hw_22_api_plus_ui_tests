package artl.demoqa.tests.config;


import org.aeonbits.owner.Config;

//@Config.Sources({
//        "classpath:config/${env}.properties"
//})
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:${env}.properties",
        "system:properties"
})

public interface DriverConfig extends Config {
    @Key("browserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("browserVersion")
    @Config.DefaultValue("100.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browserRemoteUrl")
    String browserRemoteUrl();
}