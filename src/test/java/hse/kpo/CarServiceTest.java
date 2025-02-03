package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.PedalEngine;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Test
    @DisplayName("После добавления автомобиля он доступен для выдачи клиенту")
    void addCar_CustomerTest() {
        CarService carService = new CarService();
        Customer compatibleCustomer = new Customer("Ivan", 10, 5);

        PedalCarFactory factory = new PedalCarFactory();
        carService.addCar(factory, new PedalEngineParams(10));

        Car car = carService.takeCar(compatibleCustomer);

        assertNotNull(car);
        assertEquals(1, car.getVIN());
    }

    @Test
    @DisplayName("После выдачи автомобиля клиенту он больше недоступен")
    void takeCar_Test() {

        CarService carService = new CarService();
        Customer customer = new Customer("Maria", 8, 6);
        carService.addCar(new PedalCarFactory(), new PedalEngineParams(10));

        Car firstCall = carService.takeCar(customer);
        Car secondCall = carService.takeCar(customer);

        assertNotNull(firstCall);
        assertNull(secondCall);
    }

    @Test
    @DisplayName("При добавлении автомобиля через фабрику, он имеет правильный VIN")
    void addCar_Test() {

        CarService carService = new CarService();
        Customer customer = new Customer("Alex", 7, 7);

        carService.addCar(new PedalCarFactory(), new PedalEngineParams(10));
        carService.addCar(new HandCarFactory(), EmptyEngineParams.DEFAULT);

        Car firstCar = carService.takeCar(customer);
        Car secondCar = carService.takeCar(customer);

        assertEquals(1, firstCar.getVIN());
        assertEquals(2, secondCar.getVIN());
    }

    @Test
    @DisplayName("Метод addCar должен вызывать createCar у фабрики")
    void addCar_FactoryMethodTest() {

        ICarFactory<PedalEngineParams> mockFactory = mock(PedalCarFactory.class);
        CarService carService = new CarService();
        when(mockFactory.createCar(any(), anyInt())).thenReturn(new Car(1, new PedalEngine(10)));

        carService.addCar(mockFactory, new PedalEngineParams(10));

        verify(mockFactory, times(1)).createCar(any(), eq(1));
    }
}