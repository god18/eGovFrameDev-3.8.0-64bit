package god.codegen;

import egovframework.dev.imp.codegen.template.model.DbModelElement;

public class Tables extends DbModelElement {

	public Tables(String name) {
		super(name);
	}

	private String tableSchema;

	private String tableName;

	private String tableComment;

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

}
