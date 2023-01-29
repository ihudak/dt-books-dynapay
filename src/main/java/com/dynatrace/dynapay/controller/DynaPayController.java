package com.dynatrace.dynapay.controller;

import com.dynatrace.dynapay.model.DynaPay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class DynaPayController {
    @Value("${added.workload.cpu}")
    private long cpuPressure;
    @Value("${added.workload.ram}")
    private int memPressureMb;


    // make a payment
    @PostMapping("/dynapay")
    public DynaPay createPayment(@RequestBody DynaPay dynaPay) {
        simulateHardWork();

        double rand = Math.random();

        if (rand >= 0.33) {
            // successful payment
            dynaPay.setSucceeded(true);
            dynaPay.setMessage("OK");
        } else {
            dynaPay.setSucceeded(false);
            dynaPay.setMessage("Failed");
        }

        return dynaPay;
    }

    private void simulateHardWork() {
        int arraySize = (int)((long)this.memPressureMb * 1024L * 1024L / 8L);
        if (arraySize < 0) {
            arraySize = Integer.MAX_VALUE;
        }
        long[] longs = new long[arraySize];
        int j = 0;
        for(long i = 0; i < this.cpuPressure; i++, j++) {
            j++;
            if (j >= arraySize) {
                j = 0;
            }
            try {
                if (longs[j] > Integer.MAX_VALUE) {
                    longs[j] = (long) Integer.MIN_VALUE;
                }
            } catch (Exception ignored) {};
        }
    }
}
