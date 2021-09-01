package com.beer.api.demo.client;

import com.beer.api.demo.model.BeerDto;
import com.beer.api.demo.model.BeerPagedList;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BeerClient {
    Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand);
    Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName, String beerStyle, Boolean showInventoryOnHand);
    Mono<ResponseEntity<Void>> createBeer(BeerDto dto);
    Mono<ResponseEntity<Void>> updateBeer(UUID beerId, BeerDto dto);
    Mono<ResponseEntity<Void>> deleteBeerById(UUID id);
    Mono<BeerDto> getBeerByUPC(String upc);
}
