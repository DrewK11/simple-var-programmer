package com.activeviam.varprogrammer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VarProgrammerServiceTest {

    @InjectMocks
    private VarProgrammerService varProgrammerService;

    @Test
    public void calculateVar_should_return_expected_result() {
        // given
        List<Double> historicalValues = Arrays.asList(5.0, 3.0, 4.0, 2.0, 1.0);
        double confidenceLevel = 0.95;
        double expectedResult = 1.0;

        try (MockedStatic<VarProgrammerUtil> mockedStatic = mockStatic(VarProgrammerUtil.class)) {
            // when
            mockedStatic.when(() -> VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel))
                    .thenReturn(expectedResult);
            // then
            assertEquals(expectedResult, varProgrammerService.calculateVar(historicalValues, confidenceLevel));
        }
    }

    @Test
    void calculatePortfolioVar_should_return_expected_result() {
        // given
        List<List<Double>> portfolioHistoricalValues = List.of(
                Arrays.asList(5.0, 3.0, 4.0, 2.0, 1.0),
                Arrays.asList(1.0, 5.0, 3.0, 2.0, 4.0),
                Arrays.asList(5.0, 4.0, 3.0, 6.0, 7.0)
        );
        double confidenceLevel = 0.95;
        double expectedResult = 1.29;

        try (MockedStatic<VarProgrammerUtil> mockedStatic = mockStatic(VarProgrammerUtil.class)) {
            // when
            mockedStatic.when(() -> VarProgrammerUtil.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel))
                    .thenReturn(expectedResult);

            // then
            assertEquals(expectedResult, varProgrammerService.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel));
        }
    }
}
