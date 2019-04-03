package god.codegen.v1;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import egovframework.dev.imp.codegen.template.model.DataModelContext;

public class CodeGenModel {

	private XSSFWorkbook wbTables;

	private XSSFWorkbook wbColumns;

	private List<DataModelContext> dataModels;

	public XSSFWorkbook getWbTables() {
		return wbTables;
	}

	public void setWbTables(XSSFWorkbook wbTables) {
		this.wbTables = wbTables;
	}

	public XSSFWorkbook getWbColumns() {
		return wbColumns;
	}

	public void setWbColumns(XSSFWorkbook wbColumns) {
		this.wbColumns = wbColumns;
	}

	public List<DataModelContext> getDataModels() {
		return dataModels;
	}

	public void setDataModels(List<DataModelContext> dataModels) {
		this.dataModels = dataModels;
	}

}
