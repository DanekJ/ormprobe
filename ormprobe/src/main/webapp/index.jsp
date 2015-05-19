<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Generátor</h1>
        <form action="Generate" method="get">
            <button type="submit" name="generate" value="Generuj">Generuj</button>
        </form>
        <h1>Metody</h1>
        <form action="Servlet" method="get">
            <table>
            <tr>    
                <td><label for="bg">Big group id</label></td>
                <td><input type="text" name="bg" id="bg"/></td>
            </tr>
            <tr>
                <td><label for="item">Item id</label></td>
                <td><input type="text" name="item" id="item"/></td>
            </tr>
            <tr>
                <td><button type="submit" name="method" value="1">listOwnershipCandidates</button></td>
                <td><button type="submit" name="method" value="2">listItemsBigGroups</button></td>
            </tr>
            <tr>
                <td><button type="submit" name="method" value="3">isConnectedToBigGroup</button></td>
                <td><button type="submit" name="method" value="4">listBigGroupsItems</button></td>
            </tr>
            <tr>
                <td><input type="radio" name="type" value="1" checked>Query</td>
                <td><input type="radio" name="type" value="2">Criteria</td>
            </tr>
            </table>
        </form>
        <h1>Výsledky</h1>                            
            <c:if test="${not empty result}">
                <c:forEach items="${requestScope.result}" var="result">
                    Jméno: <c:out value="${result.name}"/><br>
                    Id: <c:out value="${result.id}"/><br>
                </c:forEach>
            </c:if>
            <c:if test="${not empty connect}">
                <c:out value="${connect}" />
            </c:if>    
        </script>
    </body>
</html>
