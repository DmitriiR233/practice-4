package hse.kpo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngine;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HandCarFactoryTest {

    @Autowired
    private HandCarFactory handCarFactory;

    @Test
    @DisplayName("При передаче пустых параметров и номера 1 должна создаваться машина с HandEngine и VIN=1")
    void createCar_WithEmptyParamsAndVIN1_ShouldReturnHandEngineCarTest() {
        Car car = handCarFactory.createCar(EmptyEngineParams.DEFAULT, 1);
        assertEquals(1, car.getVIN());
        assertEquals(HandEngine.class, car.getEngine().getClass());
    }
}