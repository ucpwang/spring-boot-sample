package io.github.ucpwang.sample.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "sample.props")
public class ApplicationProperties {

    private List<Map> admins = new ArrayList<>();
    private List<String> testmsg = new ArrayList<>();

}
