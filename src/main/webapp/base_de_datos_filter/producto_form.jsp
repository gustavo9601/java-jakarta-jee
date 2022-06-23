<%@ page import="java.util.List" %>
<%@ page import="org.gmarquez.webapp.base_de_datos_filters.models.Categoria" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario de producto</title>
</head>
<body>
<h1>Formulario de productos</h1>

<form action="<%=request.getContextPath()%>/base_de_datos_filter/producto_form" method="post">
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre"/>
    </div>
    <div>
        <label for="precio">Precio:</label>
        <input type="number" name="precio" id="precio"/>
    </div>
    <div>
        <label for="sku">SKU:</label>
        <input type="text" name="sku" id="sku"/>
    </div>
    <div>
        <label for="categoria_id">Categoria:</label>
        <select name="categoria_id" id="categoria_id">
            <option value="">-- Seleccionar --</option>
            <% List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias"); %>
            <% for (Categoria categoria : categorias) {
                out.println("<option value='" + categoria.getId() + "'>" + categoria.getNombre() + "</option>");
            }%>
        </select>
    </div>
    <div>
        <label for="fecha_registro">Fecha de registro:</label>
        <input type="date" name="fecha_registro" id="fecha_registro"/>
    </div>
    <div>
        <input type="submit" value="Guardar">
    </div>
</form>
</body>
</html>
