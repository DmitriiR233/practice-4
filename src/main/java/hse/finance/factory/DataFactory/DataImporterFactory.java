package hse.finance.factory.DataFactory;

import hse.finance.imports.CSVDataImporter;
import hse.finance.imports.JsonDataImporter;
import hse.finance.interfaces.IDataImporter;
import hse.finance.report.ReportFormat;

public class DataImporterFactory {
    public IDataImporter createDataImporter(ReportFormat format, String filePath) {
        switch (format) {
            case CSV:
                return new CSVDataImporter(filePath);
            case JSON:
                return new JsonDataImporter(filePath);
            default:
                throw new IllegalArgumentException("Unsupported format");
        }
    }
}
