package hu.webuni.hr.szabi.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.TreeMap;

@Configuration
@ConfigurationProperties(prefix = "hr" )
public class ConfigObject {

    private TreeMap<Double, Integer> limits;
    private Integer normal;

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public TreeMap<Double, Integer> getLimits() {
        return limits;
    }

    public void setLimits(TreeMap<Double, Integer> limits) {
        this.limits = limits;
    }

}
