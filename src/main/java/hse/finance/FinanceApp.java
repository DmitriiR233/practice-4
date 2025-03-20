package hse.finance;

import hse.finance.domain.BankAccount;
import hse.finance.domain.Category;
import hse.finance.domain.Operation;
import hse.finance.export.CSVDataExporter;
import hse.finance.imports.CSVDataImporter;
import hse.finance.export.JsonDataExporter;
import hse.finance.imports.JsonDataImporter;
import hse.finance.factory.BankAccountFactory.BankAccountFactory;
import hse.finance.factory.CategoryFactory;
import hse.finance.factory.OperationFactory;
import hse.finance.service.BankAccountService;
import hse.finance.service.CategoryService;
import hse.finance.service.OperationService;
import hse.finance.report.ReportBuilder;
import hse.finance.report.ReportFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FinanceApp implements CommandLineRunner {

	@Autowired
	private BankAccountFactory bankAccountFactory;

	@Autowired
	private CategoryFactory categoryFactory;

	@Autowired
	private OperationFactory operationFactory;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private ReportBuilder reportBuilder;

	public static void main(String[] args) {
		SpringApplication.run(FinanceApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Создание объектов
		BankAccount mainAccount = bankAccountFactory.createBankAccount("Основной счет", 1000.0);
		BankAccount savingsAccount = bankAccountFactory.createBankAccount("Сберегательный счет", 5000.0);

		Category salaryCategory = categoryFactory.createCategory("Доход", "Зарплата");
		Category rentCategory = categoryFactory.createCategory("Расход", "Аренда");

		Operation salaryOperation = operationFactory.createOperation("Доход", mainAccount, 3000.0, new Date(), "Зарплата за месяц", salaryCategory);
		Operation rentOperation = operationFactory.createOperation("Расход", mainAccount, 1500.0, new Date(), "Плата за аренду", rentCategory);

		// Добавление объектов в сервисы
		bankAccountService.createBankAccount(mainAccount);
		bankAccountService.createBankAccount(savingsAccount);

		categoryService.createCategory(salaryCategory);
		categoryService.createCategory(rentCategory);

		operationService.createOperation(salaryOperation);
		operationService.createOperation(rentOperation);

		// Экспорт данных в CSV
		CSVDataExporter csvDataExporter = new CSVDataExporter("operations.csv");
		csvDataExporter.setOperations(operationService.getAllOperations());
		csvDataExporter.exportData("operations.csv");

		// Экспорт данных в JSON
		JsonDataExporter jsonDataExporter = new JsonDataExporter("operations.json");
		jsonDataExporter.setOperations(operationService.getAllOperations());
		jsonDataExporter.exportData("operations.json");

		// Импорт данных из CSV
		CSVDataImporter csvDataImporter = new CSVDataImporter("operations.csv");
		csvDataImporter.importData("operations.csv");
		List<Operation> importedOperationsFromCSV = csvDataImporter.getOperations();

		// Импорт данных из JSON
		JsonDataImporter jsonDataImporter = new JsonDataImporter("operations.json");
		jsonDataImporter.importData("operations.json");
		List<Operation> importedOperationsFromJSON = jsonDataImporter.getOperations();

		// Вывод импортированных данных
		System.out.println("Импортированные операции из CSV:");
		for (Operation importedOperation : importedOperationsFromCSV) {
			System.out.println(importedOperation);
		}

		System.out.println("Импортированные операции из JSON:");
		for (Operation importedOperation : importedOperationsFromJSON) {
			System.out.println(importedOperation);
		}

		// Создание отчета
		reportBuilder.buildReport(ReportFormat.CSV, "report.csv");
		reportBuilder.buildReport(ReportFormat.JSON, "report.json");
	}
}