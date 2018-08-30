package com.itsoul.lab.bitcoin.akka.messages;

public class GetThisCryptoPrice {

    public final String whatPrice;

    public GetThisCryptoPrice(String whatPrice) {
        this.whatPrice = whatPrice;
    }
}
