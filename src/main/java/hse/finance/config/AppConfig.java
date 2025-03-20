package hse.finance.config;

import hse.finance.factory.BankAccountFactory.BankAccountFactory;
import hse.finance.factory.CategoryFactory;
import hse.finance.factory.DataFactory.DataExporterFactory;
import hse.finance.factory.OperationFactory;
import hse.finance.service.BankAccountService;
import hse.finance.service.CategoryService;
import hse.finance.service.OperationService;
import hse.finance.report.ReportBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BankAccountFactory bankAccountFactory() {
        return new BankAccountFactory();
    }

    @Bean
    public CategoryFactory categoryFactory() {
        return new CategoryFactory();
    }

    @Bean
    public OperationFactory operationFactory() {
        return new OperationFactory();
    }

    @Bean
    public DataExporterFactory dataExporterFactory() {
        return new DataExporterFactory();
    }

    @Bean
    public BankAccountService bankAccountService() {
        return new BankAccountService();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryService();
    }

    @Bean
    public OperationService operationService() {
        return new OperationService();
    }

    @Bean
    public ReportBuilder reportBuilder(OperationService operationService, DataExporterFactory dataExporterFactory) {
        return new ReportBuilder(operationService, dataExporterFactory);
    }
}
