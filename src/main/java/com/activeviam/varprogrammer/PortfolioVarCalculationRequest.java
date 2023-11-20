package com.activeviam.varprogrammer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PortfolioVarCalculationRequest {
    @JsonProperty("portfolioHistoricalValues")
    private List<List<Double>> portfolioHistoricalValues;
    @JsonProperty("confidenceLevel")
    private double confidenceLevel;

    public PortfolioVarCalculationRequest() {
    }

    public PortfolioVarCalculationRequest(List<List<Double>> portfolioHistoricalValues, double confidenceLevel) {
        this.portfolioHistoricalValues = portfolioHistoricalValues;
        this.confidenceLevel = confidenceLevel;
    }

    public List<List<Double>> getPortfolioHistoricalValues() {
        return portfolioHistoricalValues;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }
}
