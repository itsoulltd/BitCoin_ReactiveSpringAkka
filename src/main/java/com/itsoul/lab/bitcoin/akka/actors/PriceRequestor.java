package com.itsoul.lab.bitcoin.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.itsoul.lab.bitcoin.akka.messages.CryptoPrice;
import com.itsoul.lab.bitcoin.akka.messages.GetThisCryptoPrice;
import com.itsoul.lab.bitcoin.spring.services.CoinbaseService;

public class PriceRequestor extends AbstractActor {

    private final ActorRef printerActor;
    private final CoinbaseService coinbaseService;

    public PriceRequestor(ActorRef printerActor, CoinbaseService coinbaseService) {
        this.printerActor = printerActor;
        this.coinbaseService = coinbaseService;
    }

    public static Props props(ActorRef printerActor, CoinbaseService coinbaseService){
        return Props.create(PriceRequestor.class, () -> new PriceRequestor(printerActor,coinbaseService));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(GetThisCryptoPrice.class, what -> {
            CryptoPrice message = new CryptoPrice(coinbaseService.getCryptoPrice(what.whatPrice));
            printerActor.tell(message, getSelf());
        }).build();
    }
}
