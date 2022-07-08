<%@ page import="java.util.List" %>
<%@ page import="org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Categoria" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.gmarquez.webapp.cdi_inyeccion_de_dependencia.models.entities.Producto" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario de producto</title>
</head>
<body>
<h1>Formulario de productos</h1>

<form action="<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/producto_form" method="post">

    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Producto producto = (Producto) request.getAttribute("producto"); %>

    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" value="<%=producto != null ? producto.getNombre() : ""%>"/>
        <% if (errores != null && errores.containsKey("nombre")) { %>
        <span class="error"><%= errores.get("nombre") %></span>
        <% } %>
    </div>
    <div>
        <label for="precio">Precio:</label>
        <input type="number" name="precio" id="precio" value="<%=producto != null ? producto.getPrecio() : ""%>"/>
        <% if (errores != null && errores.containsKey("precio")) { %>
        <span class="error"><%= errores.get("precio") %></span>
        <% } %>
    </div>
    <div>
        <label for="sku">SKU:</label>
        <input type="text" name="sku" id="sku" value="<%=producto != null ? producto.getSku() : ""%>"/>
        <% if (errores != null && errores.containsKey("sku")) { %>
        <span class="error"><%= errores.get("sku") %></span>
        <% } %>
    </div>
    <div>
        <label for="categoria_id">Categoria:</label>
        <select name="categoria_id" id="categoria_id">
            <option value="">-- Seleccionar --</option>
            <% List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias"); %>
            <% for (Categoria categoria : categorias) {
                String optionStr = "<option value='" + categoria.getId() + "'";

                if (producto != null && producto.getCategoria() != null && producto.getCategoria().getId() == categoria.getId()) {
                    optionStr += " selected";
                }

                optionStr += ">" + categoria.getNombre() + "</option>";

                out.println(optionStr);
            }%>
        </select>
        <% if (errores != null && errores.containsKey("categoria_id")) { %>
        <span class="error"><%= errores.get("categoria_id") %></span>
        <% } %>
    </div>
    <div>
        <label for="fecha_registro">Fecha de registro:</label>
        <input type="date" name="fecha_registro" id="fecha_registro" value="<%=producto != null ? producto.getFechaRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""%>"/>
        <% if (errores != null && errores.containsKey("fecha_registro")) { %>
        <span class="error"><%= errores.get("fecha_registro") %></span>
        <% } %>
    </div>
    <div>
        <input type="submit" value="Guardar">
    </div>
</form>
</body>
</html>
