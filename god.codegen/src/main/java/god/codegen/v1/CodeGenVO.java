package god.codegen.v1;

import egovframework.dev.imp.codegen.template.model.DataModelContext;

public class CodeGenVO {

	private String templateNameTables;

	private String templateNameColumns;

	private DataModelContext dataModel;

	public String getTemplateNameTables() {
		return templateNameTables;
	}

	public void setTemplateNameTables(String templateNameTables) {
		this.templateNameTables = templateNameTables;
	}

	public String getTemplateNameColumns() {
		return templateNameColumns;
	}

	public void setTemplateNameColumns(String templateNameColumns) {
		this.templateNameColumns = templateNameColumns;
	}

	public DataModelContext getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModelContext dataModel) {
		this.dataModel = dataModel;
	}

}
