<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario Login</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/listeners/login" method="post">
    <label for="usuario">Usuario:</label>
    <input type="text" name="usuario" id="usuario"/>
    <br/>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password"/>
    <br/>
    <input type="submit" value="Enviar"/>
</form>
</body>
</html>
