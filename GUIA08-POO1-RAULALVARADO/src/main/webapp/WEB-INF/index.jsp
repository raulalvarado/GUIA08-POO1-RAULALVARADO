<%-- 
    Document   : index
    Created on : 17-mar-2018, 22:05:08
    Author     : Raul
--%>

<%@page import="com.sv.udb.controlador.ControlEquipo"%>
<%@page import="com.sv.udb.modelo.Equipos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Equipos</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            boolean update = Boolean.parseBoolean((String)request.getAttribute("update"));
            String btnName = update ? "Nuevo" : "Guardar"; // Para el texto del botón
            String btnEditClass = update ? "" : "display: none"; //Para ocultar botones
        %>
        <div class="container">
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Usando Bootstrap con la Guía 08.
            </div>
            <div class="row">
                <h1>Equipos</h1>
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <div class="alert alert-success">
                                ${message}
                            </div>
                            <form method="POST" action="TeamServ" name="Demo">
                                <input type="hidden" name="code" id="code" value="${code}"/>
                                <div class="form-group">
                                    <label for="nomb">Nombre:</label>
                                    <input type="text" class="form-control" name="name" id="name" value="${name}"/>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Descripción:</label>
                                    <input type="text" class="form-control" name="desc" id="desc" value="${desc}"/>
                                </div>
                                <input type="submit" class="btn btn-default" name="teamBtn" value="<%=btnName%>"/>
                                <input type="submit" class="btn btn-primary" style="<%=btnEditClass%>" name="teamBtn" value="Modificar"/>
                                <input type="submit" class="btn btn-danger" style="<%=btnEditClass%>" name="teamBtn" value="Eliminar" onclick="return confirm('¿Desea eliminar este registro?')"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="TeamServ" name="Tabl">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Cons</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                    </tr>
                                    <%
                                        for(Equipos temp : new ControlEquipo().getAll())
                                        {
                                    %>
                                        <tr>
                                            <td><input type="radio" name="teamCodeRadio" value="<%= temp.getId() %>"/></td>
                                            <td><%= temp.getNombre() %></td>
                                            <td><%= temp.getDescripcion() %></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                </table>
                                <input type="submit" class="btn btn-success" name="teamBtn" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
                <a href='player.jsp'><h2>Ir a jugadores</h2></a>
            </div>
                                
            
        </div>
    </body>
</html>

