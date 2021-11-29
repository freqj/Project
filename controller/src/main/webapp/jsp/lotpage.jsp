<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    
    <%@ include file="header.jsp" %>
    <h1>${requestScope.Lot.title}</h1>
    <c:if test="${sessionScope.user.id == requestScope.Lot.userOwnerID}">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="change_lot_title"/>
            <input type="hidden" name="lot_id" value="${requestScope.Lot.id}"/>
            <input type="text" name="title" value="${requestScope.Lot.title}"/>
            <button type="submit"> Change</button>
        </form>
    </c:if>
    <h2>${requestScope.Lot.price}</h2>
    <c:if test="${sessionScope.user.id == requestScope.Lot.userOwnerID}">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="change_lot_price"/>
            <input type="hidden" name="lot_id" value="${requestScope.Lot.id}"/>
            <input type="number" name="price" value="${requestScope.Lot.price}"/>
            <button type="submit"> Change</button>
        </form>
    </c:if>
    <div class="product_image">
        <img class="product_image" src="data:image/jpg;base64,${requestScope.Lot.image}">
        
    </div>
    <c:choose>
        <c:when test="${sessionScope.user.role == 'guest'}">
        </c:when>
        <c:otherwise>
            <form method="get" action="Controller">
                <input type="hidden" name="command" value="add_to_bookmark" >
                <input type="hidden" name="lot_id" value="${requestScope.Lot.id}" >
                <button type="submit"> add to bookmarks </button>
            </form>
       </c:otherwise>
    </c:choose>
    <c:choose>
       <c:when test="${sessionScope.user.role == 'guest' or sessionScope.user.id == requestScope.Lot.userOwnerID}">
        </c:when>
        <c:otherwise>
            <form method="post" action="Controller">
                <input type="hidden" name="command" value="send_message_to_owner" />
                <input type="hidden" name="lot_id" value="${requestScope.Lot.id}" />
                <input type="hidden" name="user_owner_id" value="${requestScope.Lot.userOwnerID}"/>
                <button type="submit"> Buy </button>
            </form>
       </c:otherwise>
    </c:choose>
    <c:if test="${sessionScope.user.id == requestScope.Lot.userOwnerID or sessionScope.user.role == 'admin'}">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="delete_lot"/>
            <input type="hidden" name="lot_id" value="${requestScope.Lot.id}"/>
            <button type="submit"> Delete lot</button>
        </form>
    </c:if>
    <p>${requestScope.Lot.description}</p>
    <c:if test="${sessionScope.user.id == requestScope.Lot.userOwnerID}">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="CHANGE_LOT_DESCRIPTION"/>
            <input type="hidden" name="lot_id" value="${requestScope.Lot.id}"/>
            <input type="text" name="description" value="${requestScope.Lot.description}"/>
            <button type="submit"> Change</button>
        </form>
    </c:if>

    <c:if test="${sessionScope.user.id == requestScope.Lot.userOwnerID}">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="change_lot_status"/>
            <input type="hidden" name="lot_id" value="${requestScope.Lot.id}"/>
            <select name="is_active_status" id="is_active_status">
                <option value="active">Active</option>
                <option value="disabled">Disabled</option>
            </select>
            <button type="submit"> Change</button>
        </form>
    </c:if>
    <%@ include file="footer.jsp" %>
</body>
</html>