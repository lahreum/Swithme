<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Token</title>
<script>
const storage = window.sessionStorage;
const token = "${token}";
storage.setItem('jwt-auth-token', token);
//location.href = "http://localhost:8080";
</script>
</head>
<body>
</body>
</html>