// README.md
# Application to calculate Value at Risk (var) for single trades or a portfolio of multiple trades
### Introduction
This application was made using Java and SpringBoot to calculate Value At Risk using the historical method. Some assumptions made regarding calculating portfolio var are that individual trades within a portfolio have equal weight, and they are not correlated.

### Observations
* Since this particular implementation of var calculation is based on historical market conditions, it might be slow to react to changing or abnormal market conditions.
* In this implementation, portfolio var is not calculated by summing the individual vars of each trade.
* In this implementation, portfolio var seems to show the effect of diversification. Since the individual trades are not perfectly correlated, they may move in opposite directions from one another and reduce the overall risk for the portfolio.
* Following the above point, since the portfolio var reflects the combined risk of a many trades, risk aggregation may help to offset the effects of different trades, resulting in a lower combined var when compared to the individual var for single trades.

### Building & Running 
* Clone this repository.
* Cd into the project root folder.
* Then we can use Spring Boot & Maven to run the app like so:
```
mvn clean spring-boot:run
```
### Usage
* Once the app is running, you can enter values for single trades in the Single Trade Historical Values field in the format of (1.0, 5.0, 2.0, 6.0) and enter your preferred confidence level in the confidence level field (min value is 0, and max value is 1). Then, click on the Calculate var (Single Trade) button to see the result appear below the button.
* Portfolio var can be calculated by entering values for multiple trades in the Portfolio Historical Values field in the format of (1.0, 5.0), (2.0), (10.0, 3.0), where each individual list is the historical values of a single trade. Enter your confidence level as described above and click on the Calculate var (Portfolio) button to see the result.
