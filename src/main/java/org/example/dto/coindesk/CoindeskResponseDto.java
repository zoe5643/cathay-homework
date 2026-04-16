package org.example.dto.coindesk;

import lombok.Data;

import java.util.List;

@Data
public class CoindeskResponseDto {
    private String updatedAt; // 格式化後的 1990/01/01 00:00:00
    private List<TransformedCurrencyDto> currencies;
}
