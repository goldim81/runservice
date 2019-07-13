package ru.goldim.runservice.Service;

import java.util.HashSet;
import java.util.Set;

public class Service2 implements Service {
    private final Set<Class<? extends Service>> dependsOn = new HashSet<>();

    public Set<Class<? extends Service>> getDependsOn() {
        return dependsOn;
    }

    @Override
    public void run() {
        System.out.println("Запущен сервис 2");
    }
}
