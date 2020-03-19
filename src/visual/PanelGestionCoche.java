package visual;

import java.awt.Color;


import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Coche;
import model.Fabricante;
import model.controladores.CocheControlador;
import model.controladores.FabricanteControlador;
import utils.CacheImagenes;

public class PanelGestionCoche extends JPanel {
	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	JTextField jtfId = new JTextField(5);
	JTextField jtBastidor = new JTextField(30);
	JTextField jtfModelo = new JTextField(10);
	JTextField jtfColor = new JTextField(10);
	JComboBox<Fabricante> jcbFabricante = new JComboBox<Fabricante>(); 
	Coche actual = null;

	public PanelGestionCoche() {
		actual = CocheControlador.getControlador().findFirst();
		construir();
		cargarDatosActual();
	}

	private void construir() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Incluir panel de navegación
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.insets = new Insets(0, 0, 25, 0);
		this.add(getPanelNavegacion(), c);
		
		//Incluir campo Identificador
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 2, 2, 2);
		this.add(new JLabel("Identificador: "), c);
		
		c.gridx = 1;
		c.gridy = 1;
		jtfId.setEnabled(false);
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfId, c);
		
		// Incluir campo Bastidor
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Bastidor: "), c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtBastidor, c);
		
		// Incluir campo Color
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Color: "), c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfColor, c);
		
		// Incluir campo Modelo
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Modelo: "), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfModelo, c);
		
		// Incluir JComboBox (desplegable) para el Fabricante
		List<Fabricante> fabricantes = FabricanteControlador.getControlador().findAll();
		
		for (Fabricante fab : fabricantes) {
			jcbFabricante.addItem(fab);
		}
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Fabricante: "), c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbFabricante, c);
		
		// Panel Navegación
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 0, 0, 0);
		this.add(getPanelAcciones(), c);
		
	}

	private Component getPanelNavegacion() {
		JPanel pnl = new JPanel();
		
		pnl.setBackground(Color.RED);
		
		JButton jbtPrimero = new JButton("Primero");
		asignarFuncion(jbtPrimero, LOAD_FIRST);
		
		JButton jbtAnterior = new JButton("Anterior");
		asignarFuncion(jbtAnterior, LOAD_PREV);
		
		JButton jbtSiguiente = new JButton("Siguiente");
		asignarFuncion(jbtSiguiente, LOAD_NEXT);

		JButton jbtUltimo = new JButton("Ultimo");
		asignarFuncion(jbtUltimo, LOAD_LAST);
		
		pnl.setLayout(new FlowLayout());
		pnl.add(jbtPrimero);
		pnl.add(jbtAnterior);
		pnl.add(jbtSiguiente);
		pnl.add(jbtUltimo);

		
		return pnl;
	}
	
	private JPanel getPanelAcciones() {
		JPanel pnl = new JPanel();
		pnl.setBackground(Color.red);
		
		JButton jbtNuevo = new JButton("Nuevo");
		asignarFuncion(jbtNuevo, NEW);
		
		JButton jbtGuardar = new JButton("Modificar");
		asignarFuncion(jbtGuardar, SAVE);
		
		JButton jbtEliminar = new JButton("Eliminar");
		asignarFuncion(jbtEliminar, REMOVE);
		
		pnl.setLayout(new FlowLayout());
		pnl.add(jbtNuevo);
		pnl.add(jbtGuardar);
		pnl.add(jbtEliminar);
		
		return pnl;
		
	}

	private void asignarFuncion(JButton jbt, final int funcion) {
		jbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Coche obtenido = null;
				if(funcion == LOAD_FIRST) {
					obtenido = CocheControlador.getControlador().findFirst();
				}
				if(funcion == LOAD_PREV) {
					obtenido = CocheControlador.getControlador().findPrevious(actual);
				}
				if(funcion == LOAD_NEXT) {
					obtenido = CocheControlador.getControlador().findNext(actual);
				}
				if(funcion == LOAD_LAST) {
					obtenido = CocheControlador.getControlador().findLast();
				}
				if(funcion == NEW) {
					nuevo();
				}
				if(funcion == SAVE) {
					guardar();
				}
				if(funcion == REMOVE) {
					eliminar();
				}
				
				if(obtenido != null) {
					actual = obtenido;
					cargarDatosActual();
				}
				
			}

			
		});
		
	}
	
	protected void cargarDatosActual() {
		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtBastidor.setText(this.actual.getBastidor());
			this.jtfColor.setText(this.actual.getColor());
			this.jtfModelo.setText(this.actual.getModelo());
			this.jcbFabricante.setSelectedItem(this.actual.getFabricante());
		}
		
	}

	protected Coche eliminar() {
		String respuestas [] = new String[] {"Sí", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente quiere eliminar el registro?", "Eliminar registro",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"),
				respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Coche nuevoAMostrar = CocheControlador.getControlador().findPrevious(actual);
			if(nuevoAMostrar == null) {
				nuevoAMostrar = CocheControlador.getControlador().findNext(actual);
			}
			CocheControlador.getControlador().remove(actual);
			JOptionPane.showMessageDialog(this, "Eliminado correctamente");
			
			if(nuevoAMostrar != null) {
				actual = nuevoAMostrar;
			}
			else {
				limpiarPantalla();
			}
		}
		return actual;
		
		
	}

	/**
	 * 
	 */
	private void guardar() {
		Coche nuevoRegistro = new Coche();
		
		if(this.jtfId.getText().trim().equals("")) {
			nuevoRegistro.setId(0);
		}
		else {
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));
		}
		
		nuevoRegistro.setBastidor(this.jtBastidor.getText());
		nuevoRegistro.setColor(this.jtfColor.getText());
		nuevoRegistro.setModelo(this.jtfModelo.getText());
		nuevoRegistro.setFabricante((Fabricante) this.jcbFabricante.getSelectedItem());
		
		if (nuevoRegistro.getId() == 0) {
			CocheControlador.getControlador().persist(nuevoRegistro);
		}
		else {
			CocheControlador.getControlador().merge(nuevoRegistro);
		}
		this.jtfId.setText("" + nuevoRegistro.getId());
		JOptionPane.showMessageDialog(this, "Guardado correctamente");
		this.actual = nuevoRegistro;
		
	}

	private void nuevo() {
		limpiarPantalla();
		JOptionPane.showMessageDialog(this, "Introduzca los datos");
		
	}

	/**
	 * 
	 */
	private void limpiarPantalla() {
		this.jtfId.setText("");
		this.jtBastidor.setText("");
		this.jtfColor.setText("");
		this.jtfModelo.setText("");
		this.jcbFabricante.setSelectedIndex(0);
		
	}

	public PanelGestionCoche(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionCoche(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionCoche(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
