<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 01.07.2015
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <form action="/hello" method="post">
      <input type="text" name="color">
      <button type="submit">submit me</button>
    </form>


    <form action="/db" method="post">
      <p>query</p>
      <input type="text" name="query">
      <button type="submit">submit me</button>
    </form>
  </body>
</html>
