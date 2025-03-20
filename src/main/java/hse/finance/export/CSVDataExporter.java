package hse.finance.export;

import hse.finance.domain.Operation;
import hse.finance.interfaces.IDataExporter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CSVDataExporter implements IDataExporter {
    private String filePath;
    private List<Operation> operations;

    public CSVDataExporter(String filePath) {
        this.filePath = filePath;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public void exportData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Запись заголовка
            writer.write("ID,Type,BankAccountID,Amount,Date,Description,CategoryID");
            writer.newLine();

            // Запись данных
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Operation operation : operations) {
                writer.write(String.format("%d,%s,%d,%.2f,%s,%s,%d",
                        operation.getId(),
                        operation.getType(),
                        operation.getBankAccount().getId(),
                        operation.getAmount(),
                        dateFormat.format(operation.getDate()),
                        operation.getDescription(),
                        operation.getCategory().getId()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}