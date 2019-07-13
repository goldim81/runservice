package ru.goldim.runservice;

import ru.goldim.runservice.Service.Service1;
import ru.goldim.runservice.Service.Service2;
import ru.goldim.runservice.Service.Service3;

public class Main {
    public static void main(String[] args) {
        ServiceRunner serviceRunner = new ServiceRunner();
        serviceRunner.registerService(new Service1());
        serviceRunner.registerService(new Service2());
        serviceRunner.registerService(new Service3());

        serviceRunner.runServices();
    }
}
