package org.example;

import org.example.dto.coindesk.CoindeskRawDto;
import org.example.dto.coindesk.CoindeskResponseDto;
import org.example.entity.Currency;
import org.example.service.CoindeskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CoindeskServiceTest {

    @Autowired
    private CoindeskService coindeskService;
    private CoindeskRawDto mockRaw;
    private List<Currency> mockCurrencies;

    @BeforeEach
    public void setUp() {
        // 準備 Mock 資料
        mockRaw = new CoindeskRawDto();

        CoindeskRawDto.CoindeskTimeDto timeDto = new CoindeskRawDto.CoindeskTimeDto();
        timeDto.setUpdatedISO("2026-04-20T07:07:20+00:00");
        mockRaw.setTime(timeDto);

        Map<String, CoindeskRawDto.BpiInfo> bpiMap = new LinkedHashMap<>();

        CoindeskRawDto.BpiInfo usdBpi = new CoindeskRawDto.BpiInfo();
        usdBpi.setCode("USD");
        usdBpi.setRateFloat(57800.2984);
        bpiMap.put("USD", usdBpi);

        CoindeskRawDto.BpiInfo gbpBpi = new CoindeskRawDto.BpiInfo();
        gbpBpi.setCode("GBP");
        gbpBpi.setRateFloat(43990.0203);
        bpiMap.put("GBP", gbpBpi);

        mockRaw.setBpi(bpiMap);

        Currency usd = new Currency();
        usd.setCode("USD");
        usd.setChineseName("美元");

        Currency gbp = new Currency();
        gbp.setCode("GBP");
        gbp.setChineseName("英鎊");

        mockCurrencies = Arrays.asList(usd, gbp);
    }

    @Test
    public void testDataTransformation() {
        CoindeskResponseDto result = coindeskService.convert(mockRaw, mockCurrencies);

        assertNotNull(result);
        assertEquals("2026/04/20 07:07:20", result.getUpdatedAt());
        assertEquals("美元", result.getCurrencies().get(0).getChineseName());
        assertEquals(2, result.getCurrencies().size());
    }


}
