package god.codegen.v2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.rte.fdl.string.EgovDateUtil;
import god.codegen.v1.CodeGenModel;
import god.codegen.v1.CodeGenVO;
import god.codegen.v1.Excel;
import operation.CrudCodeGen;

public class GodCrudCodeGen {

	private CrudCodeGen crudCodeGen = new CrudCodeGen();

	public static void main(String[] args) {
		GodCrudCodeGen main = new GodCrudCodeGen();

		CodeGenVO vo = new CodeGenVO();
		CodeGenModel model = new CodeGenModel();

		String condition = "a";

		if ("a".equals(condition)) {
			vo.setTemplateNameTables(
					"C:\\Users\\godsoft\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-TABLES.xlsx");
			vo.setTemplateNameColumns(
					"C:\\Users\\godsoft\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-COLUMNS.xlsx");
		} else {
			vo.setTemplateNameTables(
					"D:\\Users\\LeeBaekHaeng\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-TABLES.xlsx");
			vo.setTemplateNameColumns(
					"D:\\Users\\LeeBaekHaeng\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64-COLUMNS.xlsx");
		}

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
			String data = SqlInsert.insert(dataModel);
			String pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getTable().getTableSchema() + "/"
					+ dataModel.getTable().getTableName() + "/insert " + dataModel.getTable().getTableName()
					+ ".sql";
			generate2(dataModel, data, pathname);

			data = SqlUpdate.update(dataModel);
			pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getTable().getTableSchema() + "/"
					+ dataModel.getTable().getTableName() + "/update " + dataModel.getTable().getTableName()
					+ ".sql";
			generate2(dataModel, data, pathname);

			// templateFile = "eGovFrameTemplates.god/sql/update.vm";
			// pathname = SystemUtils.USER_HOME + "/Desktop/god/" +
			// dataModel.getGodTable().getTableSchema() + "/"
			// + dataModel.getGodTable().getTableName() + "/update " +
			// dataModel.getGodTable().getTableName()
			// + ".sql";
			// generate(dataModel, templateFile, pathname);
			//
			// templateFile = "eGovFrameTemplates.god/sql/delete.vm";
			// pathname = SystemUtils.USER_HOME + "/Desktop/god/" +
			// dataModel.getGodTable().getTableSchema() + "/"
			// + dataModel.getGodTable().getTableName() + "/delete " +
			// dataModel.getGodTable().getTableName()
			// + ".sql";
			// generate(dataModel, templateFile, pathname);
			//
			// templateFile = "eGovFrameTemplates.god/sql/select.vm";
			// pathname = SystemUtils.USER_HOME + "/Desktop/god/" +
			// dataModel.getGodTable().getTableSchema() + "/"
			// + dataModel.getGodTable().getTableName() + "/select " +
			// dataModel.getGodTable().getTableName()
			// + ".sql";
			// generate(dataModel, templateFile, pathname);
			//
			// templateFile = "eGovFrameTemplates.god/sql/select2.vm";
			// pathname = SystemUtils.USER_HOME + "/Desktop/god/" +
			// dataModel.getGodTable().getTableSchema() + "/"
			// + dataModel.getGodTable().getTableName() + "/select2 " +
			// dataModel.getGodTable().getTableName()
			// + ".sql";
			// generate(dataModel, templateFile, pathname);
			//
			// templateFile = "eGovFrameTemplates.god/sql/select3.vm";
			// pathname = SystemUtils.USER_HOME + "/Desktop/god/" +
			// dataModel.getGodTable().getTableSchema() + "/"
			// + dataModel.getGodTable().getTableName() + "/select3 " +
			// dataModel.getGodTable().getTableName()
			// + ".sql";
			// generate(dataModel, templateFile, pathname);
		}
	}

	void generate(DataModelContext dataModel, String templateFile, String pathname) {
		// CrudCodeGen crudCodeGen = new CrudCodeGen();
		String result = null;
		try {
			result = crudCodeGen.generate(dataModel, templateFile);
		} catch (Exception e) {
			e.getMessage();
		}

		File file = new File(pathname);
		String data = result;
		Charset encoding = StandardCharsets.UTF_8;
		writeStringToFile(file, data, encoding);
	}

	private void generate2(DataModelContext dataModel, String data, String pathname) {
		File file = new File(pathname);
		// String data = null;
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
