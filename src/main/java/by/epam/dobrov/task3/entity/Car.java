package by.epam.dobrov.task3.entity;


import by.epam.dobrov.task3.service.CarService;
import by.epam.dobrov.task3.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */

public class Car extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Car.class);

    public static final long CAR_WAITING_TIME = 5;

    private static Semaphore semaphore = new Semaphore(Parking.getParkingPlaceCount());
    private int carId;
    private Exchanger<Integer> exchanger;


    public Car(Exchanger<Integer> ex) {
        this.exchanger = ex;
        carId = IdGenerator.getId();
    }

    public int getCarId() {
        return carId;
    }

    @Override
    public void run() {

        try {
            if (semaphore.tryAcquire(Car.CAR_WAITING_TIME, TimeUnit.SECONDS)) {

                CarService carService = new CarService();
                int parkingPlaceId = carService.park(getCarId());

                parkingPlaceId = exchanger.exchange(parkingPlaceId);
                LOGGER.info("Car {} exchanged parking place and get new place {}", getCarId(), parkingPlaceId);

                Random random = new SecureRandom();
                int parkingTime = random.nextInt(10);
                LOGGER.info("Car {} will remain on place  about {} seconds", getCarId(), parkingTime);
                TimeUnit.SECONDS.sleep(parkingTime);

                if (parkingPlaceId != -1) {
                    carService.leave(parkingPlaceId, getCarId());
                } else {
                    LOGGER.warn("Car {} no parking place found", getCarId());
                }
                semaphore.release();
            } else {
                LOGGER.info("Car {} waiting timer expired. Leaves parking", getCarId());
            }
        } catch (InterruptedException e) {
            LOGGER.warn(String.valueOf(e));
            Thread.currentThread().interrupt();
        }
    }
}


