<%-- 
    Document   : jugador
    Created on : 17-mar-2018, 23:16:22
    Author     : Raul
--%>

<%@page import="com.sv.udb.controlador.ControlEquipo"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page import="com.sv.udb.controlador.ControlJugador"%>
<%@page import="com.sv.udb.modelo.Jugadores"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Jugadores</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            boolean update = Boolean.parseBoolean((String)request.getAttribute("update"));
            String btnName = update ? "Nuevo" : "Guardar"; // Para el texto del botón
            String btnEditClass = update ? "" : "display: none"; //Para ocultar botones
            
            int teamCode;
            try {
                teamCode = (Integer)request.getAttribute("team"); 
            } catch (Exception e) {
                teamCode = 0;
            }
            
        %>
        <div class="container">
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Usando Bootstrap con la Guía 08.
            </div>
            <div class="row">
                <h1>Jugadores</h1>
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <div class="alert alert-success">
                                ${message}
                            </div>
                            <form method="POST" action="PlayerServ" name="Demo">
                                <input type="hidden" name="code" id="code" value="${code}"/>
                                <div class="form-group">
                                    <label for="nomb">Nombre:</label>
                                    <input type="text" class="form-control" name="name" id="name" value="${name}"/>
                                </div>
                                <div class="form-group">
                                    <label for="age">Edad:</label>
                                    <input type="text" class="form-control" name="age" id="age" value="${age}"/>
                                </div>
                                <div class="form-group">
                                    <label for="height">Altura:</label>
                                    <input type="text" class="form-control" name="height" id="height" value="${height}"/>
                                </div>
                                <div class="form-group">
                                    <label for="weight">Peso:</label>
                                    <input type="text" class="form-control" name="weight" id="weight" value="${weight}"/>
                                </div>
                                <div class="form-group">
                                    <label for="team">Equipo:</label>
                                    <select class="form-control" name="team" id="team">
                                    <% 
                                        for(Equipos temp : new ControlEquipo().getAll())
                                        {
                                    %>
                                        <option value="<%= temp.getId() %>"
                                                <% 
                                                    if (temp.getId() == teamCode) {%>
                                                    selected
                                                    <% } %>
                                                ><%= temp %></option>
                                    <%
                                        }
                                    %>
                                    </select>
                                </div>
                                <input type="submit" class="btn btn-default" name="playerBtn" value="<%=btnName%>"/>
                                <input type="submit" class="btn btn-primary" style="<%=btnEditClass%>" name="playerBtn" value="Modificar"/>
                                <input type="submit" class="btn btn-danger" style="<%=btnEditClass%>" name="playerBtn" value="Eliminar" onclick="return confirm('¿Desea eliminar este registro?')"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="PlayerServ" name="Tabl">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Cons</th>
                                        <th>Nombre</th>
                                        <th>Edad</th>
                                        <th>Altura</th>
                                        <th>Peso</th>
                                        <th>Equipo</th>
                                    </tr>
                                    <%
                                        for(Jugadores temp : new ControlJugador().getAll())
                                        {
                                    %>
                                        <tr>
                                            <td><input type="radio" name="playerCodeRadio" value="<%= temp.getId() %>"/></td>
                                            <td><%= temp.getNombre() %></td>
                                            <td><%= temp.getEdad() %></td>
                                            <td><%= temp.getAltura() %></td>
                                            <td><%= temp.getPeso() %></td>
                                            <td><%= temp.getEquipo() %></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                </table>
                                <input type="submit" class="btn btn-success" name="playerBtn" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
                <a href='index.jsp'><h2>Ir a equipos</h2></a>
            </div>
                                
            
        </div>
    </body>
</html>
