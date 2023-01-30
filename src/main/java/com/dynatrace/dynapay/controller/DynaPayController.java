package com.dynatrace.dynapay.controller;

import com.dynatrace.dynapay.model.DynaPay;
import com.dynatrace.dynapay.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dynapay")
public class DynaPayController extends HardworkingController {
    @Autowired
    ConfigRepository configRepository;


    // make a payment
    @PostMapping("")
    public DynaPay createPayment(@RequestBody DynaPay dynaPay) {
        simulateHardWork();
        simulateCrash();

        double rand = Math.random();

        if (rand >= getPercentFailure() / 100.0) {
            // successful payment
            dynaPay.setSucceeded(true);
            dynaPay.setMessage("OK");
        } else {
            dynaPay.setSucceeded(false);
            dynaPay.setMessage("Failed");
        }

        return dynaPay;
    }

    @Override
    public ConfigRepository getConfigRepository() {
        return configRepository;
    }
}
