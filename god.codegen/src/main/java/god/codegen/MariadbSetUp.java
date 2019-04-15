package god.codegen;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.dev.imp.codegen.template.model.Entity;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceImpl;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

public class MariadbSetUp implements SetUp {

	final static Logger logger = LoggerFactory.getLogger(MariadbSetUp.class);
	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@Override
	public List<DataModelContext> setUp(String templateName) {
		logger.debug("templateName: {}", templateName);

		Workbook wb = null;
		try {
			wb = egovExcelService.loadExcelTemplate(templateName, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

		DataModelContext dataModelContext = new DataModelContext();
		List<DataModelContext> dataModels = new ArrayList<>();

		getSheet1(wb, dataModelContext);
		getSheet2(wb, dataModelContext, dataModels);
		getSheet3(wb, dataModels);
		getSheet4(wb, dataModels);

		return dataModels;
	}

	private DataModelContext getSheet1(Workbook wb, DataModelContext dataModel) {
		Sheet sheet = wb.getSheetAt(0);
		logger.debug("sheet: {}", sheet);

		dataModel.setPackageName(EgovExcelUtil.getValue(sheet.getRow(0).getCell(1)));
		dataModel.setAuthor(EgovExcelUtil.getValue(sheet.getRow(1).getCell(1)));
		dataModel.setTeam(EgovExcelUtil.getValue(sheet.getRow(2).getCell(1)));
		dataModel.setCreateDate(EgovExcelUtil.getValue(sheet.getRow(3).getCell(1)));
		logger.debug("dataModel: {}", dataModel);
		logger.debug("getPackageName: {}", dataModel.getPackageName());
		logger.debug("getAuthor: {}", dataModel.getAuthor());
		logger.debug("getTeam: {}", dataModel.getTeam());
		logger.debug("getCreateDate: {}", dataModel.getCreateDate());

		return dataModel;
	}

	private void getSheet2(Workbook wb, DataModelContext dataModelContext, List<DataModelContext> dataModels) {
		Sheet sheet = wb.getSheetAt(1);
		logger.debug("sheet: {}", sheet);

		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			logger.debug("rowNum: {}", rowNum);

			if (rowNum == 0) {
				continue;
			}

			String b = EgovExcelUtil.getValue(row.getCell(1));
			String c = EgovExcelUtil.getValue(row.getCell(2));

			logger.debug("b: {}", b);
			logger.debug("c: {}", c);

			DataModelContext dataModel = new DataModelContext();
			BeanUtils.copyProperties(dataModelContext, dataModel);

			Entity entity = new Entity(c);
			dataModel.setEntity(entity);

			Tables tables = new Tables(c);
			tables.setTableSchema(b);
			tables.setTableName(c);
			dataModel.setTables(tables);

			dataModels.add(dataModel);
		}
	}

	private void getSheet3(Workbook wb, List<DataModelContext> dataModels) {
		Sheet sheet = wb.getSheetAt(2);
		logger.debug("sheet: {}", sheet);

		for (DataModelContext dataModel : dataModels) {
			for (Row row : sheet) {
				String b = EgovExcelUtil.getValue(row.getCell(1));
				String c = EgovExcelUtil.getValue(row.getCell(2));
				logger.debug("b: {}", b);
				logger.debug("c: {}", c);

				if (dataModel.getTables().getTableSchema().equals(b)
						&& dataModel.getTables().getTableName().equals(c)) {
					String u = EgovExcelUtil.getValue(row.getCell(20));
					logger.debug("u: {}", u);
					dataModel.getTables().setTableComment(u);
				}
			}
		}
	}

	private void getSheet4(Workbook wb, List<DataModelContext> dataModels) {
		Sheet sheet = wb.getSheetAt(3);
		logger.debug("sheet: {}", sheet);

		for (DataModelContext dataModel : dataModels) {
			List<Attribute> attributes = new ArrayList<Attribute>();
			List<Attribute> primaryKeys = new ArrayList<Attribute>();

			for (Row row : sheet) {
				String b = EgovExcelUtil.getValue(row.getCell(1));
				String c = EgovExcelUtil.getValue(row.getCell(2));
				logger.debug("b: {}", b);
				logger.debug("c: {}", c);

				if (dataModel.getTables().getTableSchema().equals(b)
						&& dataModel.getTables().getTableName().equals(c)) {
					String d = EgovExcelUtil.getValue(row.getCell(3));
					String h = EgovExcelUtil.getValue(row.getCell(7));
					logger.debug("d: {}", d);
					logger.debug("h: {}", h);

					Attribute attr = new Attribute(d);
					attr.setType(h);
					attr.setJavaType(GodUtils.getJavaType(h));
					attributes.add(attr);
					primaryKeys.add(attr);
				}
			}

			dataModel.setAttributes(attributes);
			dataModel.setPrimaryKeys(primaryKeys);
		}
	}

}
