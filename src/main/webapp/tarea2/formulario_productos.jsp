<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ingegus
  Date: 15/06/2022
  Time: 12:09 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario 2</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Formulario 2</h1>
            <form action="/java-jakarta-jee/crear_producto" method="post" class="form">
                <%
                    // Capturando el valor enviado desde el controlador
                    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
                %>

                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" value="${param.nombre}">
                    <%
                        if (errores != null && errores.containsKey("nombre")) {
                            out.println("<p class='alert alert-danger'>" + errores.get("nombre") + "</p>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <label for="precio">Precio</label>
                    <input type="number" class="form-control" id="precio" name="precio" placeholder="precio" value="${param.precio}">
                    <%
                        if (errores != null && errores.containsKey("precio")) {
                            out.println("<p class='alert alert-danger'>" + errores.get("precio") + "</p>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <label for="fabricante">Fabricante</label>
                    <input type="text" class="form-control" id="fabricante" name="fabricante" placeholder="fabricante" value="${param.fabricante}">
                    <%
                        if (errores != null && errores.containsKey("fabricante")) {
                            out.println("<p class='alert alert-danger'>" + errores.get("fabricante") + "</p>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <label for="categorias">Categorias</label>
                    <select name="categorias" id="categorias" class="form-control" multiple>
                        <option value="hogar" ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("hogar") ).get() ? "selected" : ""} >hogar</option>
                        <option value="tecnologia" ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("tecnologia") ).get() ? "selected" : ""} >tecnologia</option>
                        <option value="oficina" ${paramValues.lenguajes.stream().anyMatch( valor -> valor.equals("oficina") ).get() ? "selected" : ""} >oficina</option>
                    </select>
                    <%
                        if (errores != null && errores.containsKey("categorias")) {
                            out.println("<p class='alert alert-danger'>" + errores.get("categorias") + "</p>");
                        }
                    %>
                </div>

                <div class="form-group">
                    <input type="submit" value="Guardar" class="btn btn-outline-dark">
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
