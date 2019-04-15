package god.codegen;

public class MariadbCrudCodeGenMain {

	public static void main(String[] args) {
		// MariadbCrudCodeGen mariadbCrudCodeGen = new MariadbCrudCodeGen();
		CrudCodeGen mariadbCrudCodeGen = new MariadbCrudCodeGen();
		mariadbCrudCodeGen
				.setUp("C:\\Users\\godsoft\\Google 드라이브(godsoft18@gmail.com)\\codegen\\mariadb-10.1.37-winx64.xlsx");
		mariadbCrudCodeGen.sql();
	}

}
