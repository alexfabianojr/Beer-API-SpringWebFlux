package com.beer.api.demo.client;

import com.beer.api.demo.config.WebClientProperties;
import com.beer.api.demo.model.BeerDto;
import com.beer.api.demo.model.BeerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;

    @Override
    public Mono<BeerDto> getBeerById(UUID id, Boolean showInventoryOnHand) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_V1_PATH_GET_BY_ID)
                        .queryParamIfPresent("showInventoryOnHand", Optional.ofNullable(showInventoryOnHand))
                        .build(id))
                .retrieve().
                bodyToMono(BeerDto.class);
    }

    @Override
    public Mono<BeerPagedList> listBeers(Integer pageNumber, Integer pageSize, String beerName, String beerStyle, Boolean showInventoryOnHand) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_V1_PATH)
                        .queryParamIfPresent("pageNumber", Optional.ofNullable(pageNumber))
                        .queryParamIfPresent("pageSize", Optional.ofNullable(pageSize))
                        .queryParamIfPresent("beerName", Optional.ofNullable(beerStyle))
                        .queryParamIfPresent("showInventoryOnHand", Optional.ofNullable(showInventoryOnHand))
                        .build())
                .retrieve()
                .bodyToMono(BeerPagedList.class);
    }

    @Override
    public Mono<ResponseEntity<Void>> createBeer(BeerDto dto) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_V1_PATH)
                        .build())
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Mono<ResponseEntity<Void>> updateBeer(UUID beerId, BeerDto dto) {
        return webClient
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_V1_PATH_GET_BY_ID)
                        .build(beerId))
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBeerById(UUID id) {
        return webClient
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_V1_PATH_GET_BY_ID)
                        .build(id))
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public Mono<BeerDto> getBeerByUPC(String upc) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WebClientProperties.BEER_V1_UPC_PATH)
                        .build(upc))
                .retrieve()
                .bodyToMono(BeerDto.class);
    }
}
