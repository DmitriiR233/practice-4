package hse.finance.report;

import hse.finance.domain.Operation;
import hse.finance.export.CSVDataExporter;
import hse.finance.export.JsonDataExporter;
import hse.finance.factory.DataFactory.DataExporterFactory;
import hse.finance.interfaces.IDataExporter;
import hse.finance.service.OperationService;

import java.util.List;

public class ReportBuilder {
    private OperationService operationService;
    private DataExporterFactory dataExporterFactory;

    public ReportBuilder(OperationService operationService, DataExporterFactory dataExporterFactory) {
        this.operationService = operationService;
        this.dataExporterFactory = dataExporterFactory;
    }

    public void buildReport(ReportFormat format, String filePath) {
        List<Operation> operations = operationService.getAllOperations();
        IDataExporter dataExporter = dataExporterFactory.createDataExporter(format, filePath);
        if (dataExporter instanceof CSVDataExporter) {
            ((CSVDataExporter) dataExporter).setOperations(operations);
        } else if (dataExporter instanceof JsonDataExporter) {
            ((JsonDataExporter) dataExporter).setOperations(operations);
        }
        dataExporter.exportData(filePath);
    }
}
