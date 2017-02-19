<html>
<%@ include file="header.jsp" %>
<%@ page import="com.springapp.mvc.controller.CustomerController" %>
<script src="<c:url value="/resources/js/customer.js"/>"></script>
<head>
    <title>Customer Details</title>
</head>
<body>
<div>
    <div class="container">
        <div class="row">
            <div class="span12">
                <table id="customers" class="col-sm-4 well-sm">
                    here goes the list
                    <c:forEach items="${list}" var="cust">
                        <tr>
                            <td><c:out value="${cust}" /><button type="button">Edit</button><button type="button" class="close"></button></td>
                        </tr>
                    </c:forEach>
                </table>
                <%--<script type="text/javascript">
                    var data = [{customerAddress:"Deshani, Kirinda",customerName:"Heshani",customerPhone:712189826,customerEmail:"heshanisamarasekara@gmail.com",customerid:1},{customerAddress:"asdsasd",customerName:"asdsasd",customerPhone:15017625,customerEmail:"asdsasd@asd.com",customerid:2},{customerAddress:"asdsasd",customerName:"asdsasd",customerPhone:15017625,customerEmail:"asdsasd@asd.com",customerid:3}]
                </script>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
