package ru.goldim.runservice.service;

import ru.goldim.runservice.annotations.DependsOn;

@DependsOn({"Service2"})
public class Service1 implements Service {

    @Override
    public void run() {
        System.out.println("Запущен сервис 1");
    }
}
