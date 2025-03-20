package hse.finance.service;

import hse.finance.domain.Operation;
import hse.finance.interfaces.IOperationObservers;
import hse.finance.interfaces.IOperationProvider;

import java.util.ArrayList;
import java.util.List;

public class OperationService implements IOperationProvider {
    private List<Operation> operations = new ArrayList<>();
    private List<IOperationObservers> observers = new ArrayList<>();

    public Operation createOperation(Operation operation) {
        operations.add(operation);
        notifyObservers(operation);
        return operation;
    }

    public Operation updateOperation(Operation operation) {
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).getId().equals(operation.getId())) {
                operations.set(i, operation);
                notifyObservers(operation);
                return operation;
            }
        }
        return null;
    }

    public void deleteOperation(Long id) {
        operations.removeIf(operation -> operation.getId().equals(id));
    }

    public List<Operation> getAllOperations() {
        return operations;
    }

    @Override
    public Operation getOperationById(Long id) {
        return operations.stream().filter(operation -> operation.getId().equals(id)).findFirst().orElse(null);
    }

    public void addObserver(IOperationObservers observer) {
        observers.add(observer);
    }

    public void removeObserver(IOperationObservers observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Operation operation) {
        for (IOperationObservers observer : observers) {
            observer.update(operation);
        }
    }
}
