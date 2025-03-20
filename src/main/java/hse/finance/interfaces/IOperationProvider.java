package hse.finance.interfaces;

import hse.finance.domain.Operation;

public interface IOperationProvider {
    Operation getOperationById(Long id);
}
