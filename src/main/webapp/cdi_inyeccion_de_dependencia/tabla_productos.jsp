<%@ page import="java.util.Optional" %>
<%@ page import="org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.Producto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Importa la libreria core y con su prefijo, para poder ser llamado como etiqueta--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Exportando productos</title>
</head>
<body>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>


<c:if test="${username.isPresent()}">
    <h1>Bienvenido ${username.get()}</h1>
</c:if>

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

        <%--<%
        for (Producto producto : productos) {
            out.println("<tr>");
            out.println("<td>" + producto.getId() + "</td>");
            out.println("<td>" + producto.getSku() + "</td>");
            out.println("<td>" + producto.getNombre() + "</td>");
            out.println("<td>" + producto.getDescripcion() + "</td>");
            out.println("<td>" + producto.getPrecio() + "</td>");
            out.println("<td>" + producto.getCategoria().getNombre() + "</td>");
            out.println("<td>" + producto.getFechaRegistro()+ "</td>");
            out.println("<td><a href='"+request.getContextPath() + "/cdi_inyeccion_de_dependencia/agregar-item-carro-servlet?id="+ producto.getId()+ "'>Agregar al carrito</td>");
            out.println("<td><a href='"+request.getContextPath() + "/cdi_inyeccion_de_dependencia/producto_form?id="+ producto.getId()+ "'>Editar</td>");
            out.println("<td><a href='"+request.getContextPath() + "/cdi_inyeccion_de_dependencia/producto_eliminar?id="+ producto.getId()+ "'>Eliminar</td>");
            out.println("</tr>");

        }
    %>--%>


    <c:forEach var="producto" items="${productos}">
    <tr>
        <td><c:out value="${producto.getId()}"></c:out></td>
        <td><c:out value="${producto.getSku()}"></c:out></td>
        <td><c:out value="${producto.getNombre()}"></c:out></td>
        <td><c:out value="${producto.getDescripcion()}"></c:out></td>
        <td><c:out value="${producto.getCategoria().getNombre()}"></c:out></td>
        <td><c:out value="${producto.getPrecio()}"></c:out></td>
        <td><c:out value="${producto.getFechaRegistro()}"></c:out></td>

        <td><a href="${pageContext.request.contextPath}/cdi_inyeccion_de_dependencia/agregar-item-carro-servlet?id=${producto.getId()}">Agregar al carrito</a></td>
        <td><a href="${pageContext.request.contextPath}/cdi_inyeccion_de_dependencia/producto_form?id=${producto.getId()}">Editar</a></td>
        <td><a href="${pageContext.request.contextPath}/cdi_inyeccion_de_dependencia/producto_eliminar?id=${producto.getId()}">Eliminar</a></td>



    </tr>
    </c:forEach>


    <tbody/>
    <table/>

</table>
<a href='<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/inicio'>Inicio</a>
<br>
<a href='<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/ver-carro'>Ver el carro</a>
<br>
<a href='<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/cerrar_session'>Cerrar sesion</a>

</body>
</html>
