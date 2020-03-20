package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import utils.CacheImagenes;

public class ToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;
	

	public ToolBar() {
		this.add(crearBoton(0, "", "conectado.png", "Ir a Concesionario"));
		this.add(crearBoton(1, "", "conectado.png", "Ir a Fabricante"));
		this.add(crearBoton(2, "", "conectado.png", "Ir a Cliente"));
		this.add(crearBoton(3, "", "conectado.png", "Ir a Coche"));
		this.add(crearBoton(4, "", "conectado.png", "Ir a Ventas"));
	}

	private JButton crearBoton(int num, String titulo, String icono, String toolTip) {
		JButton jbt = new JButton();
		
		jbt.setText(titulo);
		jbt.setToolTipText(toolTip);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal.jTabbedPane.setSelectedIndex(num);
				System.out.println("Has hecho click en el botón: \"" + toolTip + "\"");
			}
			
		});
		
		try {
			jbt.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jbt;
	}
	
	

	public ToolBar(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ToolBar(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ToolBar(String arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
