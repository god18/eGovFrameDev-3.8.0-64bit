package god.codegen.v1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.rte.fdl.string.EgovDateUtil;
import operation.CrudCodeGen;

public class SqlCodeGen {

	public static void main(String[] args) {
		SqlCodeGen main = new SqlCodeGen();

		CodeGenVO vo = new CodeGenVO();
		CodeGenModel model = new CodeGenModel();

		// vo.setTemplateNameTables(
		// "C:\\Users\\godsoft\\Google
		// 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-TABLES.xlsx");
		// vo.setTemplateNameColumns(
		// "C:\\Users\\godsoft\\Google
		// 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-COLUMNS.xlsx");
		vo.setTemplateNameTables(
				"D:\\Users\\LeeBaekHaeng\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-TABLES.xlsx");
		vo.setTemplateNameColumns(
				"D:\\Users\\LeeBaekHaeng\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-COLUMNS.xlsx");

		DataModelContext dataModel = new DataModelContext();
		dataModel.setPackageName("god.com");
		dataModel.setAuthor("이백행");
		dataModel.setTeam("갓소프트");
		dataModel.setCreateDate(EgovDateUtil.toString(new Date(), "yyyy-MM-dd", null));
		dataModel.setVender("MySql");
		vo.setDataModel(dataModel);

		main.main(vo, model);
	}

	public void main(CodeGenVO vo, CodeGenModel model) {
		Excel excel = new Excel();
		excel.setDataModels(vo, model);
		for (DataModelContext dataModel : model.getDataModels()) {
			String templateFile = "eGovFrameTemplates.god/sql/insert.vm";
			String pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getGodTable().getTableSchema() + "/"
					+ dataModel.getGodTable().getTableName() + "/insert " + dataModel.getGodTable().getTableName()
					+ ".sql";
			generate(dataModel, templateFile, pathname);

			templateFile = "eGovFrameTemplates.god/sql/update.vm";
			pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getGodTable().getTableSchema() + "/"
					+ dataModel.getGodTable().getTableName() + "/update " + dataModel.getGodTable().getTableName()
					+ ".sql";
			generate(dataModel, templateFile, pathname);

			templateFile = "eGovFrameTemplates.god/sql/delete.vm";
			pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getGodTable().getTableSchema() + "/"
					+ dataModel.getGodTable().getTableName() + "/delete " + dataModel.getGodTable().getTableName()
					+ ".sql";
			generate(dataModel, templateFile, pathname);

			templateFile = "eGovFrameTemplates.god/sql/select.vm";
			pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getGodTable().getTableSchema() + "/"
					+ dataModel.getGodTable().getTableName() + "/select " + dataModel.getGodTable().getTableName()
					+ ".sql";
			generate(dataModel, templateFile, pathname);

			templateFile = "eGovFrameTemplates.god/sql/select2.vm";
			pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getGodTable().getTableSchema() + "/"
					+ dataModel.getGodTable().getTableName() + "/select2 " + dataModel.getGodTable().getTableName()
					+ ".sql";
			generate(dataModel, templateFile, pathname);

			templateFile = "eGovFrameTemplates.god/sql/select3.vm";
			pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getGodTable().getTableSchema() + "/"
					+ dataModel.getGodTable().getTableName() + "/select3 " + dataModel.getGodTable().getTableName()
					+ ".sql";
			generate(dataModel, templateFile, pathname);
		}
	}

	private void generate(DataModelContext dataModel, String templateFile, String pathname) {
		CrudCodeGen crudCodeGen = new CrudCodeGen();
		// String templateFile = "eGovFrameTemplates.god/sql/insert.vm";
		String result = null;
		try {
			result = crudCodeGen.generate(dataModel, templateFile);
		} catch (Exception e) {
			e.getMessage();
		}

		// File file = new File(SystemUtils.USER_HOME + "/Desktop/god/" +
		// dataModel.getGodTable().getTableSchema() + "/"
		// + dataModel.getGodTable().getTableName() + "/insert " +
		// dataModel.getGodTable().getTableName()
		// + ".sql");
		File file = new File(pathname);
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
