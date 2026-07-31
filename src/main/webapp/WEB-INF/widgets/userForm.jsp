<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:choose>
    <c:when test="${empty chatUser}">
        <c:url var="userUrl" value="/user"/>
        <form action="${userUrl}" method="post">
            <label>
                <input name="username" placeholder="생성할 유저 이름">
                <button>생성</button>
            </label>
        </form>
    </c:when>
    <c:otherwise>
        <p>${chatUser}</p>
    </c:otherwise>
</c:choose>