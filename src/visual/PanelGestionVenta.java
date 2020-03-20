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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cliente;
import model.Coche;
import model.Concesionario;
import model.Fabricante;
import model.Venta;
import model.controladores.ClienteControlador;
import model.controladores.CocheControlador;
import model.controladores.ConcesionarioControlador;
import model.controladores.FabricanteControlador;
import model.controladores.VentaControlador;
import utils.CacheImagenes;

public class PanelGestionVenta extends JPanel {
	
	public static int LOAD_FIRST = 0;
	public static int LOAD_PREV = 1;
	public static int LOAD_NEXT = 2;
	public static int LOAD_LAST = 3;
	public static int NEW = 4;
	public static int SAVE = 5;
	public static int REMOVE = 6;
	
	JTextField jtfId = new JTextField(5);
	JTextField jtPrecio = new JTextField(10);
	JTextField jtfFecha = new JTextField(10);
	JComboBox<Cliente> jcbCliente = new JComboBox<Cliente>();
	JComboBox<Coche>jcbCoche = new JComboBox<Coche>();
	JComboBox<Concesionario> jcbConcesionario = new JComboBox<Concesionario>();
	Venta actual = null;

	public PanelGestionVenta() {
		actual = VentaControlador.getControlador().findFirst();
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
		
		// Incluir campo Fecha de la venta
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Precio de la venta: "), c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtfFecha, c);
		
		// Incluir campo Precio
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Precio de la venta: "), c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		this.add(jtPrecio, c);
		
		// Incluir campo id del cliente
		List<Cliente> clientes = ClienteControlador.getControlador().findAll();
		for (Cliente cli : clientes) {
			jcbCliente.addItem(cli);
		}
		
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Id Cliente: "), c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbCliente, c);
		
		// Incluir campo id del coche
		List<Coche> coche = new CocheControlador().getControlador().findAll();
		for (Coche co : coche) {
			jcbCoche.addItem(co);
		}
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Id Coche: "), c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbCoche, c);
		
		// Incluir JComboBox (desplegable) para el concesionario
		List<Concesionario> concesionarios = ConcesionarioControlador.getControlador().findAll();
		
		for (Concesionario con : concesionarios) {
			jcbConcesionario.addItem(con);
		}
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.EAST;
		this.add(new JLabel("Id Concesionario: "), c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		this.add(jcbConcesionario, c);
		
		// Panel Navegación
		
		c.gridx = 0;
		c.gridy = 7;
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
				Venta obtenido = null;
				if(funcion == LOAD_FIRST) {
					obtenido = VentaControlador.getControlador().findFirst();
				}
				if(funcion == LOAD_PREV) {
					obtenido = VentaControlador.getControlador().findPrevious(actual);
				}
				if(funcion == LOAD_NEXT) {
					obtenido = VentaControlador.getControlador().findNext(actual);
				}
				if(funcion == LOAD_LAST) {
					obtenido = VentaControlador.getControlador().findLast();
				}
				if(funcion == NEW) {
					nuevo();
				}
				if(funcion == SAVE) {
					try {
						guardar();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (this.actual != null) {
			this.jtfId.setText("" + this.actual.getId());
			this.jtfFecha.setText(sdf.format(this.actual.getFecha()));
			this.jtPrecio.setText(Float.toString(this.actual.getPrecioVenta()));
			this.jcbCliente.setSelectedItem(this.actual.getCliente());
			this.jcbCoche.setSelectedItem(this.actual.getCoche());
			this.jcbConcesionario.setSelectedItem(this.actual.getConcesionario());
			
		}
		
	}

	protected Venta eliminar() {
		String respuestas [] = new String[] {"Sí", "No"};
		int opcionElegida = JOptionPane.showOptionDialog(null, "¿Realmente quiere eliminar el registro?", "Eliminar registro",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.OK_CANCEL_OPTION,
				CacheImagenes.getCacheImagenes().getIcono("confirm.png"),
				respuestas, respuestas[1]);
		
		if(opcionElegida == 0) {
			Venta nuevoAMostrar = VentaControlador.getControlador().findPrevious(actual);
			if(nuevoAMostrar == null) {
				nuevoAMostrar = VentaControlador.getControlador().findNext(actual);
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
	 * @throws ParseException 
	 * 
	 */
	private void guardar() throws ParseException {
		Venta nuevoRegistro = new Venta();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(this.jtfId.getText().trim().equals("")) {
			nuevoRegistro.setId(0);
		}
		else {
			nuevoRegistro.setId(Integer.parseInt(this.jtfId.getText()));
		}
		nuevoRegistro.setFecha(sdf.parse(this.jtfFecha.getText()));
		nuevoRegistro.setPrecioVenta(Float.parseFloat(this.jtPrecio.getText()));
		
		nuevoRegistro.setCliente((Cliente) this.jcbCliente.getSelectedItem());
		nuevoRegistro.setCoche((Coche) this.jcbCoche.getSelectedItem());
		nuevoRegistro.setConcesionario((Concesionario) this.jcbConcesionario.getSelectedItem());
		
		
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
		this.jtfFecha.setText("");
		this.jtPrecio.setText("");
		this.jcbCliente.setSelectedIndex(0);
		this.jcbCoche.setSelectedIndex(0);
		this.jcbConcesionario.setSelectedIndex(0);
		
	}

	public PanelGestionVenta(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionVenta(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PanelGestionVenta(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
