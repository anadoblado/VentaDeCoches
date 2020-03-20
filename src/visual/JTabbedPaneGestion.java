package visual;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import utils.CacheImagenes;

public class JTabbedPaneGestion extends JTabbedPane {

	public static JTabbedPaneGestion llamada = null;

	/**
	 * 
	 * @return
	 */
	public JTabbedPaneGestion (){
	
		ImageIcon icono = CacheImagenes.getCacheImagenes().getIcono("duke1-32x32.png");
	
	
		this.addTab("Concesionario", icono, new PanelGestionConcesionario(), "Concesionario");
		this.addTab("Fabricante", icono, new PanelGestionFabricante(), "Fabricante");
		this.addTab("Cliente", icono, new PanelGestionCliente(), "Cliente");
		this.addTab("Coche", icono, new PanelGestionCoche(), "Coche");
		this.addTab("Venta", icono, new PanelGestionVenta(), "Venta");
		
		this.setSelectedIndex(0);  
	}	
	
	public static JTabbedPaneGestion getJTabbedPaneGestion() {
		if (llamada == null) {
			llamada = new JTabbedPaneGestion();
		}
		return llamada;
	}
}
