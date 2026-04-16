package org.example.dto.coindesk;

import lombok.Data;

@Data
public class TransformedCurrencyDto {
    private String code;       // 幣別代碼
    private String chineseName;     // 中文名稱（從 DB 查）
    private Double rate;       // 匯率
}
