<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: ppusd
  Date: 2022-04-03
  Time: 오후 3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
<%--    <li>id=<%=((Member)request.getAttribute("member")).getId();%></li>--%>
<%--    <li>username=<%=((Member) request.getAttribute("member")).getUsername();%></li>--%>
<%--    <li>age=<%=(((Member) request.getAttribute("member")).getAge());%></li>--%>

    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
    <!-- property 접근법 사용 -->
</ul>
<a href="/index.html">메인</a>
</body>
</html>

<!-- View 치중된 모습을 볼 수 있다. -->
