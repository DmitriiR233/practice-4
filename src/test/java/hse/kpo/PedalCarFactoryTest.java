package hse.kpo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.PedalEngineParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PedalCarFactoryTest {

    @Autowired
    private PedalCarFactory pedalCarFactory;

    @Test
    @DisplayName("При передаче отрицательного размера педали должно выбрасываться исключение")
    void createCar_WithNegativePedalSize_ShouldThrowExceptionTest() { //провальный сценарии
        assertThrows(IllegalArgumentException.class,
                () -> pedalCarFactory.createCar(new PedalEngineParams(-5), 1));
    }
}