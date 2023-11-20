package com.activeviam.varprogrammer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarProgrammerService implements VarProgrammerServiceInterface {
    @Override
    public double calculateVar(List<Double> historicalValues, double confidenceLevel) {
        return VarProgrammerUtil.calculateVar(historicalValues, confidenceLevel);
    }

    @Override
    public double calculatePortfolioVar(List<List<Double>> portfolioHistoricalValues, double confidenceLevel) {
        return VarProgrammerUtil.calculatePortfolioVar(portfolioHistoricalValues, confidenceLevel);
    }
}
