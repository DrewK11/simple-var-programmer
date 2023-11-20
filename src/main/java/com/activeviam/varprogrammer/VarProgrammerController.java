package com.activeviam.varprogrammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Map;

@Controller
public class VarProgrammerController {
    private static final Logger logger = LoggerFactory.getLogger(VarProgrammerController.class);

    private final VarProgrammerService varProgrammerService;

    @Autowired
    public VarProgrammerController(VarProgrammerService varProgrammerService) {
        this.varProgrammerService = varProgrammerService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/calculate/single")
    public ResponseEntity<Map<String, Double>> calculateSingleTradeVar(@RequestBody VarCalculationRequest request) {
        logger.info("Received VarCalculationRequest. Historical values are: {}", request.getHistoricalValues());
        try {
            double result = varProgrammerService.calculateVar(request.getHistoricalValues(), request.getConfidenceLevel());
            Map<String, Double> response = Collections.singletonMap("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error calculating var for a single trade", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/calculate/portfolio")
    public ResponseEntity<Map<String, Double>> calculatePortfolioVar(@RequestBody PortfolioVarCalculationRequest request) {
        logger.info("Received PortfolioCalculationRequest. Historical values for multiple trades are: {}", request.getPortfolioHistoricalValues());
        try {
            double result = varProgrammerService.calculatePortfolioVar(request.getPortfolioHistoricalValues(), request.getConfidenceLevel());
            Map<String, Double> response = Collections.singletonMap("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error calculating var for a portfolio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}