package com.itsoul.lab.bitcoin.spring.services;

import com.itsoul.lab.bitcoin.spring.models.CoinbaseResponse;
import reactor.core.publisher.Mono;

public interface CoinbaseService {
    public Mono<CoinbaseResponse> getCryptoPrice(String priceName);
}
