package god.codegen;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.dev.imp.codegen.template.model.Entity;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceImpl;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

public class GodExcel {

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	public List<DataModelContext> a() {
		List<DataModelContext> dataModels = new ArrayList<>();

		String templateName = "D:\\Users\\LeeBaekHaeng\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-TABLES.xlsx";
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFWorkbook wb2 = loadExcelTemplate(templateName, wb);
		b(wb2, dataModels);

		templateName = "D:\\Users\\LeeBaekHaeng\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-COLUMNS.xlsx";
		wb = new XSSFWorkbook();
		wb2 = loadExcelTemplate(templateName, wb);
		c(wb2, dataModels);

		return dataModels;
	}

	private void b(XSSFWorkbook wb, List<DataModelContext> dataModels) {
		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			String a = EgovExcelUtil.getValue(row.getCell(0));
			String b = EgovExcelUtil.getValue(row.getCell(1));
			String c = EgovExcelUtil.getValue(row.getCell(2));
			System.out.println("a: " + a);
			System.out.println("b: " + b);
			System.out.println("c: " + c);

			DataModelContext dataModel = new DataModelContext();

			dataModel.setPackageName("pkg");
			dataModel.setAuthor("이백행");
			dataModel.setTeam("갓소프트");
			dataModel.setCreateDate("2009.02.01");

			dataModel.setVender("MySql");

			Entity entity = new Entity(c);

			dataModel.setEntity(entity);

			dataModels.add(dataModel);
		}
	}

	private void c(XSSFWorkbook wb, List<DataModelContext> dataModels) {
		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			String a = EgovExcelUtil.getValue(row.getCell(0));
			String b = EgovExcelUtil.getValue(row.getCell(1));
			String c = EgovExcelUtil.getValue(row.getCell(2));
			String d = EgovExcelUtil.getValue(row.getCell(3));
			String h = EgovExcelUtil.getValue(row.getCell(7));
			System.out.println("a: " + a);
			System.out.println("b: " + b);
			System.out.println("c: " + c);
			System.out.println("d: " + d);
			System.out.println("h: " + h);
		}
	}

	private XSSFWorkbook loadExcelTemplate(String templateName, XSSFWorkbook wb) {
		try {
			return egovExcelService.loadExcelTemplate(templateName, wb);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

}
