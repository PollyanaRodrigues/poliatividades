package view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProjectController;
import model.Project;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectDialogScreen extends JDialog {
	private JTextField JTextFieldName;
	ProjectController controller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProjectDialogScreen dialog = new ProjectDialogScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProjectDialogScreen() {
		
		
		controller = new ProjectController();
		setModal(true);
		setBounds(100, 100, 450, 469);
		
		JPanel JPanelToolBar = new JPanel();
		JPanelToolBar.setBackground(new Color(0, 153, 102));
		
		JPanel JPanelProject = new JPanel();
		JPanelProject.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(JPanelToolBar, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(JPanelProject, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(JPanelToolBar, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JPanelProject, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel JLabelName = new JLabel("Nome");
		JLabelName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JTextFieldName = new JTextField();
		JTextFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JTextFieldName.setColumns(10);
		
		JLabel JLabelDescription = new JLabel("Descrição");
		JLabelDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JScrollPane JScrollPaneDescription = new JScrollPane();
		GroupLayout gl_JPanelProject = new GroupLayout(JPanelProject);
		gl_JPanelProject.setHorizontalGroup(
			gl_JPanelProject.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_JPanelProject.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_JPanelProject.createParallelGroup(Alignment.TRAILING)
						.addComponent(JScrollPaneDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JLabelName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JTextFieldName, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addComponent(JLabelDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_JPanelProject.setVerticalGroup(
			gl_JPanelProject.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelProject.createSequentialGroup()
					.addGap(15)
					.addComponent(JLabelName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JTextFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(JLabelDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(JScrollPaneDescription, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextArea JTextAreaDescription = new JTextArea();
		JTextAreaDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		JScrollPaneDescription.setViewportView(JTextAreaDescription);
		JPanelProject.setLayout(gl_JPanelProject);
		
		JLabel JLabelToolBarTitle = new JLabel("Projeto");
		JLabelToolBarTitle.setForeground(new Color(255, 255, 255));
		JLabelToolBarTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JLabel JLabelToolBarSave = new JLabel("");
		JLabelToolBarSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					if(!JTextFieldName.getText().equals("")) {
						Project project = new Project();
						project.setName(JTextFieldName.getText());
						project.setDescription(JTextAreaDescription.getText());
						controller.save(project);
						JOptionPane.showMessageDialog(rootPane,"Projeto salvo com sucesso!");
						
						dispose();
					}else {
						JOptionPane.showMessageDialog(rootPane, "O projeto não foi salvo, pois o campo nome não foi preenchido");
					}
					
					
					
				}catch (Exception ex){
					JOptionPane.showMessageDialog(rootPane, ex.getMessage());
				}
				
				
				
				
			}
		});
		JLabelToolBarSave.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelToolBarSave.setIcon(new ImageIcon("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\check.png"));
		GroupLayout gl_JPanelToolBar = new GroupLayout(JPanelToolBar);
		gl_JPanelToolBar.setHorizontalGroup(
			gl_JPanelToolBar.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_JPanelToolBar.createSequentialGroup()
					.addContainerGap()
					.addComponent(JLabelToolBarTitle, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(JLabelToolBarSave, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_JPanelToolBar.setVerticalGroup(
			gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JPanelToolBar.createSequentialGroup()
					.addGroup(gl_JPanelToolBar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_JPanelToolBar.createSequentialGroup()
							.addContainerGap()
							.addComponent(JLabelToolBarTitle, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
						.addGroup(gl_JPanelToolBar.createSequentialGroup()
							.addGap(9)
							.addComponent(JLabelToolBarSave, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		JPanelToolBar.setLayout(gl_JPanelToolBar);
		getContentPane().setLayout(groupLayout);
	}
}
