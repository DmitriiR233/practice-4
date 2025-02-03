package hse.kpo;

import static org.junit.jupiter.api.Assertions.*;

import hse.kpo.domains.Car;
import hse.kpo.factories.LevitateCarFactory;
import hse.kpo.params.LevitateEngineParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LevitateCarFactoryTest {

    @Autowired
    private LevitateCarFactory levitateCarFactory;

    @Test
    @DisplayName("При создании левитирующей машины, VIN должен соответствовать переданному")
    void createCar_WithValidParams_ShouldReturnLevitateCarTest() {
        Car car = levitateCarFactory.createCar(new LevitateEngineParams(10), 3);
        assertEquals(3, car.getVIN());
        assertEquals("LevitateEngine", car.getEngine().getClass().getSimpleName());
    }
}