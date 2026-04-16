package org.example.dto.currency;

import lombok.Data;
import org.example.entity.Currency;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class CurrencyReqDto {

    @NotBlank(message = "幣別代碼不能為空")
    private String code;
    @NotBlank(message = "中文名稱不能為空")
    private String chineseName;

    public Currency toEntity() {
        Currency currency = new Currency();
        currency.setCode(this.code);
        currency.setChineseName(this.chineseName);
        currency.setUpdatedAt(LocalDateTime.now());
        return currency;
    }
}
