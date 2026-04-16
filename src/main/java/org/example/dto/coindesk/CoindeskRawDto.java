package org.example.dto.coindesk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CoindeskRawDto {
    private CoindeskTimeDto time;
    private String disclaimer;
    private String chartName;
    private Map<String, BpiInfo> bpi; // key = USD/GBP/EUR

    @Data
    public static class CoindeskTimeDto {
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class BpiInfo {
        private String code;
        private String symbol;
        private String rate;
        private String description;

        @JsonProperty("rate_float")
        private Double rateFloat;
    }
}
