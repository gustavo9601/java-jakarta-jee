<%@ page import="java.util.List" %>
<%@ page import="org.gmarquez.webapp.listeners.models.Producto" %><%--
  Created by IntelliJ IDEA.
  User: ingegus
  Date: 15/06/2022
  Time: 11:18 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exportando productos</title>
</head>
<body>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>

<h1>Bienvenido <% request.getAttribute("nombreUsuario"); %></h1>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Descripcion</th>
        <th>Precio</th>
    </thead>
    <tbody>
        <%
        for (Producto producto : productos) {
            out.println("<tr>");
            out.println("<td>" + producto.getId() + "</td>");
            out.println("<td>" + producto.getNombre() + "</td>");
            out.println("<td>" + producto.getDescripcion() + "</td>");
            out.println("<td>" + producto.getPrecio() + "</td>");
            out.println("<td><a href='"+request.getContextPath() + "/listeners/agregar-item-carro-servlet?id="+ producto.getId()+ "'>Agregar al carrito</td>");
            out.println("</tr>");

        }
    %>
    <tbody/>
    <table/>

</table>
<a href='<%=request.getContextPath()%>/listeners/inicio'>Inicio</a>
<br>
<a href='<%=request.getContextPath()%>/listeners/ver-carro'>Ver el carro</a>
<br>
<a href='<%=request.getContextPath()%>/listeners/cerrar_session'>Cerrar sesion</a>

</body>
</html>
