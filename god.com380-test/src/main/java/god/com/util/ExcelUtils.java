package god.com.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.excel.util.EgovExcelUtil;

public class ExcelUtils {

	protected static Logger egovLogger = LoggerFactory.getLogger(ExcelUtils.class);

	public static List<String> getSheetNames(Workbook wb) {
		List<String> sheetNames = new ArrayList<>();

		int numberOfSheets = wb.getNumberOfSheets();
		egovLogger.debug("numberOfSheets: {}", numberOfSheets);

		for (int index = 0; index < numberOfSheets; index++) {
			Sheet sheet = wb.getSheetAt(index);
			egovLogger.debug("sheet: {}", sheet);

			String sheetName = sheet.getSheetName();

			egovLogger.debug("sheetName: {}", sheetName);

			sheetNames.add(sheetName);
		}

		egovLogger.debug("sheetNames: {}", sheetNames);

		return sheetNames;
	}

	public static List<Map<String, String>> getValue(Workbook wb, int sheetIndex) {
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

	public static Map<Integer, List<Map<String, String>>> getValue(Workbook wb) {
		Map<Integer, List<Map<String, String>>> excel = new LinkedHashMap<>();

		int numberOfSheets = wb.getNumberOfSheets();
		egovLogger.debug("numberOfSheets: {}", numberOfSheets);

		for (int i = 0; i < numberOfSheets; i++) {
			excel.put(i, getValue(wb, i));
		}

		return excel;
	}

}
