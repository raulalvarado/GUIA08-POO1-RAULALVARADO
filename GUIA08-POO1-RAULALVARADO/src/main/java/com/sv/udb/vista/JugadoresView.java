/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.vista;

import com.sv.udb.controlador.ControlJugador;
import com.sv.udb.controlador.ControlEquipo;
import com.sv.udb.modelo.Jugadores;
import com.sv.udb.modelo.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raul
 */
@WebServlet(name = "JugadoresView", urlPatterns = {"/JugadoresView"})
public class JugadoresView extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try {
            boolean isValid = request.getMethod().equals("POST");
            String message = "";
            if(!isValid) {
                response.sendRedirect(request.getContextPath() + "/jugador.jsp");
            }
            else {
                String CRUD = request.getParameter("playerBtn");
                if(CRUD.equals("Guardar")) {
                    Equipos equipo = new ControlEquipo().consultar(Integer.parseInt(request.getParameter("team")));
                    if (new ControlJugador().insertar(equipo, request.getParameter("name"), Integer.parseInt(request.getParameter("age")), 
                            Double.parseDouble(request.getParameter("height")), Double.parseDouble(request.getParameter("weight"))))
                        message = "Datos guardados";
                    else 
                        message = "Error al guardar";
                }

                else if (CRUD.equals("Consultar")) {
                    int code = Integer.parseInt(request.getParameter("playerCodeRadio") == null ? "-1" : request.getParameter("playerCodeRadio"));
                    Jugadores jugador = new ControlJugador().consultar(code);
                    if (jugador != null) {
                        request.setAttribute("code", jugador.getId());
                        request.setAttribute("name", jugador.getNombre());
                        request.setAttribute("age", jugador.getEdad());
                        request.setAttribute("height", jugador.getAltura());
                        request.setAttribute("weight", jugador.getPeso());
                        request.setAttribute("team", jugador.getEquipo().getId());

                        message = "Información consultada";

                        request.setAttribute("update", "true");
                    }
                    else
                        message = "Error al consultar";
                }

                else if (CRUD.equals("Modificar")) {
                    Equipos equipo = new ControlEquipo().consultar(Integer.parseInt(request.getParameter("team")));
                    if (new ControlJugador().modificar(Integer.parseInt(request.getParameter("code")), equipo, request.getParameter("name"), 
                            Integer.parseInt(request.getParameter("age")), Double.parseDouble(request.getParameter("height")), 
                            Double.parseDouble(request.getParameter("weight"))))
                        message = "Datos modificados";
                    else
                        message = "Error al modificar";
                }

                else if (CRUD.equals("Eliminar")) {
                    if (new ControlJugador().eliminar(Integer.parseInt(request.getParameter("code"))))
                        message = "Datos eliminados";
                    else
                        message = "Error al eliminar";
                }

                request.setAttribute("message", message);
                request.getRequestDispatcher("/jugador.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
