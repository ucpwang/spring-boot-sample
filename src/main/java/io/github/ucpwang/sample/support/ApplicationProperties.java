package io.github.ucpwang.sample.support;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "sample.props")
public class ApplicationProperties {

    @NestedConfigurationProperty
    private List<Map> admins = new ArrayList<>();
    public List<Map> getAdmins() {
        return this.admins;
    }

    @NestedConfigurationProperty
    private List<String> testmsg = new ArrayList<>();
    public List<String> getTestmsg() {
        return this.testmsg;
    }

}
