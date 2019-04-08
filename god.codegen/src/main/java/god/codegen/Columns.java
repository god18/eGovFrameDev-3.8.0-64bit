package god.codegen;

import egovframework.dev.imp.codegen.template.model.DbModelElement;

public class Columns extends DbModelElement {

	public Columns(String name) {
		super(name);
	}

	private String columnName;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
