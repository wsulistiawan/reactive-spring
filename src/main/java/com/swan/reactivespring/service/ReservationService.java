package com.swan.reactivespring.service;

import com.swan.reactivespring.model.Reservation;
import reactor.core.publisher.Mono;

public interface ReservationService {

    Mono<Reservation> getReservation(String id);

    Mono<Reservation> saveReservation(Mono<Reservation> reservation);

    Mono<Reservation> updatePrice(Integer price, String id);

    Mono<Boolean> deleteReservation(String id);

}
