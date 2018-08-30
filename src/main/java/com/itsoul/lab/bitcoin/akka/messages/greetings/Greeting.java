package com.itsoul.lab.bitcoin.akka.messages.greetings;

//#printer-messages
public class Greeting {
  public final String message;

  public Greeting(String message) {
    this.message = message;
  }
}
