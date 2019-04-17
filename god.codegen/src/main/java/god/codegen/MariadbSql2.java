package god.codegen;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.dev.imp.codegen.template.model.DataModelContext;

public class MariadbSql2 implements Sql {

	final static Logger logger = LoggerFactory.getLogger(MariadbSql2.class);

	@Override
	public void sql(List<DataModelContext> dataModels) {
		int i = 1;
		for (DataModelContext dataModel : dataModels) {
			logger.debug("i: {}", i);
			logger.debug("dataModel: {}", dataModel);
			logger.debug("getEntity: {}", dataModel.getEntity());
			logger.debug("getName: {}", dataModel.getEntity().getName());
			logger.debug("getTables: {}", dataModel.getTables());
			logger.debug("getName: {}", dataModel.getTables().getName());
			logger.debug("getTableComment: {}", dataModel.getTables().getTableComment());
			logger.debug("getAttributes: {}", dataModel.getAttributes());

			try {
				CrudCodeGenTest crudCodeGenTest = new CrudCodeGenTest(dataModel);
				crudCodeGenTest.testSQLMap();
			} catch (Exception e) {
				e.getMessage();
			}

			i++;
		}
	}

}
