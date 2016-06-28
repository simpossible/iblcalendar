<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <meta charset="UTF-8">
    <title>11</title>
</head>
<form action="http://localhost:8080/calendar/domain/createdomain" method="POST">
    <input type="text" width="300px" height="100px" value="邮箱" name="domain_name">
    <br>
    <input type="text" width="300px" height="100px" value="密码" name="passwd">

    <input type="submit">
</form>

</body>
</html>
