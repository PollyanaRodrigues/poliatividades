package view;

import java.awt.BorderLayout;
	


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JFormattedTextField;
import javax.swing.DropMode;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TaskDialogScreen extends JDialog {
	private JTextField JTextFieldName;
	TaskController controller ;
	Project project;
	
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TaskDialogScreen dialog = new TaskDialogScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setProject(Project project){
		this.project = project;
	}

	/**
	 * Create the dialog.
	 */
	public Date dateFormatter(String d) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(d);
		
		return date;
	}
	
	
	public TaskDialogScreen() {
		
		controller = new TaskController();
		setModal(true);
		setBounds(100, 100, 450, 711);
		
		
		JPanel JPanelToolBar = new JPanel();
		JPanelToolBar.setBackground(new Color(0, 153, 102));
		
		JLabel JLabelToolBarTitle = new JLabel("Tarefas");
		JLabelToolBarTitle.setForeground(Color.WHITE);
		JLabelToolBarTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JLabel JLabelToolBarSave = new JLabel("");
		
		
		JLabelToolBarSave.setIcon(new ImageIcon("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\check.png"));
		JLabelToolBarSave.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_JPanelToolBar = new GroupLayout(JPanelToolBar);
		gl_JPanelToolBar.setHorizontalGroup(
			gl_JPanelToolBar.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 436, Short.MAX_VALUE)
				.addGroup(gl_JPanelToolBar.createSequentialGroup()
					.addContainerGap()
					.addComponent(JLabelToolBarTitle, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(JLabelToolBarSave, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_JPanelToolBar.setVerticalGroup(
			gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
				.addGap(0, 66, Short.MAX_VALUE)
				.addGroup(gl_JPanelToolBar.createSequentialGroup()
					.addGroup(gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_JPanelToolBar.createSequentialGroup()
							.addContainerGap()
							.addComponent(JLabelToolBarTitle, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
						.addGroup(gl_JPanelToolBar.createSequentialGroup()
							.addGap(9)
							.addComponent(JLabelToolBarSave, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		JPanelToolBar.setLayout(gl_JPanelToolBar);
		
		JPanel JPanelTask = new JPanel();
		JPanelTask.setBackground(Color.WHITE);
		
		JLabel JLabelName = new JLabel("Nome");
		JLabelName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextFieldName = new JTextField();
		JTextFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JTextFieldName.setColumns(10);
		
		JLabel JLabelDescription = new JLabel("Descrição");
		JLabelDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JScrollPane JScrollPaneDescription = new JScrollPane();
		
		JLabel JLabelDeadLine = new JLabel("Prazo");
		JLabelDeadLine.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JLabel JLabelNotes = new JLabel("Notas");
		JLabelNotes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JScrollPane JScrollPaneNotes = new JScrollPane();
		
		
		
		JFormattedTextField JFormattedTextFieldDeadLine = new JFormattedTextField();
		GroupLayout gl_JPanelTask = new GroupLayout(JPanelTask);
		gl_JPanelTask.setHorizontalGroup(
			gl_JPanelTask.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_JPanelTask.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_JPanelTask.createParallelGroup(Alignment.TRAILING)
						.addComponent(JFormattedTextFieldDeadLine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JScrollPaneNotes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JScrollPaneDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JLabelName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JTextFieldName, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JLabelDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JLabelDeadLine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JLabelNotes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_JPanelTask.setVerticalGroup(
			gl_JPanelTask.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelTask.createSequentialGroup()
					.addGap(15)
					.addComponent(JLabelName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JTextFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(JLabelDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JScrollPaneDescription, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(JLabelDeadLine)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JFormattedTextFieldDeadLine, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(JLabelNotes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JScrollPaneNotes, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea JTextAreaNotes = new JTextArea();
		
		JTextAreaNotes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPaneNotes.setViewportView(JTextAreaNotes);
		
		
		
		
		
		JTextArea JTextAreaDescription = new JTextArea();
		JTextAreaDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPaneDescription.setViewportView(JTextAreaDescription);
		JPanelTask.setLayout(gl_JPanelTask);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(JPanelToolBar, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(JPanelTask, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		
		JLabelToolBarSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try{
					if(!JTextFieldName.getText().equals("") || !JFormattedTextFieldDeadLine.getText().equals("")) {
						Task task = new Task();
						
						task.setIdProject(project.getId()); // project.setId()
						
						task.setName(JTextFieldName.getText());
						task.setDescription(JTextAreaDescription.getText());
						task.setCompleted(false);
						task.setNotes(JTextAreaNotes.getText());
								
						task.setDeadline(dateFormatter(JFormattedTextFieldDeadLine.getText())); 
										
						controller.save(task);
						JOptionPane.showMessageDialog(rootPane,"Tarefa salva com sucesso!");
						
						dispose();
					}else {
						JOptionPane.showMessageDialog(rootPane, "Tarefa não foi salva! Campo Nome ou Prazo não preenchidos");
					}
				}catch (Exception ex){
					JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				}
					
				
				
				
				
			}
		});
		
		
		
		
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(JPanelToolBar, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JPanelTask, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		
		
		
		getContentPane().setLayout(groupLayout);
	}
}
