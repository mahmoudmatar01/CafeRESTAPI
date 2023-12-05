package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.service.GreetingService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String getGreeting() {
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();

        if (hour >= 6 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 18) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
}
