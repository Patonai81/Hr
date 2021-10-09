package hu.webuni.hr.szabi.service.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties("hr")
@Validated
public class SalaryRelatedPropertyConfiguration {

    @Value("#{${hr.employeeService.limits}}")
    Map<String, Map<String, String>> salaryConfig;
    Map<String, Map<String, String>> orderedConfig;
    int maxPercentage;
    public static final String MAXMONTH = "maxmonth";
    public static final String PERCENTAGE= "percentage";

    public int getMaxPercentage() {
        return maxPercentage;
    }

    public void setMaxPercentage(int maxPercentage) {
        this.maxPercentage = maxPercentage;
    }

    public Map<String, Map<String, String>> getMapOfList() {
        return  orderedConfig == null ? orderConfig(): orderedConfig ;
    }

    public void setMapOfList(Map<String, Map<String, String>> mapOfList) {
        this.salaryConfig = mapOfList;
    }

    private Map<String, Map<String, String>> orderConfig() {

        return salaryConfig.entrySet().stream().sorted((a, b) -> {
                    return Integer.compare(Integer.valueOf(a.getValue().get(MAXMONTH)), Integer.valueOf(b.getValue().get(MAXMONTH)));
                }
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));


    }

}
