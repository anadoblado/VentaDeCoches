package visual;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.MenuBar;

import javax.swing.*;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;


import utils.Apariencia;
import utils.CacheImagenes;

public class VentanaPrincipal extends JFrame {

	public static int ANCHO = 800;
	public static int ALTO = 600;
	public static String TITULO_APLICACION = "Gesti�n de venta de coches";

	private CacheImagenes cacheImagenes;
	public static BufferedImage iconoApp;
	
	static JTabbedPaneGestion jTabbedPane = new JTabbedPaneGestion();
	

	// Establecer la apariencia t�pica de Windows
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}
	
	
	
	public VentanaPrincipal () {
		super (TITULO_APLICACION);
		
		cacheImagenes = new CacheImagenes();
		iconoApp = cacheImagenes.getImagen("nave.gif");
		setIconImage(iconoApp);
		
		this.setExtendedState(JFrame.NORMAL);
		this.setMinimumSize(new Dimension(ANCHO, ALTO));
		
		agregarGestionCierreAplicacion();
		
		// Construcci�n elementos b�sicos sobre el ContentPanel
		//this.setContentPane(new JTabbedPaneGestion());
		this.setJMenuBar(new Menu());
		this.add(new ToolBar(), BorderLayout.NORTH);
		this.getContentPane().add(jTabbedPane, BorderLayout.CENTER);
		
		// A�adir el MenuBar
		//this.setJMenuBar(getJMyMenuBar());
	}

	
	
		
	private JMenuBar getJMyMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuGestion = new JMenu("Desplegar Gesti�n");
		
		String opciones[] = new String[] {"Concesionario", "Fabricante", "Cliente", "Coche", "Venta" };
		for (int i = 0; i < opciones.length; i++) {
			final int iFinal = i;
			JMenuItem item = new JMenuItem(opciones[i]);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jTabbedPane.setSelectedIndex(iFinal);
					
				}
			});
			menuGestion.add(item);
		}
		menuBar.add(menuGestion);
		return menuBar;
	}




	/**
	 * 
	 */
	private void agregarGestionCierreAplicacion () {
		// Configuraci�n del evento de cerrado
		// Para m�s informaci�n debes estudiar Javadoc WindowListener y WindowAdapter
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				String posiblesRespuestas[] = {"S�","No"};
				// En esta opci�n se utiliza un showOptionDialog en el que personalizo el icono mostrado
				int opcionElegida = JOptionPane.showOptionDialog(null, "�Realmente desea cerrar la aplicaci�n?", TITULO_APLICACION, 
				        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, cacheImagenes.getIcono("confirm.png"), posiblesRespuestas, posiblesRespuestas[1]);
			    if(opcionElegida == 0) {
			      System.exit(0);          
			    }
			}
		});
	}
}
