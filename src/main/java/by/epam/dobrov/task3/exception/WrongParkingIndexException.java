package by.epam.dobrov.task3.exception;

/**
 * 3.	Автостоянка. На стоянке доступно несколько машиномест. На одном месте может находиться только один автомобиль.
 * Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет.
 * Припаркованные рядом автомобили могут поменяться своими парковочными местами
 */

public class WrongParkingIndexException extends Exception {
    public WrongParkingIndexException() {
    }

    public WrongParkingIndexException(String s) {
        super(s);
    }

    public WrongParkingIndexException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongParkingIndexException(Throwable throwable) {
        super(throwable);
    }
}
