<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio listeners</title>
</head>
<body>
<h2>Mensajes</h2>
<p>mensajeGlobal: <%=request.getServletContext().getAttribute("mensajeGlobal")%></p>
<p>mensajeRequest: <%=request.getAttribute("mensajeRequest")%></p>


<h1>Opciones</h1>

<p>
    <a href="<%=request.getContextPath()%>/base_de_datos_filter/inicio">Inicio</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/base_de_datos_filter/login">Login</a>
</p>
<p>
    <a href='<%=request.getContextPath()%>/base_de_datos_filter/ver-carro'>Ver el carro</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/base_de_datos_filter/tabla_productos">Tabla productos</a>
</p>
</body>
</html>
