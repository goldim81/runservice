package ru.goldim.runservice;

import ru.goldim.runservice.Service.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class ServiceRunner {

    private Set<Service> services = new HashSet<>();
    private Set<Service> runningService = new HashSet<>();

    public void registerService(Service service) {
        services.add(service);
    }

    public void runServices() {
        do {
            for (Iterator<Service> iterator = services.iterator(); iterator.hasNext(); ) {
                Service s = iterator.next();
                if (s.getDependsOn().size() == 0) {
                    runService(iterator, s);
                } else {
                    Set<Class<? extends Service>> runningClasses = runningService.stream().map(Service::getClass).collect(Collectors.toSet());
                    if (runningClasses.containsAll(s.getDependsOn())) {
                        runService(iterator, s);
                    }
                }
            }
        } while (services.size() != 0);
    }

    private void runService(Iterator<Service> iterator, Service s) {
        s.run();
        iterator.remove();
        runningService.add(s);
    }

}
