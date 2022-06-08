/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unal.unal_ciclo2clase25_grupo2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Usuario
 */
public class Menu extends JFrame {

    JMenuBar menu;
    JMenu inicio;
    JMenu acercade;

    JMenuItem crear;
    JMenuItem buscar;
    JMenuItem actualizar;
    JMenuItem eliminar;
    JMenuItem cerrar;
    JMenuItem efe1;

    JPanel panelAgregar;
    JPanel panelBuscar;
    JPanel panelActualizar;
    JPanel panelEliminar;
    JPanel panelCerrar;

    JLabel L_nombre;
    JLabel L_direccion;
    JLabel L_telefono;

    JTextField T_nombre;
    JTextField T_telefono;
    JTextField T_direccion;

    JButton guardar;
    JButton limpiar;

    JLabel L_buscarpor;
    JComboBox C_buscarpor;
    JTable tablaresult;
    JButton B_buscar;

    Menu() {
        this.setSize(800, 600);
        this.getContentPane();

        menu = new JMenuBar();
        inicio = new JMenu("Actividad");
        acercade = new JMenu("Acerca de...");
        efe1 = new JMenuItem("Ayuda");

        crear = new JMenuItem("Agregar Nuevo");
        buscar = new JMenuItem("Buscar");
        actualizar = new JMenuItem("Modificar");
        eliminar = new JMenuItem("Eliminar");
        cerrar = new JMenuItem("Cerrar");

        inicio.add(crear);
        inicio.add(buscar);
        inicio.add(actualizar);
        inicio.add(eliminar);
        inicio.add(cerrar);

        acercade.add(efe1);

        menu.add(inicio);
        menu.add(acercade);

        this.setJMenuBar(menu);

        panelAgregar = this.agregarPanelCrear();
        this.add(panelAgregar);
        this.panelAgregar.setVisible(false);

        panelBuscar = this.agregarPanelBuscar();

        this.guardar.addActionListener((ActionEvent e0) -> {
            guardarDatos();
        });

        this.efe1.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(rootPane,
                    "Software desarrollado por MISION TIC 2022");
        });

        crear.addActionListener((ActionEvent e1) -> {
            repintarFrame1();
        });
        buscar.addActionListener((ActionEvent e2) -> {
            repintarFrame2();
        });

        cerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
        cerrar.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel agregarPanelCrear() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);

        L_nombre = new JLabel("Nombre:");
        L_direccion = new JLabel("Dirección:");
        L_telefono = new JLabel("Telefono:");

        T_nombre = new JTextField();
        T_direccion = new JTextField();
        T_telefono = new JTextField();

        guardar = new JButton("Guardar");
        limpiar = new JButton("Limpiar");

        L_nombre.setBounds(40, 40, 80, 20);
        panel1.add(L_nombre);
        T_nombre.setBounds(120, 40, 100, 20);
        panel1.add(T_nombre);

        L_direccion.setBounds(40, 70, 80, 20);
        panel1.add(L_direccion);
        T_direccion.setBounds(120, 70, 100, 20);
        panel1.add(T_direccion);

        L_telefono.setBounds(40, 100, 80, 20);
        panel1.add(L_telefono);
        T_telefono.setBounds(120, 100, 100, 20);
        panel1.add(T_telefono);

        guardar.setBounds(230, 40, 90, 25);
        panel1.add(guardar);
        limpiar.setBounds(230, 80, 90, 25);
        panel1.add(limpiar);

        return panel1;
    }

    //--------------------------------------------------------
    public JPanel agregarPanelBuscar() {
        JPanel panel2 = new JPanel();
        JScrollPane scroll;
        panel2.setLayout(new FlowLayout());

        Guardar g = new Guardar();
        g.conectar();
        String datos[][]=g.leerBD();
        
        String[] titulos = {"Nombre", "Correo Electronico", "Telefono"};

        this.tablaresult = new JTable(datos, titulos);//datos matriz y luego arreglo
        scroll= new JScrollPane(this.tablaresult);
        this.L_buscarpor = new JLabel("Buscar por:");

        String opciones[]={"Nombre","email","tel"};
        this.C_buscarpor = new JComboBox(opciones);
        this.C_buscarpor.addItem("Nombre");
        this.C_buscarpor.addItem("E-Mail");
        this.C_buscarpor.addItem("Telefono");

        this.B_buscar = new JButton("Buscar");

        panel2.add(L_buscarpor);
        panel2.add(C_buscarpor);
        panel2.add(B_buscar);
        panel2.add(scroll);

        return panel2;
    }

    //---------------------------------------------------------
    public void guardarDatos() {
        if (!this.T_nombre.getText().equals("")
                && !this.T_direccion.getText().equals("")
                && !this.T_telefono.getText().equals("")) {
            Guardar G = new Guardar();
            G.conectar();
            Integer res = G.guardar(this.T_nombre.getText(),
                    this.T_direccion.getText(),
                    this.T_telefono.getText());

            if (res == 1) {
                JOptionPane.showMessageDialog(rootPane,
                        "Exito al guardar");
            } else {
                JOptionPane.showMessageDialog(rootPane,
                        "Problema al guardar");
            }
        } else {
            JOptionPane.showConfirmDialog(rootPane,
                    "Datos Vacios, complete por favor");
        }
    }

    //----------------------------------------------------------
    public void limpiarDatos() {
        this.T_direccion.setText("");
        this.T_nombre.setText("");
        this.T_telefono.setText("");
    }

    //-------------------------------------------------------------
    public void repintarFrame1() {
        this.getContentPane().removeAll();
        this.add(this.panelAgregar);
        this.panelAgregar.setVisible(true);
        this.repaint();
    }

    public void repintarFrame2() {
        this.getContentPane().removeAll();
        this.add(this.panelBuscar);
        this.panelBuscar.setVisible(true);
        this.repaint();
    }

   
}
