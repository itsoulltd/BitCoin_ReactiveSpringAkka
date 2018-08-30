package com.itsoul.lab.bitcoin.akka.cmd;

import com.itsoul.lab.bitcoin.akka.actors.Poller;
import com.itsoul.lab.bitcoin.akka.actors.PriceRequestor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.itsoul.lab.bitcoin.akka.actors.Printer;
import com.itsoul.lab.bitcoin.spring.services.CoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AkkaCMDLineUI implements CommandLineRunner {

  @Autowired
  private CoinbaseService coinbaseService;

  private void printHeader(){
    System.out.println(
              "\n============================================================="
              + "\n                                                           "
              + "\n           Coinbase Price Service                          "
              + "\n           ITSoul Limited                                  "
              + "\n                                                           "
              + "\n           Written by: ITSoul Limited                      "
              + "\n============================================================="
    );
    System.out.println();
  }

  @Override
  public void run(String... args) throws Exception {

    final ActorSystem system = ActorSystem.create("BitCoinService");

    printHeader();

    final ActorRef printer = system.actorOf(Printer.props(), "printer");
    final ActorRef priceRequestor = system.actorOf(PriceRequestor.props(printer, coinbaseService), "requestor");

    final ActorRef poller_USD = system.actorOf(Poller.props("BTC-USD", priceRequestor), "poller-USD");
    final ActorRef poller_ETH = system.actorOf(Poller.props("ETH-USD", priceRequestor), "poller-ETH");

  }

}
