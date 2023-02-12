package controller;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;


public class ProjectController {

	
	public void save(Project project) {
		String sql = "INSERT INTO projects (name, "
				+ "description, "
				+ "createdAt, "
				+ "updatedAt) "
				+ "VALUES (?, ?, ?, ?)";
		
		Connection conn = null; 
		PreparedStatement st = null;
			
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			
			
			st.setString(1, project.getName());
			st.setString(2, project.getDescription());
			st.setDate(3, new Date(project.getCreatedAt().getTime()));
			st.setDate(4, new Date(project.getUpdatedAt().getTime()));
			
			
			st.execute(); // EXECUTAR NO BANCO DE DADOS
			
		}catch(Exception ex) {
			throw new RuntimeException("Erro ao salvar o projeto "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st);
		}
		
		
	}
	
	public void update(Project project) {
		
		String sql = "UPDATE projects SET "
				+ "name = ?,"
				+ "description = ?,"
				+ "createdAt = ?,"
				+ "updatedAt = ?"
				+ "WHERE id = ?";
		
		Connection conn = null; 
		PreparedStatement st = null;
		
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			
			st.setString(1, project.getName());
			st.setString(2, project.getDescription());
			st.setDate(3, new Date(project.getCreatedAt().getTime()));
			st.setDate(4, new Date(project.getUpdatedAt().getTime()));
			st.setInt(5, project.getId());
			
			st.execute(); // EXECUTAR NO BANCO DE DADOS
			
		}catch(Exception ex) {
			
			throw new RuntimeException("Erro ao atualizar o projeto "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st);
		}
		
		
		
	}
	
	
	
	public List<Project> getAll() {
		String sql = "SELECT * FROM projects";
		
		List<Project> projects = new ArrayList<>();
		
		Connection conn = null; 
		PreparedStatement st = null;
		ResultSet rs = null; // GUARDAR RETORNO DO BANCO DE DADOS
		
		// LISTA DE TAREFAS DEVOLVIDA QUANDO A CHAMADA DO MÉTODO ACONTECER
		
		
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
						
			rs = st.executeQuery(); 
			
			while (rs.next()) {
				Project project= new Project();
				
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				project.setDescription(rs.getString("description"));
				project.setCreatedAt(rs.getDate("createdAt"));
				project.setUpdatedAt(rs.getDate("updatedAt"));
			
				
				projects.add(project);
			}
			
		}catch(Exception ex) {
			throw new RuntimeException("Erro ao inserir a tarefa "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st, rs);
		}
		
		
		return projects;
	}
	
	
	public void removeById(int IdProject) {
		String sql = "DELETE FROM projects WHERE id = ?";
		
		Connection conn = null; 
		PreparedStatement st = null;
			
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			st.setInt(1, IdProject); // SETOU O PAREMETRO "?" POR projectId
			st.execute(); // EXECUTAR NO BANCO DE DADOS
			
		}catch(Exception ex) {
			
			throw new RuntimeException("Erro ao deletar o projeto "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st);
		}
		
		
	}
	
	
	
}
