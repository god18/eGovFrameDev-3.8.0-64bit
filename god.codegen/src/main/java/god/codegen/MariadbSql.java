package god.codegen;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import god.codegen.v2.SqlInsert;

public class MariadbSql implements Sql {

	final static Logger logger = LoggerFactory.getLogger(MariadbSql.class);

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

			String data = SqlInsert.insert(dataModel);
			String pathname = SystemUtils.USER_HOME + "/Desktop/god/" + dataModel.getTables().getTableSchema() + "/"
					+ dataModel.getTables().getTableName() + "/insert " + dataModel.getTables().getTableName() + ".sql";
			generate(dataModel, data, pathname);

			i++;
		}
	}

	private void generate(DataModelContext dataModel, String data, String pathname) {
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
