<%@ page import="java.util.Hashtable" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.NamingEnumeration" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.naming.directory.DirContext" %>
<%@ page import="javax.naming.directory.SearchControls" %>
<%@ page import="javax.naming.directory.SearchResult" %>
<%@ page import="com.unboundid.ldap.sdk.*" %>

<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="LoginController" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>