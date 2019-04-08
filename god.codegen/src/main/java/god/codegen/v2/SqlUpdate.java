package god.codegen.v2;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;

public class SqlUpdate {

	public static String update(DataModelContext model) {
		StringBuffer sb = new StringBuffer();

		sb.append("/*\n");
		sb.append("god\n");
		sb.append("*/\n");

		sb.append("UPDATE ");
		sb.append(model.getEntity().getName());
		sb.append(" SET\n");

		int i = 0;
		int size = model.getAttributes().size() - 1;
		for (Attribute attribute : model.getAttributes()) {
			if (i < size) {
				if ("bigint".equals(attribute.getType())) {
					sb.append("\t");
					sb.append(attribute.getName());
					sb.append(" = 1");
					sb.append(",\n");
				} else if ("datetime".equals(attribute.getType())) {
					sb.append("\t");
					sb.append(attribute.getName());
					sb.append(" = SYSDATE()");
					sb.append(",\n");
				} else {
					sb.append("\t");
					sb.append(attribute.getName());
					sb.append(" = ''");
					sb.append(",\n");
				}
			} else {
				if ("bigint".equals(attribute.getType())) {
					sb.append("\t");
					sb.append(attribute.getName());
					sb.append(" = 1");
					sb.append("\n");
				} else if ("datetime".equals(attribute.getType())) {
					sb.append("\t");
					sb.append(attribute.getName());
					sb.append(" = SYSDATE()");
					sb.append("\n");
				} else {
					sb.append("\t");
					sb.append(attribute.getName());
					sb.append(" = ''");
					sb.append("\n");
				}
			}
			i++;
		}

		sb.append("WHERE\n");

		// i = 0;
		for (Attribute attribute : model.getPrimaryKeys()) {
			// if (i < size) {
			if ("bigint".equals(attribute.getType())) {
				sb.append("\tAND ");
				sb.append(attribute.getName());
				sb.append(" = 1\n");
			} else {
				sb.append("\tAND ");
				sb.append(attribute.getName());
				sb.append(" = '1'\n");
			}
			// } else {
			// if ("bigint".equals(attribute.getType())) {
			// sb.append("\tAND ");
			// sb.append(attribute.getName());
			// sb.append(" = 1\n");
			// } else {
			// sb.append("\tAND ");
			// sb.append(attribute.getName());
			// sb.append(" = '1'\n");
			// }
			// }
			// i++;
		}

		sb.append(";");

		return sb.toString();
	}

}
