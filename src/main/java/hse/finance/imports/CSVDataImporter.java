package hse.finance.imports;

import hse.finance.domain.BankAccount;
import hse.finance.domain.Category;
import hse.finance.domain.Operation;
import hse.finance.interfaces.IDataImporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVDataImporter implements IDataImporter {
    private String filePath;
    private List<Operation> operations;

    public CSVDataImporter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void importData(String filePath) {
        List<Operation> operations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Пропускаем заголовок
                }
                String[] values = line.split(",");
                Long id = Long.parseLong(values[0]);
                String type = values[1];
                Long bankAccountId = Long.parseLong(values[2]);
                Double amount = Double.parseDouble(values[3]);
                java.util.Date date = dateFormat.parse(values[4]);
                String description = values[5];
                Long categoryId = Long.parseLong(values[6]);

                BankAccount bankAccount = new BankAccount(bankAccountId, "Imported Account", 0.0);
                Category category = new Category(categoryId, "Imported Category", "Imported Category");

                Operation operation = new Operation(id, type, bankAccount, amount, date, description, category);
                operations.add(operation);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.operations = operations;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}