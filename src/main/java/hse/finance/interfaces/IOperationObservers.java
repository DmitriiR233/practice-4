package hse.finance.interfaces;

import hse.finance.domain.Operation;

public interface IOperationObservers {
    void update(Operation operation);
}
