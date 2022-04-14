package com.autoParkingLot.autoParkingLot.pattern.adapter;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Component
public class AdapterFactory {
    private Target target;

    @PostConstruct
    private void createAdapter(){
        target = new Adapeter(new AdapteeImpl());
    }
}
