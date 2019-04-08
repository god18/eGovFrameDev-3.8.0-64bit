package god.codegen.v2;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;

public class SqlUpdate {

	public static String update(DataModelContext model) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();

		sb.append("/*\n");
		sb.append("god\n");
		sb.append("*/\n");

		sb.append("UPDATE ");
		sb.append(model.getEntity().getName());
		sb.append(" SET\n");

		for (Attribute attribute : model.getAttributes()) {
			sb2.append("\t");
			if ("bigint".equals(attribute.getType())) {
				sb2.append(attribute.getName());
				sb2.append(" = 1");
			} else if ("datetime".equals(attribute.getType())) {
				sb2.append(attribute.getName());
				sb2.append(" = SYSDATE()");
			} else {
				sb2.append(attribute.getName());
				sb2.append(" = ''");
			}
			sb2.append(",\n");
		}

		String set = sb2.toString();
		sb.append(set.substring(0, set.length() - 2));

		sb.append("\nWHERE\n");

		for (Attribute attribute : model.getPrimaryKeys()) {
			if ("bigint".equals(attribute.getType())) {
				sb.append("\tAND ");
				sb.append(attribute.getName());
				sb.append(" = 1\n");
			} else {
				sb.append("\tAND ");
				sb.append(attribute.getName());
				sb.append(" = '1'\n");
			}
		}

		sb.append(";");

		return sb.toString();
	}

}
