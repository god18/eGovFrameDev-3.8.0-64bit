package egovframework.rte.fdl.excel.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.dev.imp.codegen.template.model.Entity;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import god.com.util.ExcelUtils;

public class EgovExcelServiceImplLoadExcelTemplateTest {

	protected Logger egovLogger = LoggerFactory.getLogger(EgovExcelServiceImplLoadExcelTemplateTest.class);

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@Test
	public void test() {
		String templateName = "C:\\Users\\godsoft\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-TABLES.xls";
		Workbook wb = null;
		try {
			wb = egovExcelService.loadExcelTemplate(templateName);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
			return;
		}

		egovLogger.debug("wb: {}", wb);

		// a(wb);
		// b(wb);
		// List<Map<String, String>> items = getItems(wb, 0);
		// c(items);
		// d(wb);
		// ExcelUtils.getSheetNames(wb);
		// ExcelUtils.getValue(wb, 2);
		ExcelUtils.getValue(wb);
	}

	void a(Workbook wb) {
		int activeSheetIndex = wb.getActiveSheetIndex();
		egovLogger.debug("activeSheetIndex: {}", activeSheetIndex);

		int numberOfSheets = wb.getNumberOfSheets();
		egovLogger.debug("numberOfSheets: {}", numberOfSheets);

		for (int index = 0; index < numberOfSheets; index++) {
			Sheet sheet = wb.getSheetAt(index);
			egovLogger.debug("sheet: {}", sheet);
			egovLogger.debug("getSheetName: {}", sheet.getSheetName());
		}
	}

	void b(Workbook wb) {
		int numberOfSheets = wb.getNumberOfSheets();
		egovLogger.debug("numberOfSheets: {}", numberOfSheets);

		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = wb.getSheetAt(i);
			egovLogger.debug("sheet: {}", sheet);

			for (Row row : sheet) {
				int rowNum = row.getRowNum();
				egovLogger.debug("rowNum: {}", rowNum);
				for (Cell cell : row) {
					egovLogger.debug("cell: {}", cell);
				}
			}
		}
	}

	List<Map<String, String>> getItems(Workbook wb, int sheetIndex) {
		List<Map<String, String>> items = new ArrayList<>();

		Map<Integer, String> key = new LinkedHashMap<>();

		Sheet sheet = wb.getSheetAt(sheetIndex);
		egovLogger.debug("sheet: {}", sheet);

		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			egovLogger.debug("rowNum: {}", rowNum);

			Map<String, String> item = new LinkedHashMap<>();

			for (Cell cell : row) {
				egovLogger.debug("cell: {}", cell);

				int columnIndex = cell.getColumnIndex();

				if (rowNum == 0) {
					key.put(columnIndex, EgovExcelUtil.getValue(cell));
				}

				item.put(key.get(columnIndex), EgovExcelUtil.getValue(cell));
			}

			items.add(item);
		}

		egovLogger.debug("key: {}", key);
		egovLogger.debug("items: {}", items);

		return items;
	}

	void c(List<Map<String, String>> items) {
		for (Map<String, String> item : items) {
			egovLogger.debug("item: {}", item);
			for (String key : item.keySet()) {
				egovLogger.debug("key: {}", item.get(key));
			}

			Entity tableName = new Entity(item.get("TABLE_NAME"));
			egovLogger.debug("tableName: {}", tableName);
			egovLogger.debug("getName: {}", tableName.getName());
			egovLogger.debug("getUcName: {}", tableName.getUcName());
			egovLogger.debug("getLcName: {}", tableName.getLcName());
			egovLogger.debug("getCcName: {}", tableName.getCcName());
			egovLogger.debug("getPcName: {}", tableName.getPcName());
		}
	}

	Map<String, List<Map<String, String>>> getExcel(Workbook wb) {
		Map<String, List<Map<String, String>>> excel = new LinkedHashMap<>();

		int numberOfSheets = wb.getNumberOfSheets();
		egovLogger.debug("numberOfSheets: {}", numberOfSheets);

		for (int i = 0; i < numberOfSheets; i++) {
			excel.put(wb.getSheetName(i), getItems(wb, i));
		}

		return excel;
	}

	Map<Integer, List<Map<String, String>>> getExcel2(Workbook wb) {
		Map<Integer, List<Map<String, String>>> excel = new LinkedHashMap<>();

		int numberOfSheets = wb.getNumberOfSheets();
		egovLogger.debug("numberOfSheets: {}", numberOfSheets);

		for (int i = 0; i < numberOfSheets; i++) {
			excel.put(i, getItems(wb, i));
		}

		return excel;
	}

	void d(Workbook wb) {
		Map<String, List<Map<String, String>>> excel = getExcel(wb);
		egovLogger.debug("excel: {}", excel);

		Map<Integer, List<Map<String, String>>> excel2 = getExcel2(wb);
		egovLogger.debug("excel2: {}", excel2);
	}

}
