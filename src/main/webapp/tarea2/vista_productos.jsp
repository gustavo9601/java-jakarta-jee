<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vista productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Producto creado</h1>
            <%
                if (request.getAttribute("nombre") != null) {
            %>
            <p>Nombre: <%= request.getAttribute("nombre") %>
            </p>
            <%
                }
                if (request.getAttribute("precio") != null) {
            %>
            <p>Precio: <%= request.getAttribute("precio") %>
            </p>
            <%
                }%>

            <%
                if (request.getAttribute("fabicante") != null) {
            %>
            <p>Precio: <%= request.getAttribute("fabicante") %>
            </p>
            <%
                }%>

            <%
                if (request.getAttribute("categorias") != null) {
            %>
            <h4>Categorias:</h4>
            <hr>

            <%
                    List<String> categorias = (List<String>) request.getAttribute("categorias");

                    for (String categoria : categorias) {
                        out.println("<p>" + categoria + "</p>");
                    }
                }

            %>

        </div>
    </div>
    <div>
        <a href="/java-jakarta-jee/tarea2/formulario_productos.jsp" class="btn btn-warning btn-block">Crear nuevo</a>
    </div>
</div>
</body>
</html>
