package ru.goldim.runservice.service;

import ru.goldim.runservice.annotations.DependsOn;

@DependsOn({"Service1", "Service2"})
public class Service3 implements Service {

    @Override
    public void run() {
        System.out.println("Запущен сервис 3");
    }
}
