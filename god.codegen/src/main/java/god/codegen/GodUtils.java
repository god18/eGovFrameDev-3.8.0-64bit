package god.codegen;

public class GodUtils {

	public static String getJavaType(String type) {
		String javaType = "String";
		if ("bigint".equals(type)) {
			javaType = "Long";
		} else if ("datetime".equals(type)) {
			javaType = "Date";
		}
		return javaType;
	}

}
