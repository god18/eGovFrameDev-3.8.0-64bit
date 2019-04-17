package god.codegen;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import operation.CrudCodeGen;

public class CrudCodeGenTest {

	private CrudCodeGen crudCodeGen;
	private DataModelContext dataModel;

	public CrudCodeGenTest(DataModelContext dataModel) throws Exception {
		crudCodeGen = new CrudCodeGen();

		this.dataModel = dataModel;
	}

	private void genAndDiff(String templateFile, String targetFile) throws Exception {
		String result = crudCodeGen.generate(dataModel, templateFile);
		System.out.println(result);
	}

	public void testSQLMap() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/resource/pkg/EgovSample_Sample2_SQL.vm";
		String targetFile = "eGovFrameTemplates/crud/resources/pkg/EgovSample_Sample2_SQL.xml";

		genAndDiff(templateFile, targetFile);
	}

	public void testService() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/EgovSample2Service.vm";
		String targetFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/EgovSample2Service.jav";

		genAndDiff(templateFile, targetFile);
	}

	public void testVO() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/Sample2VO.vm";
		String targetFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/Sample2VO.jav";

		genAndDiff(templateFile, targetFile);
	}

	public void testServiceImpl() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/impl/EgovSample2ServiceImpl.vm";
		String targetFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/impl/EgovSample2ServiceImpl.jav";

		genAndDiff(templateFile, targetFile);
	}

	public void testDAO() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/impl/Sample2DAO.vm";
		String targetFile = "eGovFrameTemplates/crud/src/main/java/pkg/service/impl/Sample2DAO.jav";

		genAndDiff(templateFile, targetFile);
	}

	public void testController() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/main/java/pkg/web/EgovSample2Controller.vm";
		String targetFile = "eGovFrameTemplates/crud/src/main/java/pkg/web/EgovSample2Controller.jav";

		genAndDiff(templateFile, targetFile);
	}

	public void testListView() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2List.vm";
		String targetFile = "eGovFrameTemplates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2List.jsp";

		genAndDiff(templateFile, targetFile);
	}

	public void testRegisterView() throws Exception {
		String templateFile = "eGovFrameTemplates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2Register.vm";
		String targetFile = "eGovFrameTemplates/crud/src/webapp/WEB-INF/jsp/pkg/egovSample2Register.jsp";

		genAndDiff(templateFile, targetFile);
	}

}
