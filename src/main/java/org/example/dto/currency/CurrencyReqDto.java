package org.example.dto.currency;

import lombok.Data;
import org.example.entity.Currency;

import java.time.LocalDateTime;

@Data
public class CurrencyReqDto {
    private String code;
    private String chineseName;

    public Currency toEntity() {
        Currency currency = new Currency();
        currency.setCode(this.code);
        currency.setChineseName(this.chineseName);
        currency.setUpdatedAt(LocalDateTime.now());
        return currency;
    }
}
