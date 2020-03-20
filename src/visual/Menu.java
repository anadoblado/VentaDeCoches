package visual;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import utils.CacheImagenes;

public class Menu extends JMenuBar {
	
	//JTabbedPane jTabbedPane = new JTabbedPane();

	public Menu mi0 = null;
	Menu mi1 = null;
	Menu mi2 = null;
	Menu mi3 = null;
	Menu mi4 = null;
	
	public Menu() {
		// Menú para la Gestión
		JMenu menuGes = new JMenu("Gestion");
	    
//	    mi0.crearNuevoMenuItem(0, "Concesionario", "ruedadentada.png", KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//	    
//    	mi1.crearNuevoMenuItem(1, "Fabricante", "desconectado.png", KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//    	
//    	mi2.crearNuevoMenuItem(2, "Cliente", "next.png", KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//    	
//    	mi3.crearNuevoMenuItem(3, "Coche", "conectado.png", KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//    	
//    	mi4.crearNuevoMenuItem(4, "Venta", "conectado.png", KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//	
//    	menuGes.add(mi0);
//    	menuGes.add(mi1);
//    	menuGes.add(mi2);
//    	menuGes.add(mi3);
//    	menuGes.add(mi4);
//    	
	    menuGes.add(crearNuevoMenuItem(0, "Concesionario", "ruedadentada.png", KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		menuGes.add(crearNuevoMenuItem(1, "Fabricante", "desconectado.png", KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		menuGes.add(crearNuevoMenuItem(2, "Cliente", "next.png", KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		menuGes.add(crearNuevoMenuItem(3, "Coche", "conectado.png", KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		menuGes.add(crearNuevoMenuItem(4, "Venta", "conectado.png", KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())));
		
		this.add(menuGes);
	}

	/**
	 * 
	 * @param titulo
	 * @param nombreIcono
	 * @param atajoTeclado
	 * @return
	 */
	private JMenuItem crearNuevoMenuItem( int num, String titulo, String nombreIcono, KeyStroke atajoTeclado) {
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(titulo);
		item.setIcon(CacheImagenes.getCacheImagenes().getIcono(nombreIcono));
		item.setAccelerator(atajoTeclado);
		
		item.addActionListener(new ActionListener() 
		{
			  @Override
	            public void actionPerformed(ActionEvent e) {
				  
				  VentanaPrincipal.jTabbedPane.setSelectedIndex(num);
	               System.out.println("Han hecho clic en: " + titulo);
	                
	                //JTabbedPane jtbp = new JTabbedPane();
	                //if (e.getSource()==mi0) jtbp.getSelectedComponent(JTabb);
	               
	                
	                
//	                for (int i = 0; i < getMenuCount(); i++) {
//	                	JTabbedPaneGestion.getJTabbedPaneGestion().setSelectedIndex(i);
//
//					}
	                
					
					
	            }
		});
		
		return item;
	}

}
