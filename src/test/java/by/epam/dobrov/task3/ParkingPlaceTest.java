package by.epam.dobrov.task3;

import by.epam.dobrov.task3.entity.ParkingPlace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingPlaceTest {

    private ParkingPlace parkingPlace;

    @Before
    public void createNewParkingPlace() {

        parkingPlace = new ParkingPlace();
    }

    @Test
    public void newCarID_ShouldBeMoreThanZero() {

        int carId = 1;

        Assert.assertTrue(parkingPlace.checkCarId(carId));
    }

    @Test
    public void newCarID_ShouldBeLessThanOne() {

        int carId = 0;

        Assert.assertFalse(parkingPlace.checkCarId(carId));
    }


}
