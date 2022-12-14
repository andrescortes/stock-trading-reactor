package com.springwebflux5.stocktrading.controller;

import com.springwebflux5.stocktrading.dto.StockRequest;
import com.springwebflux5.stocktrading.dto.StockResponse;
import com.springwebflux5.stocktrading.model.Stock;
import com.springwebflux5.stocktrading.service.StockService;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stocks")
public class StocksController {
    private final StockService stockService;

    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{id}")
    public Mono<StockResponse> getOneStock(@PathVariable final String id) {
        return stockService.getOneStock(id);
    }

    @GetMapping
    public Flux<StockResponse> getAllStocks(@RequestParam(required = false, defaultValue = "0") final
        BigDecimal priceGreaterThan) {
        return stockService.getAllStocks(priceGreaterThan);
    }
    @PostMapping
    public Mono<StockResponse> createStock(@RequestBody StockRequest stockRequest){
        return stockService.createStock(stockRequest);
    }
}
