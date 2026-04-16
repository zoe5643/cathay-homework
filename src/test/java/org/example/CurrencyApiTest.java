package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.currency.CurrencyReqDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void testCurrencyCrud() throws Exception {

        // 新增
        CurrencyReqDto createReq = new CurrencyReqDto();
        createReq.setCode("TWD");
        createReq.setChineseName("新台幣");

        mockMvc.perform(post("/api/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("TWD"))
                .andExpect(jsonPath("$.chineseName").value("新台幣"));

        // 查詢單個貨幣
        mockMvc.perform(get("/api/currencies/USD").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"))
                .andExpect(jsonPath("$.chineseName").value("美元"));

        // 查詢所有貨幣
        mockMvc.perform(get("/api/currencies/list").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        // 更新
        CurrencyReqDto updateReq = new CurrencyReqDto();
        updateReq.setCode("TWD");
        updateReq.setChineseName("元");

        mockMvc.perform(put("/api/currencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chineseName").value("元"));

        // 刪除
        mockMvc.perform(delete("/api/currencies/TWD"))
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/currencies/TWD"))
                .andExpect(status().isNotFound());
    }

}