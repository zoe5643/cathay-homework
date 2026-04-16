package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CoindeskApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCoindeskApi() throws Exception {
        mockMvc.perform(get("/api/coindesk/raw"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.bpi").exists())
                .andExpect(jsonPath("$.bpi.USD").exists())
                .andExpect(jsonPath("$.bpi.GBP").exists())
                .andExpect(jsonPath("$.bpi.EUR").exists())
                .andReturn();
    }

    @Test
    public void testGetTransformedDataApi() throws Exception {
        mockMvc.perform(get("/api/coindesk/transformed").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updatedAt").exists())
                .andExpect(jsonPath("$.currencies").isArray())
                .andExpect(jsonPath("$.currencies[0].code").exists())
                .andExpect(jsonPath("$.currencies[0].chineseName").exists())
                .andExpect(jsonPath("$.currencies[0].rate").isNumber());
    }
}
