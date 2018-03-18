/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.modelo.Jugadores;
import com.sv.udb.modelo.Equipos;
import com.sv.udb.recursos.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raul
 */
public class ControlJugador {

    private final Connection conn;

    public ControlJugador() {
        conn = new ConnectionDB().getConn();
    }

    public boolean insertar(Equipos equipo, String nombre, int edad, double altura, double peso) {
        boolean resp = false;
        try {
            PreparedStatement cmd = conn.prepareStatement("INSERT INTO jugadores VALUES (NULL, ?, ?, ?, ?, ?)");
            cmd.setInt(1, equipo.getId());
            cmd.setString(2, nombre);
            cmd.setInt(3, edad);
            cmd.setDouble(4, altura);
            cmd.setDouble(5, peso);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al guardar jugador: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión al guardar jugador: " + e.getMessage());
            }
        }
        return resp;
    }

    public List<Jugadores> getAll() {
        List<Jugadores> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = conn.prepareStatement("SELECT ju.codi_juga, eq.*, ju.nomb_juga, "
                    + "ju.edad_juga, ju.altu_juga, ju.peso_juga FROM jugadores ju INNER JOIN equipos eq "
                    + "ON ju.codi_equi = eq.codi_equi;");
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                resp.add(new Jugadores(
                        rs.getInt(1),
                        new Equipos(rs.getInt(2), rs.getString(3), rs.getString(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getDouble(8)));
            }
        } catch (Exception e) {
            System.err.println("Error al consultar jugadores: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resp;
    }

    public Jugadores consultar(int code) {
        Jugadores player = null;
        try {
            PreparedStatement cmd = conn.prepareStatement("SELECT ju.codi_juga, eq.*, ju.nomb_juga, "
                    + "ju.edad_juga, ju.altu_juga, ju.peso_juga FROM jugadores ju INNER JOIN equipos eq "
                    + "ON ju.codi_equi = eq.codi_equi WHERE ju.codi_juga = ?");
            cmd.setInt(1, code);
            ResultSet rs = cmd.executeQuery();
            if (rs.next()) {
                player = new Jugadores(rs.getInt(1),
                        new Equipos(rs.getInt(2), rs.getString(3), rs.getString(4)),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDouble(7),
                        rs.getDouble(8));
            }
        } catch (Exception e) {
            System.err.println("Error al consultar jugador: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return player;
    }

    public boolean modificar(int id, Equipos equipo, String nombre, int edad, double altura, double peso) {
        boolean resp = false;
        try {
            PreparedStatement cmd = conn.prepareStatement("UPDATE jugadores SET codi_equi = ?, "
                    + "nomb_juga = ?, edad_juga = ?, altu_juga = ?, peso_juga = ? WHERE codi_juga = ?");
            cmd.setInt(1, equipo.getId());
            cmd.setString(2, nombre);
            cmd.setInt(3, edad);
            cmd.setDouble(4, altura);
            cmd.setDouble(5, peso);
            cmd.setInt(6, id);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al modificar jugador: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resp;
    }

    public boolean eliminar(int code) {
        boolean resp = false;
        try {
            PreparedStatement cmd = conn.prepareStatement("DELETE FROM jugadores WHERE codi_juga = ?");
            cmd.setInt(1, code);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception e) {
            System.err.println("Error al eliminar jugador: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    if (!conn.isClosed()) {
                        conn.close();
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resp;
    }

}
