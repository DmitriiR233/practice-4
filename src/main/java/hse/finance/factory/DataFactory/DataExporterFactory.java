package hse.finance.factory.DataFactory;

import hse.finance.export.CSVDataExporter;
import hse.finance.export.JsonDataExporter;
import hse.finance.interfaces.IDataExporter;
import hse.finance.report.ReportFormat;

public class DataExporterFactory {
    public IDataExporter createDataExporter(ReportFormat format, String filePath) {
        switch (format) {
            case CSV:
                return new CSVDataExporter(filePath);
            case JSON:
                return new JsonDataExporter(filePath);
            default:
                throw new IllegalArgumentException("Unsupported format");
        }
    }
}
