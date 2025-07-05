package Negocio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author BRANDON OÑA
 */

public class ColorFilasReserva extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table,Object value, boolean 
            selected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table,value,selected,hasFocus,row,column);
        Object valor = table.getValueAt(row,2);
        
        if(valor == null){
                //setBackground(new Color (108,245,5,200));
                setBackground(Color.green);
                setForeground(Color.BLACK);
                setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
            }else{
                setBackground(Color.red);
                //setBackground(new Color(235,24,20,250));
                setForeground(Color.BLACK);
                setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
            }
        return this;
    }
}
