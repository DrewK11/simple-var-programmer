package com.activeviam.varprogrammer;

import org.apache.commons.math3.special.Erf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VarProgrammerUtil {

    private static final Logger logger = LoggerFactory.getLogger(VarProgrammerUtil.class);

    public static double calculateVar(List<Double> historicalValues, double confidenceLevel) {
        logger.info("Calculating var with historicalValues: {} and confidenceLevel: {}", historicalValues, confidenceLevel);

        if (historicalValues.isEmpty()) {
            logger.error("Historical values list is empty. Cannot calculate var.");
            throw new IllegalArgumentException("Historical values list is empty");
        }

        historicalValues.sort(Double::compareTo);
        return percentile(historicalValues, 100 - (confidenceLevel * 100));
    }

    public static double calculatePortfolioVar(List<List<Double>> portfolioHistoricalValues, double confidenceLevel) {
        logger.info("Calculating var for a portfolio with portfolioHistoricalValues {} at confidenceLevel: {}", portfolioHistoricalValues, confidenceLevel);
        double weight = 1.0 / portfolioHistoricalValues.size();
        double zScore = calculateZScore(confidenceLevel);
        double portfolioVar = 0.0;

        for (List<Double> tradeValues : portfolioHistoricalValues) {
            logger.info("Single Trade Values are: {}", tradeValues);
            double singleTradeVar = calculateVar(tradeValues, confidenceLevel);
            portfolioVar += Math.pow(weight * singleTradeVar, 2);;
        }

        portfolioVar = Math.sqrt(portfolioVar) * zScore;

        return portfolioVar;
    }

    private static double percentile(List<Double> historicalValues, double percent) {
        int index = (int) Math.ceil(percent * historicalValues.size() / 100) - 1;
        if (index < 0) {
            index = 0;
        }

        return historicalValues.get(index);
    }

    private static double calculateZScore(double confidenceLevel) {
        return Erf.erfInv(2 * confidenceLevel - 1);
    }
}
