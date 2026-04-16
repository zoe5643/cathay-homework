package org.example.controller;

import org.example.dto.currency.CurrencyReqDto;
import org.example.dto.currency.CurrencyResDto;
import org.example.entity.Currency;
import org.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/list")
    public ResponseEntity<List<CurrencyResDto>> getAllCurrencies() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyResDto> getCurrencyByCode(@PathVariable String code) {
        CurrencyResDto currency = currencyService.getCurrencyByCode(code.toUpperCase());
        return currency != null ? ResponseEntity.ok(currency) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CurrencyResDto> createCurrency(@RequestBody CurrencyReqDto dto) {
        return ResponseEntity.ok(currencyService.createCurrency(dto));
    }

    @PutMapping
    public ResponseEntity<CurrencyResDto> updateCurrency(@RequestBody CurrencyReqDto dto) {
        CurrencyResDto currency = currencyService.updateCurrency(dto);
        return currency != null ? ResponseEntity.ok(currency) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String code) {
        currencyService.deleteCurrency(code);
        return ResponseEntity.noContent().build();
    }
}
