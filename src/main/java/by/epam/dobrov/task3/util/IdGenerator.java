package by.epam.dobrov.task3.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */

public class IdGenerator {

    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private IdGenerator() {
    }

    public static int generateId() {
        return idCounter.incrementAndGet();
    }

    public static int getId() {
        generateId();
        return idCounter.get();
    }
}
