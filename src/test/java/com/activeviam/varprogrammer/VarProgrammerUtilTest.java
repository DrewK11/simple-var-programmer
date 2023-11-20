package com.activeviam.varprogrammer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VarProgrammerUtilTest {
    @Test
    void calculateVar_should_calculate_var_when_provided_historical_values_for_a_single_trade() {
        // given
        List<Double> historicalValues = Arrays.asList(5.0, 3.0, 4.0, 2.0, 1.0);
        double confidenceLevel = 0.95;

        // when
        double var = VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel);

        // then
        assertEquals(1.0, var);
    }

    @Test
    void calculateVar_should_calculate_var_when_provided_a_single_value_for_a_trade() {
        // given
        List<Double> historicalValues = Arrays.asList(1.0);
        double confidenceLevel = 0.95;

        // when
        double var = VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel);

        // then
        assertEquals(1.0, var);
    }

    @Test
    void calculateVar_should_calculate_var_when_provided_negative_values_for_a_single_trade() {
        // given
        List<Double> historicalValues = Arrays.asList(5.0, 3.0, 4.0, 2.0, -1.0);
        double confidenceLevel = 0.3;

        // when
        double var = VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel);

        // then
        assertEquals(4.0, var);
    }

    @Test
    void calculateVar_should_calculate_var_when_provided_a_single_value_for_a_trade_and_a_low_confidence_level() {
        // given
        List<Double> historicalValues = Arrays.asList(5.0, 3.0, 4.0, 2.0, 1.0);
        double confidenceLevel = 0.1;

        // when
        double var = VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel);

        // then
        assertEquals(5.0, var);
    }

    @Test
    void calculateVar_should_return_IllegalArgumentException_when_provided_with_empty_input() {
        // given
        List<Double> historicalValues = List.of();
        double confidenceLevel = 0.95;
        String expectedMessage = "Historical values list is empty";

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel);
        });

        String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void calculatePortfolioVar_should_calculate_portfolio_var_for_a_list_of_trades() {
        // given
        List<List<Double>> portfolioHistoricalValues = List.of(
                Arrays.asList(5.0, 3.0, 4.0, 2.0, 1.0),
                Arrays.asList(1.0, 5.0, 3.0, 2.0, 4.0),
                Arrays.asList(5.0, 4.0, 3.0, 6.0, 7.0)
        );
        double confidenceLevel = 0.95;

        // when
        double var = VarProgrammerUtil.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel);

        // then
        assertEquals(1.29, var, 0.01);
    }

    @Test
    void calculatePortfolioVar_should_calculate_portfolio_var_for_a_list_of_trades_with_negative_values() {
        // given
        List<List<Double>> portfolioHistoricalValues = List.of(
                Arrays.asList(5.0, 3.0, -4.0, 2.0, 1.0),
                Arrays.asList(1.0, -5.0, 3.0, 2.0, 4.0),
                Arrays.asList(5.0, 4.0, 3.0, -6.0, -7.0)
        );
        double confidenceLevel = 0.95;

        // when
        double var = VarProgrammerUtil.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel);

        // then
        assertEquals(3.68, var, 0.01);
    }

    @Test
    void calculatePortfolioVar_should_calculate_portfolio_var_for_a_list_of_trades_with_single_value() {
        // given
        List<List<Double>> portfolioHistoricalValues = List.of(
                Arrays.asList(5.0),
                Arrays.asList(1.0),
                Arrays.asList(-7.0)
        );
        double confidenceLevel = 0.95;

        // when
        double var = VarProgrammerUtil.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel);

        // then
        assertEquals(3.36, var, 0.01);
    }

    @Test
    void calculatePortfolioVar_should_calculate_portfolio_var_for_a_list_of_trades_with_duplicate_values() {
        // given
        List<List<Double>> portfolioHistoricalValues = List.of(
                Arrays.asList(5.0, 5.0),
                Arrays.asList(1.0, 1.0),
                Arrays.asList(-7.0, -7.0)
        );
        double confidenceLevel = 0.95;

        // when
        double var = VarProgrammerUtil.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel);

        // then
        assertEquals(3.36, var, 0.01);
    }
}
