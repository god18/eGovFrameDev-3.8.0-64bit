package god.codegen;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.dev.imp.codegen.template.model.Entity;
import operation.CrudCodeGen;

public class GodCrudCodeGen {

	public static void main(String[] args) {
		GodCrudCodeGen main = new GodCrudCodeGen();
		DataModelContext dataModel = main.a();
		main.b(dataModel);
	}

	private DataModelContext a() {
		DataModelContext dataModel = new DataModelContext();

		dataModel.setPackageName("pkg");
		dataModel.setAuthor("이백행");
		dataModel.setTeam("갓소프트");
		dataModel.setCreateDate("2009.02.01");

		dataModel.setVender("MySql");

		Entity entity = new Entity("SAMPLE2");

		dataModel.setEntity(entity);

		List<Attribute> attributes = new ArrayList<Attribute>();
		List<Attribute> primaryKeys = new ArrayList<Attribute>();

		Attribute attr = new Attribute("ID");
		attr.setJavaType("String");
		attributes.add(attr);
		primaryKeys.add(attr);

		attr = new Attribute("NAME");
		attr.setJavaType("String");
		attributes.add(attr);
		// primaryKeys.add(attr);

		attr = new Attribute("DESCRIPTION");
		attr.setJavaType("String");
		attributes.add(attr);

		attr = new Attribute("USE_YN");
		attr.setJavaType("String");
		attributes.add(attr);

		attr = new Attribute("REG_USER");
		attr.setJavaType("String");
		attributes.add(attr);

		dataModel.setAttributes(attributes);
		dataModel.setPrimaryKeys(primaryKeys);

		return dataModel;
	}

	private void b(DataModelContext dataModel) {
		CrudCodeGen crudCodeGen = new CrudCodeGen();
		String templateFile = "eGovFrameTemplates/crud/resource/pkg/EgovSample_Sample2_SQL.vm";
		String result = null;
		try {
			result = crudCodeGen.generate(dataModel, templateFile);
		} catch (Exception e) {
			e.getMessage();
			System.err.println(result);
		}

		File file = new File(SystemUtils.USER_HOME + "/Desktop/" + dataModel.getEntity().getUcName() + ".sql");
		String data = result;
		Charset encoding = StandardCharsets.UTF_8;
		writeStringToFile(file, data, encoding);
	}

	private void writeStringToFile(File file, String data, Charset encoding) {
		System.out.println("file: " + file);
		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (IOException e) {
			e.getMessage();
		}
	}

}
