<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%
    String redirectURL = request.getContextPath() + "/Home";
    response.sendRedirect(redirectURL);
%>
