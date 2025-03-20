package hse.finance.imports;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hse.finance.domain.Operation;
import hse.finance.interfaces.IDataImporter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDataImporter implements IDataImporter {
    private String filePath;
    private List<Operation> operations;

    public JsonDataImporter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void importData(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            operations = objectMapper.readValue(new File(filePath), new TypeReference<List<Operation>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Operation> getOperations() {
        return operations;
    }
}
