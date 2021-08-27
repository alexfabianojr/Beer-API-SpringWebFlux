package com.beer.api.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null private UUID id;
    @NotBlank private String beerName;
    @NotBlank private BeerStyleEnum beerStyle;
    private String upc;
    private BigDecimal price;
    private Integer quantityOnHand;
}
