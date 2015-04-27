<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    <h1>Choose your way, young padawan...</h1>

    <form action="/switch" method="get">
        <input id="x" type="radio" name="testerType" value="HQL" checked>
        <label for="x">HQL</label>
        <br>
        <input id="y" type="radio" name="testerType" value="Criteria">
        <label for="y">Criteria</label>

        <br>
        <hr>

        <input id="a" type="radio" name="method" value="listOwnershipCandidates" checked>
        <label for="a">listOwnershipCandidates</label>
        <br>
        <input id="b" type="radio" name="method" value="listItemsBigGroups">
        <label for="b">listItemsBigGroups</label>
        <br>
        <input id="c" type="radio" name="method" value="isConnectedToBigGroup">
        <label for="c">isConnectedToBigGroup</label>
        <br>
        <input id="d" type="radio" name="method" value="listBigGroupsItems">
        <label for="d">listBigGroupsItems</label>

        <br>
        <hr>

        <label for="itemId">itemId:</label>
        <input type="number" min="1" value="1" name="itemId" id="itemId">

        <label for="bigGroupId">bigGroupId:</label>
        <input type="number" min="1" value="1" name="bigGroupId" id="bigGroupId">

        <br><br>
        <button type="submit">Send</button>
    </form>


</body>
</html>
