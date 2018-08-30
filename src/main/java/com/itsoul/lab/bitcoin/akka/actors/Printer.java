package com.itsoul.lab.bitcoin.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.itsoul.lab.bitcoin.akka.messages.CryptoPrice;
import com.itsoul.lab.bitcoin.spring.models.CoinbaseResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


public class Printer extends AbstractActor {

  static public Props props() {
    return Props.create(Printer.class, () -> new Printer());
  }

  private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

  public Printer() {
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().match(CryptoPrice.class, msg -> {
        Mono<CoinbaseResponse> response = msg.message;
        response.subscribe(coinbase -> {
                System.out.println("[" + LocalDateTime.now() + "] "
                + coinbase.getData().getBase()
                + " Buy Price: $" + coinbase.getData().getAmount()
                + " " + coinbase.getData().getCurrency());
      });
    }).build();
  }

}

