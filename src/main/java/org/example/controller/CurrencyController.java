package org.example.controller;

import org.example.dto.currency.CurrencyReqDto;
import org.example.dto.currency.CurrencyResDto;
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
        return ResponseEntity.ok(currencyService.getCurrencyByCode(code.toUpperCase()));
    }

    @PostMapping
    public ResponseEntity<CurrencyResDto> createCurrency(@RequestBody CurrencyReqDto dto) {
        return ResponseEntity.ok(currencyService.createCurrency(dto));
    }

    @PutMapping
    public ResponseEntity<CurrencyResDto> updateCurrency(@RequestBody CurrencyReqDto dto) {
        return ResponseEntity.ok(currencyService.updateCurrency(dto)) ;
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String code) {
        currencyService.deleteCurrency(code);
        return ResponseEntity.noContent().build();
    }
}
