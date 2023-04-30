package com.swan.reactivespring.service;

import com.swan.reactivespring.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReactiveMongoOperations reactiveMongoOperations;

    @Override
    public Mono<Reservation> getReservation(String id) {
        return reactiveMongoOperations.findById(id, Reservation.class);
    }

    @Override
    public Mono<Reservation> saveReservation(Mono<Reservation> reservation) {
        return reactiveMongoOperations.save(reservation);
    }

    @Override
    public Mono<Reservation> updatePrice(Integer price, String id) {
        return reactiveMongoOperations.findAndModify(
                Query.query(Criteria.where("id").is(id)), Update.update("price", price), Reservation.class)
                .flatMap(result -> {
                    result.setPrice(price);
                    return Mono.just(result);
                });
    }

    @Override
    public Mono<Boolean> deleteReservation(String id) {
        return reactiveMongoOperations.remove(Query.query(Criteria.where("id").is(id)), Reservation.class)
                .flatMap(deleted -> Mono.just(deleted.wasAcknowledged()));
    }

}
