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
    <a href="<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/inicio">Inicio</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/login">Login</a>
</p>
<p>
    <a href='<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/ver-carro'>Ver el carro</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/tabla_productos">Tabla productos</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/cdi_inyeccion_de_dependencia/producto_form">Crear productos</a>
</p>
</body>
</html>
