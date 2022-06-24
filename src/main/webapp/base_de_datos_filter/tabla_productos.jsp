<%@ page import="java.util.List" %>
<%@ page import="org.gmarquez.webapp.base_de_datos_filters.models.Producto" %>

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
        <th>SKU</th>
        <th>Nombre</th>
        <th>Descripcion</th>
        <th>Categoria</th>
        <th>Precio</th>
        <th>Fecha creacion</th>
    </thead>
    <tbody>
        <%
        for (Producto producto : productos) {
            out.println("<tr>");
            out.println("<td>" + producto.getId() + "</td>");
            out.println("<td>" + producto.getSku() + "</td>");
            out.println("<td>" + producto.getNombre() + "</td>");
            out.println("<td>" + producto.getDescripcion() + "</td>");
            out.println("<td>" + producto.getPrecio() + "</td>");
            out.println("<td>" + producto.getCategoria().getNombre() + "</td>");
            out.println("<td>" + producto.getFechaRegistro()+ "</td>");
            out.println("<td><a href='"+request.getContextPath() + "/base_de_datos_filter/agregar-item-carro-servlet?id="+ producto.getId()+ "'>Agregar al carrito</td>");
            out.println("<td><a href='"+request.getContextPath() + "/base_de_datos_filter/producto_form?id="+ producto.getId()+ "'>Editar</td>");
            out.println("<td><a href='"+request.getContextPath() + "/base_de_datos_filter/producto_eliminar?id="+ producto.getId()+ "'>Eliminar</td>");
            out.println("</tr>");

        }
    %>
    <tbody/>
    <table/>

</table>
<a href='<%=request.getContextPath()%>/base_de_datos_filter/inicio'>Inicio</a>
<br>
<a href='<%=request.getContextPath()%>/base_de_datos_filter/ver-carro'>Ver el carro</a>
<br>
<a href='<%=request.getContextPath()%>/base_de_datos_filter/cerrar_session'>Cerrar sesion</a>

</body>
</html>
