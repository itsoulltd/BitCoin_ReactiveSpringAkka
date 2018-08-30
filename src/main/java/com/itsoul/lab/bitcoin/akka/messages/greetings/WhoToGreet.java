package com.itsoul.lab.bitcoin.akka.messages.greetings;

//#greeter-messages
public class WhoToGreet {
  public final String who;

  public WhoToGreet(String who) {
      this.who = who;
  }
}
