package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lista_Usuario {
	public static boolean Validartel(String telefono) throws SQLException {
		Connection conn=DriverManager.getConnection("jdbc:mariadb://localhost:3306/ricarros", "root","");
		String sql="Select * from usuarios where Nombre='"+telefono+"'";
		Statement stm= conn.createStatement();
		ResultSet rs= stm.executeQuery(sql);
		return rs.next();
		
	}

}
