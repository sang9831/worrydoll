<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:choose>
    <c:when test="${not empty chatUser}">
        <c:url var="chatUrl" value="/chat" />
        <form action="${chatUrl}" method="post">
            <label>
                <input name="content" placeholder="보낼 메시지">
                <button>보내기</button>
            </label>
        </form>
        <section>
            <c:forEach items="${chats}" var="chat">
                <p>${chat}</p>
            </c:forEach>
        </section>
    </c:when>
    <c:otherwise>
        <p>로그인이 필요합니다</p>
    </c:otherwise>
</c:choose>