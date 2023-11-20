package com.activeviam.varprogrammer;

import java.util.List;

public interface VarProgrammerServiceInterface {
    double calculateVar(List<Double> historicalValues, double confidenceLevel);

    double calculatePortfolioVar(List<List<Double>> portfolioHistoricalValues, double confidenceLevel);
}
