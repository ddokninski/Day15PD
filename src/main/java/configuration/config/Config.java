package configuration.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import configuration.models.Browser;
import configuration.models.Environment;

public class Config {

    public Environment environment;
    public Browser browser;

    @JsonAnyGetter
    public Environment getEnvironment() {
        return environment;
    }

    @JsonAnyGetter
    public Browser getBrowser() {
        return browser;
    }
}
