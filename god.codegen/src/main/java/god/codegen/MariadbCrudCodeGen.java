package god.codegen;

public class MariadbCrudCodeGen extends CrudCodeGen {

	public MariadbCrudCodeGen() {
		setUp = new MariadbSetUp();
		// sql = new MariadbSql();
		sql = new MariadbSql2();
	}

	@Override
	void display() {

	}

}
