package by.epam.dobrov.task3.entity;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */


public class ParkingPlace {

    private boolean free;
    private int carId;

    public ParkingPlace() {

        this.free = true;
        this.carId = 0;
    }

    public boolean isFree() {

        return free;
    }

    /**
     * Place occupied
     *
     * @param carId
     */
    public void setBusy(int carId) {

        if (checkCarId(carId)) {
            this.carId = carId;
            this.free = false;
        }
    }

    public boolean checkCarId(int carId) {
        if (carId < 1) {
            return false;
        }
        return true;
    }

    /**
     * vacate the park place
     */
    void setFree() {

        this.free = true;
        this.carId = 0;
    }
}
