package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.EventTarget;

import com.mysql.cj.xdevapi.Table;

import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;
import util.ButtonColumnCellRederer;
import util.DeadLineColumnCellRederer;
import util.TaskTableModel;

import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.awt.BorderLayout;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JTable JTableTasks;
	;
	
	
	ProjectController projectController;
	TaskController taskController;
	
	DefaultListModel projectsModel;
	TaskTableModel taskModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		
		initDataController();
		initComponetsModel();
		
		
		setTitle("PoliAtividades");
		setMinimumSize(new Dimension(900, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel JPanelToolBar = new JPanel();
		JPanelToolBar.setAutoscrolls(true);
		JPanelToolBar.setBackground(new Color(0, 153, 102));
		
		JLabel JLabelToolBarTitle = new JLabel(" PoliAtividades");
		JLabelToolBarTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabelToolBarTitle.setIcon(new ImageIcon("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\tick.png"));
		JLabelToolBarTitle.setForeground(new Color(255, 255, 255));
		JLabelToolBarTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
		
		JLabel JLabelToolBarSubTitle = new JLabel("Anote tudo, não esqueça de nada");
		JLabelToolBarSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabelToolBarSubTitle.setForeground(new Color(255, 255, 255));
		JLabelToolBarSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		GroupLayout gl_JPanelToolBar = new GroupLayout(JPanelToolBar);
		gl_JPanelToolBar.setHorizontalGroup(
			gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelToolBar.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
						.addComponent(JLabelToolBarTitle, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
						.addComponent(JLabelToolBarSubTitle, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
					.addGap(13))
		);
		gl_JPanelToolBar.setVerticalGroup(
			gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelToolBar.createSequentialGroup()
					.addGap(31)
					.addComponent(JLabelToolBarTitle)
					.addGap(10)
					.addComponent(JLabelToolBarSubTitle))
		);
		JPanelToolBar.setLayout(gl_JPanelToolBar);
		
		JPanel JPanelProjects = new JPanel();
		JPanelProjects.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanelProjects.setBackground(new Color(255, 255, 255));
		
		JPanel JPanelTasks = new JPanel();
		JPanelTasks.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanelTasks.setBackground(new Color(255, 255, 255));
		
		JPanel JPanelProjectList = new JPanel();
		JPanelProjectList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanelProjectList.setBackground(new Color(255, 255, 255));
		
		JPanel JPanelTableTask = new JPanel();
		JPanelTableTask.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		JPanelTableTask.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JPanelTableTask.setBackground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(JPanelToolBar, GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(JPanelProjectList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(JPanelProjects, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(JPanelTasks, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addComponent(JPanelTableTask, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(JPanelToolBar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(JPanelTasks, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addComponent(JPanelProjects, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(JPanelProjectList, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
						.addComponent(JPanelTableTask, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))
		);
		
		JScrollPane JScrollPaneProjects = new JScrollPane();
		GroupLayout gl_JPanelProjectList = new GroupLayout(JPanelProjectList);
		gl_JPanelProjectList.setHorizontalGroup(
			gl_JPanelProjectList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelProjectList.createSequentialGroup()
					.addContainerGap()
					.addComponent(JScrollPaneProjects, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_JPanelProjectList.setVerticalGroup(
			gl_JPanelProjectList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelProjectList.createSequentialGroup()
					.addGap(12)
					.addComponent(JScrollPaneProjects, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JList JListProjects = new JList();
		
		JScrollPaneProjects.setViewportView(JListProjects);
		JListProjects.setFixedCellHeight(50);
		JListProjects.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JListProjects.setSelectionBackground(new Color(0, 153, 102));
		JListProjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JListProjects.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JListProjects.setFont(new Font("Segoe UI", Font.BOLD, 18));
		JPanelProjectList.setLayout(gl_JPanelProjectList);
		
		
		JScrollPane JScrollPaneTask = new JScrollPane();
		JScrollPaneTask.setBackground(new Color(255, 255, 255));
		
		JTableTasks = new JTable();
		
		JTableTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTableTasks.setBackground(new Color(255, 255, 255));
		JTableTasks.setGridColor(new Color(255, 255, 255));
		JTableTasks.setShowVerticalLines(false);
		JTableTasks.setSelectionBackground(new Color(204, 255, 204));
		JTableTasks.setRowHeight(50);
		JTableTasks.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, Boolean.FALSE},
				{null, null, null, Boolean.FALSE},
				{null, null, null, Boolean.FALSE},
				{null, null, null, Boolean.FALSE},
				{null, null, null, Boolean.FALSE},
				{null, null, null, null},
			},
			new String[] {
				"Nome", "Descrição", "Prazo", "Tarefa Concluida"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		JTableTasks.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPaneTask.setViewportView(JTableTasks);
		
		JPanel JPanelEmptyList = new JPanel();
		JPanelEmptyList.setBackground(Color.WHITE);
		
		JLabel JlabelEmptyListTitle = new JLabel("Nenhuma tarefa por aqui :D");
		JlabelEmptyListTitle.setHorizontalAlignment(SwingConstants.CENTER);
		JlabelEmptyListTitle.setForeground(new Color(0, 153, 102));
		JlabelEmptyListTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JLabel JlabelEmptyListSubTitle = new JLabel("Clique no botão \"+\" para adicionar uma nova tarefa");
		JlabelEmptyListSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		JlabelEmptyListSubTitle.setForeground(new Color(153, 153, 153));
		JlabelEmptyListSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		JLabel JlabelEmptyListIcon = new JLabel("");
		JlabelEmptyListIcon.setIcon(new ImageIcon("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\lists.png"));
		JlabelEmptyListIcon.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_JPanelEmptyList = new GroupLayout(JPanelEmptyList);
		gl_JPanelEmptyList.setHorizontalGroup(
			gl_JPanelEmptyList.createParallelGroup(Alignment.LEADING)
				.addGap(0, 565, Short.MAX_VALUE)
				.addComponent(JlabelEmptyListTitle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
				.addComponent(JlabelEmptyListSubTitle, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
				.addComponent(JlabelEmptyListIcon, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
		);
		gl_JPanelEmptyList.setVerticalGroup(
			gl_JPanelEmptyList.createParallelGroup(Alignment.LEADING)
				.addGap(0, 486, Short.MAX_VALUE)
				.addGroup(gl_JPanelEmptyList.createSequentialGroup()
					.addGap(110)
					.addComponent(JlabelEmptyListIcon, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JlabelEmptyListTitle, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JlabelEmptyListSubTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(156))
		);
		JPanelEmptyList.setLayout(gl_JPanelEmptyList);
		
		JLabel JLabelTasksTitle = new JLabel("Tarefas");
		JLabelTasksTitle.setForeground(new Color(0, 153, 102));
		JLabelTasksTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel JLabelTasksAdd = new JLabel("");
		JLabelTasksAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TaskDialogScreen taskDialogScreen = new TaskDialogScreen();
				
				
				int projectIndex = JListProjects.getSelectedIndex();
				Project project = (Project) projectsModel.get(projectIndex);
				taskDialogScreen.setProject(project);
				
				taskDialogScreen.setVisible(true);
				
				
				taskDialogScreen.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e ) {
						//initComponetsModel();
						//JTableTasks.setModel(taskModel);
						int projectIndex = JListProjects.getSelectedIndex();
						Project project = (Project) projectsModel.get(projectIndex);
						loadTasks(project.getId());
						
											
						if (hasTasks(project.getId())) {
							if (JPanelEmptyList.isVisible()) {
								JPanelEmptyList.setVisible(false);
								JPanelTableTask.remove(JPanelEmptyList);
								
							}
							JPanelTableTask.add(JScrollPaneTask);
							JScrollPaneTask.setVisible(true);
							JScrollPaneTask.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
							
						}else {
							if(JScrollPaneTask.isVisible()){
								JScrollPaneTask.setVisible(false);
								JPanelTableTask.remove(JScrollPaneTask);
							}	
								JPanelTableTask.add(JPanelEmptyList);
								JPanelEmptyList.setVisible(true);
								JPanelEmptyList.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
							
						}
						
					}
				});
			}
		});
		JLabelTasksAdd.setIcon(new ImageIcon("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\add.png"));
		GroupLayout gl_JPanelTasks = new GroupLayout(JPanelTasks);
		gl_JPanelTasks.setHorizontalGroup(
			gl_JPanelTasks.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_JPanelTasks.createSequentialGroup()
					.addContainerGap()
					.addComponent(JLabelTasksTitle, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
					.addComponent(JLabelTasksAdd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_JPanelTasks.setVerticalGroup(
			gl_JPanelTasks.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelTasks.createSequentialGroup()
					.addGroup(gl_JPanelTasks.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_JPanelTasks.createSequentialGroup()
							.addContainerGap()
							.addComponent(JLabelTasksAdd, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_JPanelTasks.createSequentialGroup()
							.addGap(18)
							.addComponent(JLabelTasksTitle)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		JPanelTasks.setLayout(gl_JPanelTasks);
		
		JLabel JLabelProjectsTitle = new JLabel("Projetos");
		JLabelProjectsTitle.setForeground(new Color(0, 153, 102));
		JLabelProjectsTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel JLabelProjectsAdd = new JLabel("");
		JLabelProjectsAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProjectDialogScreen projectDialogScreen = new ProjectDialogScreen();
				projectDialogScreen.setVisible(true);
				
				projectDialogScreen.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e ) {
						initComponetsModel();
						JListProjects.setModel(projectsModel);
						
					}
				});
				
				
			}
		});
		
		
		
		JLabelProjectsAdd.setIcon(new ImageIcon("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\add.png"));
		GroupLayout gl_JPanelProjects = new GroupLayout(JPanelProjects);
		gl_JPanelProjects.setHorizontalGroup(
			gl_JPanelProjects.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_JPanelProjects.createSequentialGroup()
					.addContainerGap()
					.addComponent(JLabelProjectsTitle)
					.addPreferredGap(ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
					.addComponent(JLabelProjectsAdd)
					.addContainerGap())
		);
		gl_JPanelProjects.setVerticalGroup(
			gl_JPanelProjects.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelProjects.createSequentialGroup()
					.addGroup(gl_JPanelProjects.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_JPanelProjects.createSequentialGroup()
							.addContainerGap()
							.addComponent(JLabelProjectsAdd, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
						.addGroup(gl_JPanelProjects.createSequentialGroup()
							.addGap(18)
							.addComponent(JLabelProjectsTitle)))
					.addContainerGap())
		);
		
		JPanelProjects.setLayout(gl_JPanelProjects);
		contentPane.setLayout(gl_contentPane);
		
		
		
		
		//******************************************************************************//
		
		JListProjects.setModel(projectsModel);
		JTableTasks.setModel(taskModel);
		JPanelTableTask.setLayout(new BorderLayout(0, 0));
		JPanelTableTask.add(JPanelEmptyList);
		JPanelTableTask.add(JScrollPaneTask);
		
		
		JListProjects.setSelectedIndex(0);
		Project project = (Project) projectsModel.get(0);
		loadTasks(project.getId());
		
		if (hasTasks(project.getId())) {
			if (JPanelEmptyList.isVisible()) {
				JPanelEmptyList.setVisible(false);
				JPanelTableTask.remove(JPanelEmptyList);
				
			}
			JPanelTableTask.add(JScrollPaneTask);
			JScrollPaneTask.setVisible(true);
			JScrollPaneTask.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
			
		}else {
			if(JScrollPaneTask.isVisible()){
				JScrollPaneTask.setVisible(false);
				JPanelTableTask.remove(JScrollPaneTask);
			}	
				JPanelTableTask.add(JPanelEmptyList);
				JPanelEmptyList.setVisible(true);
				JPanelEmptyList.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
			
		}
		
		JListProjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int projecIndex = JListProjects.getSelectedIndex();
				Project project = (Project) projectsModel.get(projecIndex);
				loadTasks(project.getId());
				
				if (hasTasks(project.getId())) {
					if (JPanelEmptyList.isVisible()) {
						JPanelEmptyList.setVisible(false);
						JPanelTableTask.remove(JPanelEmptyList);
						
					}
					JPanelTableTask.add(JScrollPaneTask);
					JScrollPaneTask.setVisible(true);
					JScrollPaneTask.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
					
				}else {
					if(JScrollPaneTask.isVisible()){
						JScrollPaneTask.setVisible(false);
						JPanelTableTask.remove(JScrollPaneTask);
					}	
						JPanelTableTask.add(JPanelEmptyList);
						JPanelEmptyList.setVisible(true);
						JPanelEmptyList.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
					
				}
				
			}
		});
		
		JTableTasks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowIndex = JTableTasks.rowAtPoint(e.getPoint());
				int columnIndex = JTableTasks.columnAtPoint(e.getPoint());
				
				Task task = taskModel.getTasks().get(rowIndex);
				
				switch(columnIndex) {
					case 3:
						
						taskController.update(task);
						break;
					case 4:
						
						break;
					case 5:
						
						taskController.removeById(task.getId());
						
						int projectIndex = JListProjects.getSelectedIndex();
						Project project = (Project) projectsModel.get(projectIndex);
						loadTasks(project.getId());
						
						if (hasTasks(project.getId())) {
							if (JPanelEmptyList.isVisible()) {
								JPanelEmptyList.setVisible(false);
								JPanelTableTask.remove(JPanelEmptyList);
								
							}
							JPanelTableTask.add(JScrollPaneTask);
							JScrollPaneTask.setVisible(true);
							JScrollPaneTask.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
							
						}else {
							if(JScrollPaneTask.isVisible()){
								JScrollPaneTask.setVisible(false);
								JPanelTableTask.remove(JScrollPaneTask);
							}	
								JPanelTableTask.add(JPanelEmptyList);
								JPanelEmptyList.setVisible(true);
								JPanelEmptyList.setSize(JPanelTableTask.getWidth(), JPanelTableTask.getHeight());
							
						}
						break;
						
				}
			}
		});
		
		
		decorateTableTask();
				
		
		
		
	}
	
	
   
	
	public void decorateTableTask() {
		
		//Customizando o header da table de tarefas
		JTableTasks.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 14));
		JTableTasks.getTableHeader().setBackground(new Color(0,153,102));
		JTableTasks.getTableHeader().setForeground(new Color(255,255,255));
		
		//Criando um sort automatico para as colunas da table
		//JTableTasks.setAutoCreateRowSorter(true);
		
		JTableTasks.getColumnModel().getColumn(2).setCellRenderer(new DeadLineColumnCellRederer());
		JTableTasks.getColumnModel().getColumn(4).setCellRenderer(new ButtonColumnCellRederer("edit"));
		JTableTasks.getColumnModel().getColumn(5).setCellRenderer(new ButtonColumnCellRederer("delete"));

	}
	
	public void initDataController() {
		projectController = new ProjectController();
		taskController = new TaskController();
	}
	
	public  void initComponetsModel() {
		projectsModel = new DefaultListModel<Project>();
		loadProjects();
		
		taskModel = new TaskTableModel();
		
		 
		
		
	}
	
	public void loadTasks(int idProject) {
		List<Task> tasks = taskController.getAll(idProject);
		taskModel.setTasks(tasks);
		
		
	}
	
	public boolean hasTasks(int idProject) {
		List<Task> tasks = taskController.getAll(idProject);
		taskModel.setTasks(tasks);
		
		return !tasks.isEmpty();
		
		
	}
	
	
	
	
	
	public void loadProjects() {
		List<Project> projects = projectController.getAll();
		
		projectsModel.clear();
		
		for (int i =0; i < projects.size() ; i++) {
			Project project = projects.get(i);
			projectsModel.addElement(project);
			
		}
				
	}
	
	
	

	
	
	
	
}
