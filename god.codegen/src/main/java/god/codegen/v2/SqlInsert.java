package god.codegen.v2;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;

public class SqlInsert {

	public static String insert(DataModelContext model) {
		StringBuffer sb = new StringBuffer();

		sb.append("/*\n");
		sb.append("god\n");
		sb.append("*/\n");

		sb.append("INSERT INTO ");
		sb.append(model.getEntity().getName());
		sb.append(" (\n");

		int i = 0;
		int size = model.getAttributes().size() - 1;
		for (Attribute attribute : model.getAttributes()) {
			if (i < size) {
				sb.append("\t");
				sb.append(attribute.getName());
				sb.append(",\n");
			} else {
				sb.append("\t");
				sb.append(attribute.getName());
				sb.append("\n");
			}
			i++;
		}

		sb.append(") VALUES (\n");

		i = 0;
		for (Attribute attribute : model.getAttributes()) {
			if (i < size) {
				if ("bigint".equals(attribute.getType())) {
					sb.append("\t1, /* ");
					sb.append(attribute.getName());
					sb.append(" */\n");
				} else {
					sb.append("\t'', /* ");
					sb.append(attribute.getName());
					sb.append(" */\n");
				}
			} else {
				if ("bigint".equals(attribute.getType())) {
					sb.append("\t1 /* ");
					sb.append(attribute.getName());
					sb.append(" */\n");
				} else {
					sb.append("\t'' /* ");
					sb.append(attribute.getName());
					sb.append(" */\n");
				}
			}
			i++;
		}

		sb.append(")\n;");

		return sb.toString();
	}

}
