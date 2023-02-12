package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/*import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;*/

public class ConnectionFactory {
	
	
	private static final String URL = "jdbc:mysql://localhost:3306/poliatividades";
	private static final String USER = "root";
	private static final String PASS = "";
	
	
	//ABRE CONEXÃO COM BANCO DE DADOS
	public static Connection getConnection() {
		try {
			
			return DriverManager.getConnection(URL, USER, PASS);
		}catch (Exception ex) {
			throw new RuntimeException("Erro na conexão com o banco de dados", ex);
		}
	}
	
	//FECHA CONEXÃO COM BANCO DE DADOS
		public static  void closeConnection(Connection connection) {
			
			try {
				if(connection != null) {
					connection.close();
				}		
				
			}catch (SQLException e) {
				throw new RuntimeException("Erro ao fechar a conexão com banco de dados");
			}
			
		}
		
	//FECHA CONEXÃO COM BANCO DE DADOS
	public static  void closeConnection(Connection connection, PreparedStatement statement) {
		
		try {
			if(connection != null) {
				connection.close();
			}		
			
			if(statement != null) {
				statement.close();
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar a conexão com banco de dados");
		}
		
	}
	
public static  void closeConnection(Connection connection, 
		PreparedStatement statement, ResultSet resultset) {
		
		try {
			if(connection != null) {
				connection.close();
			}		
			
			if(statement != null) {
				statement.close();
			}
			
			if(resultset != null) {
				resultset.close();
			}
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar a conexão com banco de dados");
		}
		
	}
	
	

	
	
	
/*	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		
		catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
				
	}
	
	
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}*/
	
	
}
