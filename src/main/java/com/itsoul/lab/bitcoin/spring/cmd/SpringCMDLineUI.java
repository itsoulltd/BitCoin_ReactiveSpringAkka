package com.itsoul.lab.bitcoin.spring.cmd;

import com.itsoul.lab.bitcoin.spring.observers.ConsolePrintObserver;
import com.itsoul.lab.bitcoin.spring.services.CoinbaseService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SpringCMDLineUI implements CommandLineRunner {

    @Autowired
    private CoinbaseService coinbaseService;

    private void printHeader(){
        System.out.println(
                "\n============================================================="
                        + "\n                                                           "
                        + "\n Reactive Programming With SpringBoot + Webflux(Spring Reactor) + rxjava2 "
                        + "\n           ITSoul Limited                                  "
                        + "\n                                                           "
                        + "\n           Written by: ITSoul Limited                      "
                        + "\n============================================================="
        );
        System.out.println();
    }

    @Override
    public void run(String... args) throws Exception {

        printHeader();

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tick -> coinbaseService.getCryptoPrice("BTC-USD"))
                .subscribe(new ConsolePrintObserver());

        Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.io())
                .map(tick -> coinbaseService.getCryptoPrice("ETH-USD"))
                .subscribe(new ConsolePrintObserver());

    }
}
