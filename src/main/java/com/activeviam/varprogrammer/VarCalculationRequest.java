package com.activeviam.varprogrammer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class VarCalculationRequest {
    @JsonProperty("historicalValues")
    private List<Double> historicalValues;
    @JsonProperty("confidenceLevel")
    private double confidenceLevel;

    public VarCalculationRequest() {
    }

    public VarCalculationRequest(List<Double> historicalValues, double confidenceLevel) {
        this.historicalValues = historicalValues;
        this.confidenceLevel = confidenceLevel;
    }

    public List<Double> getHistoricalValues() {
        return historicalValues;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }
}
