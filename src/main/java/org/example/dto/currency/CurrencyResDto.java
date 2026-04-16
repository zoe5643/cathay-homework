package org.example.dto.currency;

import lombok.Data;
import org.example.entity.Currency;

@Data
public class CurrencyResDto {
    private String code;
    private String chineseName;

    public static CurrencyResDto fromEntity(Currency currency) {
        CurrencyResDto dto = new CurrencyResDto();
        dto.setCode(currency.getCode());
        dto.setChineseName(currency.getChineseName());
        return dto;
    }
}
