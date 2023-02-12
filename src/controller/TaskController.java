package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Task;
import util.ConnectionFactory;

public class TaskController {
	
	public void save(Task task) {
		String sql = "INSERT INTO tasks (idProject,"
				+ " name, "
				+ "description, "
				+ "completed, "
				+ "notes, "
				+ "deadline, "
				+ "createdAt, "	
				+ "updatedAt) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null; 
		PreparedStatement st = null;
			
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			st.setInt(1, task.getIdProject()); // SETOU O PAREMETRO "?" POR ID DE TASK
			
			st.setString(2, task.getName());
			st.setString(3, task.getDescription());
			st.setBoolean(4, task.isCompleted());
			st.setString(5, task.getNotes());
			st.setDate(6, new Date(task.getDeadline().getTime()));
			st.setDate(7, new Date(task.getCreatedAt().getTime()));
			st.setDate(8, new Date(task.getUpdatedAt().getTime()));
			
			st.execute(); // EXECUTAR NO BANCO DE DADOS
			
		}catch(Exception ex) {
			//e.printStackTrace();
			throw new RuntimeException("Erro ao salvar a tarefa "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st);
		}
		
		
	}
	
	
	public void update(Task task) {
			
		String sql = "UPDATE tasks SET "
				+ "idProject = ?, "
				+ "name = ?,"
				+ "description = ?,"
				+ "completed = ?,"
				+ "notes = ?,"
				+ "deadline = ?,"
				+ "createdAt = ?,"
				+ "updatedAt = ?"
				+ "WHERE id = ?";
		
		Connection conn = null; 
		PreparedStatement st = null;
		
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			st.setInt(1, task.getIdProject()); // SETOU O PAREMETRO "?" 
			st.setString(2, task.getName());
			st.setString(3, task.getDescription());
			st.setBoolean(4, task.isCompleted());
			st.setString(5, task.getNotes());
			st.setDate(6, new Date(task.getDeadline().getTime()));
			st.setDate(7, new Date(task.getCreatedAt().getTime()));
			st.setDate(8, new Date(task.getUpdatedAt().getTime()));
			st.setInt(9, task.getId());
			
			st.execute(); // EXECUTAR NO BANCO DE DADOS
			
		}catch(SQLException ex) {
			
			throw new RuntimeException("Erro ao atualizar a tarefa "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st);
		}
		
		
		
	}
	
	public void removeById(int taskId) {
		String sql = "DELETE FROM tasks WHERE id = ?";
		
		Connection conn = null; 
		PreparedStatement st = null;
			
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			st.setInt(1, taskId); // SETOU O PAREMETRO "?" POR TASKID
			st.execute(); // EXECUTAR NO BANCO DE DADOS
			
		}catch(Exception ex) {
			
			throw new RuntimeException("Erro ao deletar a tarefa "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st);
		}
		
		
	}
	
	public List<Task> getAll(int idProject) {
		String sql = "SELECT * FROM tasks WHERE idProject = ?";
		
		Connection conn = null; 
		PreparedStatement st = null;
		ResultSet rs = null; // GUARDAR RETORNO DO BANCO DE DADOS
		
		// LISTA DE TAREFAS DEVOLVIDA QUANDO A CHAMADA DO MÉTODO ACONTECER
		List<Task> tasks = new ArrayList<Task>();
		
		try {
			conn = ConnectionFactory.getConnection(); //PEDIU A CONEXÃO
			st = conn.prepareStatement(sql); //PREPAROU O SQL
			st.setInt(1, idProject); 
			
			rs = st.executeQuery(); 
			
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setIdProject(rs.getInt("idProject"));
				task.setName(rs.getString("name"));
				task.setDescription(rs.getString("description"));
				task.setCompleted(rs.getBoolean("completed"));
				task.setNotes(rs.getString("notes"));
				task.setDeadline(rs.getDate("deadline"));
				task.setCreatedAt(rs.getDate("createdAt"));
				task.setUpdatedAt(rs.getDate("updatedAt"));
			
				
				tasks.add(task);
			}
			
		}catch(Exception ex) {
			//e.printStackTrace();
			throw new RuntimeException("Erro ao inserir a tarefa "+ ex.getMessage(), ex );
		}finally {
			ConnectionFactory.closeConnection(conn, st, rs);
		}
		
		
		return tasks;
	}
	
	
	
	

}
