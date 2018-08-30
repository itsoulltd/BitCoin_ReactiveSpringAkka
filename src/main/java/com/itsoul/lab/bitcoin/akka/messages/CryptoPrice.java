package com.itsoul.lab.bitcoin.akka.messages;

import com.itsoul.lab.bitcoin.spring.models.CoinbaseResponse;
import reactor.core.publisher.Mono;

public class CryptoPrice {
    public Mono<CoinbaseResponse> message;

    public CryptoPrice(Mono<CoinbaseResponse> message) {
        this.message = message;
    }
}
