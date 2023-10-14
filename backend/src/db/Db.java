package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {
	private static final String URL = "jdbc:mysql://localhost:3306/agencia?allowPublicKeyRetrieval=true";
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final boolean USESSL= false;
	private static Connection conexao = null;

	public static Properties loadProps() {
			Properties props = new Properties();
			props.setProperty("user", USUARIO);
			props.setProperty("password", SENHA);
			props.setProperty("useSSL", USESSL+"");
			return props;
	}

	public static Connection getConnection() {
		if (conexao == null) {
			try {
				Properties props = loadProps();
				conexao = DriverManager.getConnection(URL, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conexao;
	}
	
	public static void criarDatabase() {
		Statement st = null;
		try {
			st = conexao.createStatement();
			st.executeUpdate("USE agencia");
			st.executeUpdate(DbTable.getDDLCreateUsuario());
			st.executeUpdate(DbTable.getDDLCreateEndereco());
			st.executeUpdate(DbTable.getDDLCreateFornecedor());
			st.executeUpdate(DbTable.getDDLCreateAdministrador());
			st.executeUpdate(DbTable.getDDLCreateCliente());
			st.executeUpdate(DbTable.getDDLCreateCarrinhoCompra());
			st.executeUpdate(DbTable.getDDLCreateHospedagem());
			st.executeUpdate(DbTable.getDDLCreatePassagem());
			st.executeUpdate(DbTable.getDDLCreatePacoteViagem());
			st.executeUpdate(DbTable.getDDLCreateOrigemDestino());
			st.executeUpdate(DbTable.getDDLCreateRevisa());

			st.executeUpdate(DbTable.getDDLSetUsuarioFkEndereco());
			st.executeUpdate(DbTable.getDDLSetHospedagemFkOrigemDestino());
			st.executeUpdate(DbTable.getDDLSetPassagemFkPacoteViagem());
			st.executeUpdate(DbTable.getDDLSetPacoteViagemFkOrigemDestino());

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeStatement(st);
		}
	}

	public static void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closePreparedStatement(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
