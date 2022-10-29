package com.springwebflux5.stocktrading.service;

import com.springwebflux5.stocktrading.dto.StockRequest;
import com.springwebflux5.stocktrading.dto.StockResponse;
import com.springwebflux5.stocktrading.model.Stock;
import com.springwebflux5.stocktrading.repository.StocksRepository;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StockService {

    private final StocksRepository stocksRepository;

    private StockService(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    public Mono<StockResponse> getOneStock(String id) {
        return stocksRepository.findById(id)
            .map(stock -> StockResponse.fromModel(stock));
    }

    public Flux<StockResponse> getAllStocks(BigDecimal priceGreaterThan) {
        return stocksRepository.findAll()
            .filter(stock -> stock.getPrice().compareTo(priceGreaterThan) > 0)
            .map(stock -> StockResponse.fromModel(stock));
    }

    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return stocksRepository.save(stockRequest.toModel())
            .map(stock -> StockResponse.fromModel(stock));
    }

}
