package ru.goldim.runservice.Service;

import java.util.Set;

public interface Service {
    Set<Class<? extends Service>> getDependsOn();

    void run();

}
