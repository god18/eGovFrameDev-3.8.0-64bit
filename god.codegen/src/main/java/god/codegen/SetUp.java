package god.codegen;

import java.util.List;

import egovframework.dev.imp.codegen.template.model.DataModelContext;

public interface SetUp {

	List<DataModelContext> setUp(String templateName);

}
