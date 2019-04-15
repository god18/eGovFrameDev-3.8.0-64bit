package god.codegen;

import java.util.List;

import egovframework.dev.imp.codegen.template.model.DataModelContext;

public abstract class CrudCodeGen {

	SetUp setUp;
	Sql sql;
	// QuackBehavior quackBehavior;

	List<DataModelContext> dataModels;

	public CrudCodeGen() {
	}

	public void setSetUp(SetUp setUp) {
		this.setUp = setUp;
	}

	public void setSql(Sql sql) {
		this.sql = sql;
	}

	// public void setQuackBehavior(QuackBehavior qb) {
	// quackBehavior = qb;
	// }

	abstract void display();

	public void setUp(String templateName) {
		dataModels = setUp.setUp(templateName);
	}

	public void sql() {
		sql.sql(dataModels);
	}

	// public void performQuack() {
	// quackBehavior.quack();
	// }

	public void swim() {
		System.out.println("All ducks float, even decoys!");
	}
}
