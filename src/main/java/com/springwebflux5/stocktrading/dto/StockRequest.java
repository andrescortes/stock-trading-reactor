package com.springwebflux5.stocktrading.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springwebflux5.stocktrading.model.Stock;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StockRequest {

    @JsonProperty("stockName")
    private String name;
    private BigDecimal price;
    private String currency;

    public Stock toModel(){
        return Stock.builder()
            .name(this.name)
            .price(this.price)
            .currency(this.currency)
            .build();
    }
}
