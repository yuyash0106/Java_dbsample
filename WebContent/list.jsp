<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>

    <%
    ArrayList<ArrayList<String>> table = (ArrayList<ArrayList<String>>)request.getAttribute("TABLE");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<% for(ArrayList<String> rec : table){ %>
<tr>
<% for(String data :rec){ %>
<td><%=data %></td>

<% } %>
</tr>
<%} %>
</table>
</body>
</html>