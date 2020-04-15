package by.epam.dobrov.task3.main;

import by.epam.dobrov.task3.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        Car firstCar = new Car(exchanger);
        Car secondCar = new Car(exchanger);
        Car thirdCar = new Car(exchanger);
        Car fourthCar = new Car(exchanger);
        Car fifthCar = new Car(exchanger);
        Car sixthCar = new Car(exchanger);

        firstCar.start();
        secondCar.start();
        thirdCar.start();
        fourthCar.start();
        fifthCar.start();
        sixthCar.start();

        try {
            firstCar.join();
            secondCar.join();
            thirdCar.join();
            fourthCar.join();
            fifthCar.join();
            sixthCar.join();

        } catch (InterruptedException e) {
            LOGGER.warn(String.valueOf(e));
            Thread.currentThread().interrupt();
        }

    }

}
