<%@ page import="java.util.List" %>
<%@ page import="org.gmarquez.webapp.listeners.models.Carro" %>
<%@ page import="org.gmarquez.webapp.listeners.models.ItemCarro" %><%--
  Created by IntelliJ IDEA.
  User: ingegus
  Date: 18/06/2022
  Time: 9:39 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carro de compras</title>
</head>
<body>

<% Carro carro = (Carro) session.getAttribute("carro"); %>

<h1>Carro de compras</h1>

<% if (carro.estaVacio()) { %>
<p>No hay productos en el carro</p>
<% } else { %>


<form action="<%=request.getContextPath()%>/base_de_datos_filter/actualizar-item-carro-servlet" method="post">
    <table>
        <thead>
        <tr>
        <tr>id</tr>
        <tr>nombre</tr>
        <tr>precio</tr>
        <tr>cantidad</tr>
        <tr>total</tr>
        <tr>eliminar</tr>
        </tr>
        </thead>
        <tbody>
        <% for (ItemCarro itemCarro : carro.getItemCarros()) { %>
        <tr>
            <td><%=itemCarro.getProducto().getId()%></td>
            <td><%=itemCarro.getProducto().getNombre()%></td>
            <td><%=itemCarro.getProducto().getPrecio()%></td>
            <td>
                <input type="number" name="cantidad-<%=itemCarro.getProducto().getId()%>" value="<%=itemCarro.getCantidad()%>">
            </td>
            <td><%=itemCarro.getImporte()%></td>
            <td>
                <input type="checkbox" name="eliminar" value="<%=itemCarro.getProducto().getId()%>"/>
            </td>
        </tr>
        <%}%>
        <tr>
            <td colspan="4" style="text-align: right">Total</td>
            <td><%=carro.getTotal()%></td>
        </tr>
        </tbody>
    </table>
    <button type="submit">Actualizar</button>
</form>

<%}%>
<hr>
<p>
    <a href="<%=request.getContextPath()%>/base_de_datos_filter/tabla_productos">Tabla productos</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/base_de_datos_filter/inicio">Inicio</a>
</p>


</body>
</html>
