package by.epam.dobrov.task3;

import by.epam.dobrov.task3.entity.Parking;
import by.epam.dobrov.task3.entity.ParkingPlace;
import by.epam.dobrov.task3.exception.WrongParkingIndexException;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingTest {

    private Parking parking;

    @Before
    public void createParking() {
        parking = new Parking();

        List<ParkingPlace> parkingPlaceList = new ArrayList<ParkingPlace>();

        int PARKING_PLACE_COUNT = 4;
        for (int i = 0; i < PARKING_PLACE_COUNT; i++) {

        }
    }

    @Test
    public void newParkingPlace_ShouldHaveFreeParkingPlace() {

        int PARKING_PLACE_COUNT = 4;
        for (int i = 0; i < PARKING_PLACE_COUNT; i++) {
            try {
                parking.isFree(i);
            } catch (WrongParkingIndexException e) {
                e.printStackTrace();
            }
        }
    }


    @Test(expected = WrongParkingIndexException.class)
    public void newParkingPlace_ShouldNotExist() throws WrongParkingIndexException {

        int parkingPlace = 5;

        parking.isFree(parkingPlace);

    }
}
