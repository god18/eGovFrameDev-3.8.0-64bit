import java.util.ArrayList;
import java.util.List;

import model.Attribute;
import model.DataModelContext;
import model.Entity;
import operation.CrudCodeGen;

public class GodCrudCodeGenMain {

	public static void main(String[] args) {
		GodCrudCodeGenMain main = new GodCrudCodeGenMain();
		DataModelContext dataModel = main.a();
		main.b(dataModel);
	}

	private DataModelContext a() {
		DataModelContext dataModel = new DataModelContext();

		dataModel.setPackageName("pkg");
		dataModel.setAuthor("이백행");
		dataModel.setTeam("갓소프트");
		dataModel.setCreateDate("2009.02.01");

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
		String templateFile = "templates/crud/src/main/resources/pkg/EgovSample_Sample2_SQL.vm";
		String result = null;
		try {
			result = crudCodeGen.generate(dataModel, templateFile);
		} catch (Exception e) {
			e.getMessage();
			System.err.println(result);
		}
	}

}
