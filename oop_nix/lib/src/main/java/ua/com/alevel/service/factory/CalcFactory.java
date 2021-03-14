package ua.com.alevel.service.factory;

import org.reflections.Reflections;
import ua.com.alevel.service.CalcService;
import ua.com.alevel.service.impl.NewVersionDefaultCalcService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CalcFactory {

    private static CalcFactory instance;
    private Reflections reflections;
    private Set<Class<? extends CalcService>> calcServices;
    private Map<Class<? extends CalcService>, Object> maps = new ConcurrentHashMap<>();

    private CalcFactory() {
        reflections = new Reflections("ua.com.alevel");
        calcServices = reflections.getSubTypesOf(CalcService.class);
    }

    public static CalcFactory getInstance() {
        if (instance == null) {
            instance = new CalcFactory();
        }
        return instance;
    }

    public CalcService getCalcService() {
        for (Class<? extends CalcService> calcService : calcServices) {
            if (!calcService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calcService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Idiot");
                }
            }
        }
        throw new RuntimeException("Idiot");
    }
}
