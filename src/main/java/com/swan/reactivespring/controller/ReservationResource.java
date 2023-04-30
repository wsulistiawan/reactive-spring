package com.swan.reactivespring.controller;

import com.swan.reactivespring.domain.UpdatePrice;
import com.swan.reactivespring.model.Reservation;
import com.swan.reactivespring.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping(ReservationResource.PATH)
@CrossOrigin
public class ReservationResource {

    public static final String PATH = "/room/v1/reservation/";

    private final ReservationService reservationService;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> getById(@PathVariable("id") String id) {
        return reservationService.getReservation(id);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> create(@RequestBody Mono<Reservation> reservation) {
        return reservationService.saveReservation(reservation);
    }

    @PutMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Reservation> updatePriceById(@PathVariable("id") String id, @RequestBody UpdatePrice updatePrice) {
        return reservationService.updatePrice(updatePrice.getNewPrice(), id);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Boolean> deleteById(@PathVariable("id") String id) {
        return reservationService.deleteReservation(id);
    }

}
