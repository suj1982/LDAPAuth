<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>

    <h2>User Details</h2>

    <% 
        HttpSession session = request.getSession();
        Map<String, String> userAttributes = (Map<String, String>) session.getAttribute("userAttributes");
        
        if (userAttributes != null && !userAttributes.isEmpty()) {
            Set<Entry<String, String>> attributeSet = userAttributes.entrySet();
            Iterator<Entry<String, String>> iterator = attributeSet.iterator();
            
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
    %>
                <p><strong><%= entry.getKey() %>:</strong> <%= entry.getValue() %></p>
    <%
            }
        } else {
    %>
            <p>No user attributes found.</p>
    <%
        }
    %>

</body>
</html>
