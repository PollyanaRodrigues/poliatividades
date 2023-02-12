package util;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ButtonColumnCellRederer extends DefaultTableCellRenderer {

	private String buttonType;
	
	

	public ButtonColumnCellRederer(String buttonType) {
		this.buttonType = buttonType;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
	
	@Override
	 public Component getTableCellRendererComponent(JTable table, Object value,
             boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		label.setHorizontalAlignment(CENTER);
		
		label.setIcon(new ImageIcon(("C:\\Users\\polly\\Documents\\wp-polly\\PoliAtividades\\src\\resources\\"+buttonType+".png")));
		return label;
	}
}
