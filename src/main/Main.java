package main;

import java.util.Date;
import java.util.List;


import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;

public class Main {

	public static void main(String[] args) {


		
		ProjectController projectController = new ProjectController();
		
		Project project = new Project();
		
		project.setName("FACULDADE");
		project.setDescription("registrar atividades da UFPB");
		
		
		projectController.save(project);
		
		
		TaskController taskController = new TaskController();
		
		Task task = new Task();
		task.setIdProject(10);
		task.setName("projeto de MEC DOS SOLOS");
		task.setDescription("relatorio referente as aulas práticas no laboratório");
		task.setCompleted(false);
		task.setNotes("Sem notas");
		task.setDeadline(new Date());
		
		taskController.save(task);
		task.setId(4);
		task.setName("proj mec solos");
		taskController.update(task);
		
		
		List<Project> projects = projectController.getAll();
		System.out.println("Total de projetos = " + projects.size());
		
		
		
		
		
		
	}

}
