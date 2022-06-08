/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.unal.unal_ciclo2clase25_grupo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Guardar {

    public Connection conn;

    public Connection conectar() {
        String dbURL = "jdbc:mysql://localhost:3306/mydb";
        String username = "admin";
        String password = "Admin123#";

        conn = null;
        // conectar
        try {
            conn = DriverManager.getConnection(
                    dbURL, username, password);
            if (conn != null) {
                //System.out.println(" Conectado ");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    //-------------------------------------------------------
    public Integer guardar(String nombre, String direccion,
            String telefono) {

        String sql = "INSERT INTO tenedor "
                + "(nombre,direccion,telefono) "
                + "VALUES (?,?,?)";

        Integer rowsInserted = 0;
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.setString(3, telefono);

            rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return rowsInserted;

    }

    //---------------------------------
    public String[][] leerBD() {
        String result[][] = null;

        String sql = "SELECT * FROM tenedor";
        String sqltam = "SELECT count(*) FROM tenedor";
        Statement statement;
        Statement statement2;
        ResultSet resulttam;
        int tamano=0;
        try {
            statement = conn.createStatement();
            resulttam = statement.executeQuery(sqltam);
            while (resulttam.next()) {
                tamano = resulttam.getInt(1);
                System.out.println(tamano);
            }
            int fila = 0;

            result = new String[tamano][3];//filas y columnas
            statement2 = conn.createStatement();
            ResultSet resultsql = statement2.executeQuery(sql);
            while (resultsql.next()) {
                result[fila][0] = resultsql.getString(2);
                result[fila][1] = resultsql.getString(3);
                result[fila][2] = resultsql.getString(4);
                System.out.println(result[fila][0]);
                System.out.println(result[fila][1]);
                System.out.println(result[fila][2]);
                fila++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return result;
    }
}
