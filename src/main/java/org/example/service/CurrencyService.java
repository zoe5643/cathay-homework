package org.example.service;

import org.example.dto.currency.CurrencyReqDto;
import org.example.dto.currency.CurrencyResDto;
import org.example.entity.Currency;
import org.example.exception.DataNotFoundException;
import org.example.repository.CurrencyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<CurrencyResDto> getAllCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencies.stream()
                .map(CurrencyResDto::fromEntity)
                .collect(Collectors.toList());
    }

    public CurrencyResDto getCurrencyByCode(String code) {
        Currency currency = currencyRepository.findById(code)
                .orElseThrow(() -> new DataNotFoundException("查無幣別"));

        CurrencyResDto dto = new CurrencyResDto();
        BeanUtils.copyProperties(currency, dto);
        return dto;

    }

    public CurrencyResDto createCurrency(CurrencyReqDto dto) {
        Currency saved = currencyRepository.save(dto.toEntity());
        return CurrencyResDto.fromEntity(saved);
    }

    public CurrencyResDto updateCurrency(CurrencyReqDto dto) {
        Currency currency = currencyRepository.findById(dto.getCode()).orElseThrow(() -> new DataNotFoundException("查無幣別"));
        currency.setChineseName(dto.getChineseName());
        currency.setUpdatedAt(LocalDateTime.now());
        return CurrencyResDto.fromEntity(currencyRepository.save(currency));
    }

    public void deleteCurrency(String code) {
        currencyRepository.deleteById(code);
    }
}
