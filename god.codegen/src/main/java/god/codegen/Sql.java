package god.codegen;

import java.util.List;

import egovframework.dev.imp.codegen.template.model.DataModelContext;

public interface Sql {

	void sql(List<DataModelContext> dataModels);

}
