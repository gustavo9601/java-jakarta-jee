<%--Especifica el tipo de documento y el encoding--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.Map" %>
<%--
Se pone el codigo java
<% %>

--%>
<%
    // Capturando el valor enviado desde el controlador
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">


</head>
<body>

<h3>Formulario</h3>
<%
    if (errores != null && errores.size() > 0) {
%><h2>Lista de errores</h2><%
    for (String error : errores.values()) {
%>
<%-- <%=  %> // Permite imprimir la expresion  --%>
<p class='alert alert-danger'><%= error %>
</p>
<%
        }
    }
%>

<form action="/java-jakarta-jee/formulario" method="post" class="container form">
    <div>
        <label for="nombre">Nombre</label>
        <%--
        ${para.llave} // Permite imprimir la llave de recibirse en el request ( request.getParameter("llave") )
        --%>
        <input class="form-control" type="text" name="nombre" id="nombre" value="${param.nombre}">
        <%
            if (errores != null && errores.containsKey("nombre")) {
                out.println("<p class='alert alert-danger'>" + errores.get("nombre") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="apellido">Apellido</label>
        <input class="form-control" type="text" name="apellido" id="apellido" value="${param.apellido}">
        <%
            if (errores != null && errores.containsKey("apellido")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("apellido") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="correo">Correo</label>
        <input class="form-control" type="email" name="correo" id="correo" value="${param.correo}">
        <%
            if (errores != null && errores.containsKey("email")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("email") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="contrasena">Contraseña</label>
        <input class="form-control" type="password" name="contrasena" id="contrasena">
        <%
            if (errores != null && errores.containsKey("contrasena")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("contrasena") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="edad">Edad</label>
        <input class="form-control" type="number" name="edad" id="edad" value="${param.edad}">
        <%
            if (errores != null && errores.containsKey("edad")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("edad") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="pais">Pais</label>
        <select name="pais" class="form-control" id="pais">
            <option value="CO" ${param.pais.equals("CO") ? "selected" : ""}>Colombia</option>
            <option value="USA" ${param.pais.equals("USA") ? "selected" : ""}>EE UU</option>
            <option value="ES" ${param.pais.equals("ES") ? "selected" : ""}>España</option>
        </select>
        <%
            if (errores != null && errores.containsKey("pais")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("pais") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="lenguajes">Lenguajes programacion</label>
        <select class="form-control" name="lenguajes" id="lenguajes" multiple>
            <%--
            paramValues // cuando es una lista
            --%>
            <option value="java" ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("java") ).get() ? "selected" : ""}>
                Java
            </option>
            <option value="php"  ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("php") ).get() ? "selected" : ""}>
                PHP
            </option>
            <option value="python"  ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("python") ).get() ? "selected" : ""}>
                Python
            </option>
            <option value="js"  ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("js") ).get() ? "selected" : ""}>
                Javascript
            </option>
        </select>
        <%
            if (errores != null && errores.containsKey("lenguajes")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("lenguajes") + "</p>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label>Roles</label>
        <div>
            <input type="checkbox" name="roles"
                   value="ADMIN" ${paramValues.roles.stream().anyMatch( valor -> valor.equals("ADMIN") ).get() ? "checked" : ""}>
            <label>Administrador</label>
        </div>
        <div>
            <input type="checkbox" name="roles"
                   value="MODERADOR" ${paramValues.roles.stream().anyMatch( valor -> valor.equals("MODERADOR") ).get() ? "checked" : ""}>
            <label>Moderador</label>
        </div>
        <div>
            <input type="checkbox" name="roles"
                   value="USUARIO" ${paramValues.roles.stream().anyMatch( valor -> valor.equals("USUARIO") ).get() ? "checked" : ""}>
            <label>Usuario</label>
        </div>
        <%
            if (errores != null && errores.containsKey("roles")) {
                out.println("<p class='alert alert-danger' style='color:red'>" + errores.get("roles") + "</p>");
            }
        %>
    </div>
    <hr>
    <input class="btn btn-primary" type="submit" value="Enviar">
</form>

</body>
</html>
