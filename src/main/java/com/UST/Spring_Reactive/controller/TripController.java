package com.UST.Spring_Reactive.controller;

import com.UST.Spring_Reactive.dto.Tripdto;
import com.UST.Spring_Reactive.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired
    public TripService tripService;

    public TripController (TripService tripService){
        this.tripService = tripService;
    }

    @PostMapping("/create")
    public Mono<Tripdto> addTrip(@RequestBody Mono<Tripdto> tripdto) {
        return tripService.addTrip(tripdto);
    }
    @GetMapping("/gettrips")
    public Flux<Tripdto> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/gettrip/{id}")
    public Mono<Tripdto> getTripById(@PathVariable String id){
        return tripService.getTripById(id);
    }

    @DeleteMapping("/deletetrip/{id}")
    public Mono<Void> deleteTripById(String id){
        return tripService.deleteTripById(id);
    }
    @PutMapping("/updatetrip/{id}")
    public Mono<Tripdto> updateTrip(@PathVariable String id, @RequestBody Mono<Tripdto> tripdto){
        return tripService.updateTrip(id, tripdto);
    }

    @GetMapping("/ratings/{rating}")
    public Flux<Tripdto> getTripByRating(int rating){
        return tripService.getTripByRating(rating);
    }

    @GetMapping("/durations/{duration}")
    public Flux<Tripdto> getTripByDuration(String duration){
        return tripService.getTripByDuration(duration);
    }
    @GetMapping("/pricerange")
    public Flux<Tripdto> getTripByPricerange(@RequestParam("higherPrice") double higherPrice, @RequestParam("lowerPrice") double lowerPrice){
        return tripService.getTripByPricerange(lowerPrice, higherPrice);
    }


//    @GetMapping("/price/{higherPrice}/{lowerPrice}")
//    public Flux<Tripdto> getTripsBasedOnPriceRange( double higherPrice,
//                                                            double lowerPrice) {
//        return tripService.getTripsBasedOnPriceRange(higherPrice, lowerPrice);
//    }

}
