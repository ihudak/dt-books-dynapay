package com.dynatrace.dynapay.controller;

import com.dynatrace.dynapay.model.DynaPay;
import com.dynatrace.dynapay.repository.ConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(DynaPayController.class);


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
            logger.info("Payment succeeded");
        } else {
            dynaPay.setSucceeded(false);
            dynaPay.setMessage("Failed");
            logger.error("Payment failed");
        }

        return dynaPay;
    }

    @Override
    public ConfigRepository getConfigRepository() {
        return configRepository;
    }
}
