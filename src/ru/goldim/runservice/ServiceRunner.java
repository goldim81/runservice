package ru.goldim.runservice;

import ru.goldim.runservice.annotations.DependsOn;
import ru.goldim.runservice.service.Service;

import java.util.*;
import java.util.stream.Collectors;

public class ServiceRunner {

    private Set<Service> services = new HashSet<>();
    private Set<Service> runningService = new HashSet<>();

    public void registerService(Service service) {
        services.add(service);
    }

    public void runServices() {
        int count = services.size();
        do {
            for (Iterator<Service> iterator = services.iterator(); iterator.hasNext(); ) {
                Service s = iterator.next();
                Class<?> clazz = s.getClass();
                if (!clazz.isAnnotationPresent(DependsOn.class) || clazz.getDeclaredAnnotation(DependsOn.class).value().length==0) {
                    runService(iterator, s);
                } else {
                    List<String> values = Arrays.asList(clazz.getDeclaredAnnotation(DependsOn.class).value());
                    if (values.contains(clazz.getSimpleName())) {
                        throw new IllegalStateException("Неверно указана зависимость. Класс "+clazz.getSimpleName()+" сылается на себя");
                    }
                    Set<String> runningClasses = runningService.stream().map(service -> service.getClass().getSimpleName()).collect(Collectors.toSet());
                    if (runningClasses.containsAll(values)) {
                        runService(iterator, s);
                    }
                }
            }
            System.out.println(count--);
        } while (services.size() != 0 && count > 0);

        if (count==0 && services.size() > 0 ) {
            throw new IllegalStateException("Что то пошло не так. Скорее всего были перекрестные ссылки.");
        }
    }

    private void runService(Iterator<Service> iterator, Service s) {
        s.run();
        iterator.remove();
        runningService.add(s);
    }

}
