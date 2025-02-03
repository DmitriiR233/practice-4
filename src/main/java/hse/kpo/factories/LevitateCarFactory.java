package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.LevitateEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.LevitateEngineParams;
import org.springframework.stereotype.Component;

@Component
public class LevitateCarFactory implements ICarFactory<LevitateEngineParams> {
    @Override
    public Car createCar(LevitateEngineParams params, int carNumber) {
        return new Car(carNumber, new LevitateEngine());
    }
}