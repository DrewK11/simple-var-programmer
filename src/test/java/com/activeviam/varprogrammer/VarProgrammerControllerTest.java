package com.activeviam.varprogrammer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class VarProgrammerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VarProgrammerService varProgrammerService;

    @Test
    void calculateSingleTradeVar_should_return_the_correct_response_when_provided_a_list_of_trade_values() throws Exception {
        // given
        VarCalculationRequest request = new VarCalculationRequest(List.of(1.0, 2.0, 3.0), 0.95);
        when(varProgrammerService.calculateVar(request.getHistoricalValues(), request.getConfidenceLevel())).thenReturn(10.0);

        // when
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculate/single")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10.0));
    }

    @Test
    void calculatePortfolioVar_should_return_the_correct_response_when_provided_a_list_of_trades() throws Exception {
        // given
        PortfolioVarCalculationRequest request = new PortfolioVarCalculationRequest(
                List.of(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(2.0, 3.0, 4.0)), 0.95);
        when(varProgrammerService.calculatePortfolioVar(request.getPortfolioHistoricalValues(), request.getConfidenceLevel())).thenReturn(20.0);

        // when
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculate/portfolio")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(20.0));
    }
}
