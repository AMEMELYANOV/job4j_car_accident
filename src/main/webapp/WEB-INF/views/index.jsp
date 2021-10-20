<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
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
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h3>Проект Автонарушения</h3>
            </div>
            <div class="col pt-2 mx-2 px-2 my-1 py-1">
                <a class="btn btn-primary" href="<c:url value='/create'/>">Добавить нарушение</a>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Номер</th>
                    <th scope="col">Название</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Адрес</th>
                    <th scope="col">Тип</th>
                    <th scope="col">Статьи</th>
                    <th scope="col">Редактирование</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="accident" items="${accidents}">
                    <tr>
                        <td><c:out value="${accident.id}"/></td>
                        <td>
                            <c:out value="${accident.name}"/>
                        </td>
                        <td><c:out value="${accident.text}"/></td>
                        <td><c:out value="${accident.address}"/></td>
                        <td><c:out value="${accident.type.name}"/></td>
                        <td>
                            <c:forEach var="rule" items="${accident.rules}">
                                <c:out value="${rule.name}"/>
                                <br>
                            </c:forEach>
                        </td>
                        <td><a class="btn btn-primary" href=
                                "<c:url value='/update?id=${accident.id}'/>">Редактирование</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>