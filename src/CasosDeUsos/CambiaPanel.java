/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasosDeUsos;

/**
 *
 * @author RojeruSan
 */
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class CambiaPanel {
    //
    private JDesktopPane container;
    private JDesktopPane content;


    /**
     * Constructor de clase
     */
    public CambiaPanel(JDesktopPane container, JDesktopPane content) {
        this.container = container;
        this.content = content;
        this.container.removeAll();
        this.container.revalidate();
        this.container.repaint();
        
        this.container.add(this.content);
        this.container.revalidate();
        this.container.repaint();
    }

}//--> fin clase
