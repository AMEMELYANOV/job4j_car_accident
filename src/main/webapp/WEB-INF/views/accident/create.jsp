<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
            integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container pt-3 pt-3 py-3 mx-auto">
    <div class="row justify-content-end">
        <div class="col-2 align-self-center">
            Вы вошли как : ${user.username}
        </div>
        <div class="col-2 align-self-center">
            <a class="btn btn-link" href="<c:url value='/logout'/>">Выйти</a>
        </div>
    </div>
</div>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Проект Автонарушения. Создание нарушения.</h3>
            </div>
            <form action="<c:url value='/save'/>" method='POST'>
                <table>
                    <tr>
                        <td>Название:</td>
                        <td><input type='text' name='name' required></td>
                    </tr>
                    <tr>
                        <td>Описание:</td>
                        <td><input type='text' name='text' required></td>
                    </tr>
                    <tr>
                        <td>Адрес:</td>
                        <td><input type='text' name='address' required></td>
                    </tr>
                    <tr>
                        <td>Тип:</td>
                        <td>
                            <select name="type.id">
                                <c:forEach var="type" items="${types}" >
                                    <option value="${type.id}">${type.name}</option>
                                </c:forEach>
                            </select>
                    </tr>
                    <tr>
                        <td>Статьи:</td>
                        <td>
                            <select name="rIds" multiple required>
                                <c:forEach var="rule" items="${rules}" >
                                    <option value="${rule.id}">${rule.name}</option>
                                </c:forEach>
                            </select>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>