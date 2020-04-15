package by.epam.dobrov.task3.entity;

import by.epam.dobrov.task3.exception.WrongParkingIndexException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */

public class Parking {

    private static final int PARKING_PLACE_COUNT = 4;
    private static Parking instance;
    private static Lock lock = new ReentrantLock(true);
    private static AtomicBoolean created = new AtomicBoolean(false);

    private List<ParkingPlace> parkingPlaceList;

    public Parking() {

        parkingPlaceList = new ArrayList<ParkingPlace>();

        for (int i = 0; i < PARKING_PLACE_COUNT; i++) {
            parkingPlaceList.add(new ParkingPlace());
        }
    }

    public static Parking getInstance() {

        if (!created.get()) {

            lock.lock();
            try {

                if (instance == null) {
                    instance = new Parking();
                    created.set(true);
                }

            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * get the quantity of parking places
     *
     * @return
     */
    public static int getParkingPlaceCount() {

        return PARKING_PLACE_COUNT;
    }

    /**
     * check if parking space is free
     *
     * @param i parking place
     * @return true/false
     * @throws WrongParkingIndexException
     */
    public boolean isFree(int i) throws WrongParkingIndexException {

        if ((i < PARKING_PLACE_COUNT) && (i >= 0)) {
            return parkingPlaceList.get(i).isFree();
        } else {
            throw new WrongParkingIndexException("Wrong index i = " + i);
        }
    }

    /**
     * occupied park place
     *
     * @param i  park place
     * @param carId car number
     */
    public void takeParkingPlace(int i, int carId) {

        parkingPlaceList.get(i).setBusy(carId);

    }

    /**
     * make parking place free
     *
     * @param i park place
     */
    public void freeParkingPlace(int i) {

        parkingPlaceList.get(i).setFree();
    }
}
