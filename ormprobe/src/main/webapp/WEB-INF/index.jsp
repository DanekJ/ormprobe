<%--
  Created by IntelliJ IDEA.
  User: witz
  Date: 30.3.15
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Mybatis</title>
</head>
<body>
    <h1>Groups</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/g/a.do">All</a></li>
        <li><a href="${pageContext.request.contextPath}/g/ibg.do?id=1">Item - BigGroups</a></li>
    </ul>
    <h1>TestDao</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/i/loc.do?id=1">ListOwnerShipCandidates</a></li>
        <li><a href="${pageContext.request.contextPath}/i/ictob.do?gid=1&iid=1">IsConnectedToBigGroup</a></li>
        <li><a href="${pageContext.request.contextPath}/i/lbgi.do?id=1">ListBigGroupItems</a></li>
        <li><a href="${pageContext.request.contextPath}/i/libg.do?id=1">ListItemBigGroups</a></li>
    </ul>
</body>
</html>
