package com.UST.Spring_Reactive.repository;

import com.UST.Spring_Reactive.dto.Tripdto;
import com.UST.Spring_Reactive.entity.Trip;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface TripRepository extends ReactiveMongoRepository<Trip,String> {


    Flux<Trip> findByRating(int rating);

    Flux<Trip> findByDuration(String duration);

//    @Query("{price:{$lt:?0,$gt:?1}}")
//    Flux<Trip> getTripsBasedOnPrice(double higherPrice, double lowerPrice);

    Flux<Trip> findByPriceBetween(double lowerPrice, double higherPrice);

    // Flux<Trip>findPriceBetween(double higherPrice, double lowerPrice);
}
