package com.UST.Spring_Reactive.service;

import com.UST.Spring_Reactive.dto.Tripdto;
import com.UST.Spring_Reactive.repository.TripRepository;
import com.UST.Spring_Reactive.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Mono<Tripdto> addTrip(Mono<Tripdto> tripdto) {
        return tripdto.map(AppUtils::dtoToEntity)
                .flatMap(tripRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Flux<Tripdto> getAllTrips() {
        return tripRepository.findAll()
                .map(AppUtils::entityToDto);
    }

    public Mono<Tripdto> getTripById(String id) {
        return tripRepository.findById(id)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteTripById(String id) {
        return tripRepository.deleteById(id);
    }

    public Mono<Tripdto> updateTrip(String id, Mono<Tripdto> tripdto) {
        return tripRepository.findById(id)
                .flatMap(existingTrip -> tripdto.map(AppUtils::dtoToEntity)
                        .doOnNext(entity -> entity.setId(id))
                        .flatMap(tripRepository::save)
                        .map(AppUtils::entityToDto));
    }

    public Flux<Tripdto> getTripByRating(int rating) {
        return tripRepository.findByRating(rating)
                .map(AppUtils::entityToDto);

    }

    public Flux<Tripdto> getTripByDuration(String duration) {
        return tripRepository.findByDuration(duration)
                .map(AppUtils::entityToDto);
    }



//    public Flux<Tripdto> getTripsBasedOnPriceRange(double higherPrice, double lowerPrice) {
//        return tripRepository.findAll()
//                .filter(trip -> trip.getPrice() >= lowerPrice && trip.getPrice() <= higherPrice)
//                .map(AppUtils::entityToDto);
//    }

    public Flux<Tripdto> getTripByPricerange(double lowerPrice, double higherPrice) {
        return tripRepository.findByPriceBetween(lowerPrice, higherPrice)
                .map(AppUtils::entityToDto);
    }
}
