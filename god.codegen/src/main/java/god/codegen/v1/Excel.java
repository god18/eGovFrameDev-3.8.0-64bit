package god.codegen.v1;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.dev.imp.codegen.template.model.Entity;
import egovframework.dev.imp.codegen.template.model.GodTable;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceImpl;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

public class Excel {

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	public void setDataModels(CodeGenVO vo, CodeGenModel model) {
		setWbTables(vo, model);
		setWbColumns(vo, model);
		b(vo, model);
	}

	private XSSFWorkbook loadExcelTemplate(String templateName, XSSFWorkbook type) {
		try {
			return egovExcelService.loadExcelTemplate(templateName, type);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void setWbTables(CodeGenVO vo, CodeGenModel model) {
		model.setWbTables(loadExcelTemplate(vo.getTemplateNameTables(), new XSSFWorkbook()));
	}

	public void setWbColumns(CodeGenVO vo, CodeGenModel model) {
		model.setWbColumns(loadExcelTemplate(vo.getTemplateNameColumns(), new XSSFWorkbook()));
	}

	private void b(CodeGenVO vo, CodeGenModel model) {
		Sheet sheet = model.getWbTables().getSheetAt(0);
		List<DataModelContext> dataModels = new ArrayList<>();
		for (Row row : sheet) {
			int rowNum = row.getRowNum();

			if (rowNum == 0) {
				continue;
			}

			System.out.println("rowNum: " + rowNum);

			String a = EgovExcelUtil.getValue(row.getCell(0));
			String b = EgovExcelUtil.getValue(row.getCell(1));
			String c = EgovExcelUtil.getValue(row.getCell(2));
			String u = EgovExcelUtil.getValue(row.getCell(19));
			System.out.println("a: " + a);
			System.out.println("b: " + b);
			System.out.println("c: " + c);
			System.out.println("u: " + u);

			DataModelContext dataModel = new DataModelContext();

			dataModel.setVender(vo.getDataModel().getVender());

			Entity entity = new Entity(c);
			dataModel.setEntity(entity);

			GodTable godTable = new GodTable();
			godTable.setTableSchema(b);
			godTable.setTableName(c);
			godTable.setTableComment(u);
			dataModel.setGodTable(godTable);

			c(model.getWbColumns(), dataModel);

			dataModels.add(dataModel);
		}
		model.setDataModels(dataModels);
	}

	private void c(XSSFWorkbook wb, DataModelContext dataModel) {
		Sheet sheet = wb.getSheetAt(0);

		List<Attribute> attributes = new ArrayList<Attribute>();
		List<Attribute> primaryKeys = new ArrayList<Attribute>();

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

			if (b.equals(dataModel.getGodTable().getTableSchema())
					&& c.equals(dataModel.getGodTable().getTableName())) {
				Attribute attr = new Attribute(d);
				attr.setType(h);
				attr.setJavaType(getJavaType(h));
				attributes.add(attr);
				primaryKeys.add(attr);
			}
		}

		dataModel.setAttributes(attributes);
		dataModel.setPrimaryKeys(primaryKeys);
	}

	private String getJavaType(String type) {
		String javaType = "String";
		if ("bigint".equals(type)) {
			javaType = "Long";
		} else if ("datetime".equals(type)) {
			javaType = "Date";
		}
		return javaType;
	}

}