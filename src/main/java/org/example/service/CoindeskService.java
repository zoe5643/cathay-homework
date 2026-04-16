package org.example.service;

import org.example.dto.coindesk.CoindeskRawDto;
import org.example.dto.coindesk.CoindeskResponseDto;
import org.example.dto.coindesk.TransformedCurrencyDto;
import org.example.entity.Currency;
import org.example.repository.CurrencyRepository;
import org.example.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoindeskService {

    private static final String COINDESK_URL = "https://kengp3.github.io/blog/coindesk.json";

    @Autowired
    private CurrencyRepository currencyRepository;

    public CoindeskRawDto getRawData() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(COINDESK_URL, CoindeskRawDto.class);
    }

    // 呼叫 CoinDesk API，進行資料轉換後回傳
    public CoindeskResponseDto getTransformedData() {
        CoindeskRawDto rawData = getRawData();

        // 轉換時間格式
        String updatedAt = DateUtil.formatFromISO(rawData.getTime().getUpdatedISO());

        // 轉換幣別資訊，並從 DB 查中文名稱
        List<TransformedCurrencyDto> currencies = rawData.getBpi().values().stream()
                .map(bpi -> {
                    TransformedCurrencyDto dto = new TransformedCurrencyDto();
                    dto.setCode(bpi.getCode());
                    dto.setRate(bpi.getRateFloat());
                    dto.setChineseName(
                            currencyRepository.findById(bpi.getCode())
                                    .map(Currency::getChineseName)
                                    .orElse(""));
                    return dto;
                })
                .collect(Collectors.toList());

        CoindeskResponseDto result = new CoindeskResponseDto();
        result.setUpdatedAt(updatedAt);
        result.setCurrencies(currencies);
        return result;
    }

}
