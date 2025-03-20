package hse.finance.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.finance.domain.Operation;
import hse.finance.interfaces.IDataExporter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDataExporter implements IDataExporter {
    private String filePath;
    private List<Operation> operations;

    public JsonDataExporter(String filePath) {
        this.filePath = filePath;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public void exportData(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), operations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}