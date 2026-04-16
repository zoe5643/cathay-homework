package org.example.controller;

import org.example.dto.coindesk.CoindeskRawDto;
import org.example.dto.coindesk.CoindeskResponseDto;
import org.example.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    // ① 呼叫並回傳 CoinDesk 原始資料
    @GetMapping("/raw")
    public ResponseEntity<CoindeskRawDto> getRaw() {
        return ResponseEntity.ok(coindeskService.getRawData());
    }

    // ② 呼叫並回傳轉換後的新格式
    @GetMapping("/transformed")
    public ResponseEntity<CoindeskResponseDto> getTransformed() {
        return ResponseEntity.ok(coindeskService.getTransformedData());
    }

}
