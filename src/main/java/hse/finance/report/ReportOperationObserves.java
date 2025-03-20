package hse.finance.report;

import hse.finance.domain.Operation;
import hse.finance.interfaces.IOperationObservers;

import java.util.ArrayList;
import java.util.List;

public class ReportOperationObserves implements IOperationObservers {
    private List<Operation> operations = new ArrayList<>();

    @Override
    public void update(Operation operation) {
        operations.add(operation);
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
