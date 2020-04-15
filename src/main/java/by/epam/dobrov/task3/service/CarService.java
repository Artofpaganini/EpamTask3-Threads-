package by.epam.dobrov.task3.service;

import by.epam.dobrov.task3.entity.Parking;
import by.epam.dobrov.task3.exception.WrongParkingIndexException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */

public class CarService extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private static Lock lock = new ReentrantLock(true);

    /**
     * Car took place
     *
     * @param carId
     * @return
     */
    public int park(int carId) {

        Parking parking = Parking.getInstance();
        lock.lock();

        try {
            for (int i = 0; i < Parking.getParkingPlaceCount(); i++) {

                if (parking.isFree(i)) {
                    parking.takeParkingPlace(i, carId);
                    LOGGER.info("Car {} has taken place number {} ", carId, i);

                    return i;
                }

            }

        } catch (WrongParkingIndexException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            lock.unlock();
        }
        LOGGER.info("Car {} No free parking places available", carId);
        return -1;
    }

    /**
     * Сar has left parking place
     *
     * @param i
     * @param carId
     */
    public void leave(int i, int carId) {

        Parking.getInstance().freeParkingPlace(i);
        LOGGER.info("Car {} has left  place number {}", carId, i);
    }
}
