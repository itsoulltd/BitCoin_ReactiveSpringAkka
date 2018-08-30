package com.itsoul.lab.bitcoin.akka.actors;

import akka.actor.AbstractActorWithTimers;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.itsoul.lab.bitcoin.akka.messages.FirstTick;
import com.itsoul.lab.bitcoin.akka.messages.GetThisCryptoPrice;
import com.itsoul.lab.bitcoin.akka.messages.Tick;

import java.time.Duration;

public class PriceTicker extends AbstractActorWithTimers {

    private static final Object TICK_KEY = "TickKey";
    private final ActorRef requestorActor;
    private final String cryptoName;

    public PriceTicker(ActorRef requestorActor, String cryptoName) {
        this.requestorActor = requestorActor;
        this.cryptoName = cryptoName;
        getTimers().startSingleTimer(TICK_KEY, new FirstTick(), Duration.ofMillis(3000));
    }

    public static Props props(String cryptoName, ActorRef requestorActor){
        return Props.create(PriceTicker.class, () -> new PriceTicker(requestorActor, cryptoName));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                    FirstTick.class, message -> {
                        getTimers().startPeriodicTimer(TICK_KEY, new Tick(), Duration.ofSeconds(3));
                    })
                .match(
                    Tick.class, message -> {
                        //System.out.println("tick");
                        GetThisCryptoPrice aboutCryptoPrice = new GetThisCryptoPrice(cryptoName);
                        requestorActor.tell(aboutCryptoPrice, getSelf());
                    })
                .build();
    }
}
