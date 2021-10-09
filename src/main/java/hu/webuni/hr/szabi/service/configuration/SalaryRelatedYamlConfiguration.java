package hu.webuni.hr.szabi.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "hr")
public class SalaryRelatedYamlConfiguration {

    private Map<String, Limit> limits;
    private int maxPercentage;

    public int getMaxPercentage() {
        return maxPercentage;
    }

    public void setMaxPercentage(int maxPercentage) {
        this.maxPercentage = maxPercentage;
    }

    public Map<String, Limit> getLimits() {
        return limits;
    }

    public void setLimits(Map<String, Limit> limits) {
        this.limits = limits;
    }

    public static class Limit {

        private String maxmonth;
        private String percentage;


        public String getMaxmonth() {
            return maxmonth;
        }

        public void setMaxmonth(String maxmonth) {
            this.maxmonth = maxmonth;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }
    }
}