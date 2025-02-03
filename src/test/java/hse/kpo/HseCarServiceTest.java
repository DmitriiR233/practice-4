package hse.kpo;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import hse.kpo.domains.Customer;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class HseCarServiceTest {

    @Test
    @DisplayName("При отсутствии совместимых машин, клиент не получает автомобиль")
    void sellCars_WhenNoCompatibleCars_ShouldNotAssignCarTest() {
        // Arrange
        CustomerStorage spyCustomerStorage = spy(new CustomerStorage());
        CarService mockCarService = mock(CarService.class);
        HseCarService hseCarService = new HseCarService(mockCarService, spyCustomerStorage);

        Customer customer = new Customer("Nikita", 4, 4); // Несовместим ни с какими двигателями
        spyCustomerStorage.addCustomer(customer);

        // Act
        hseCarService.sellCars();

        // Assert
        assertNull(customer.getCar());
        verify(spyCustomerStorage, times(1)).getCustomers();
    }
}