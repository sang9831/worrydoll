<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:choose>
    <c:when test="${not empty chatUser}">
        <c:url var="seachUrl" value="/search" />
        <form action="${seachUrl}" method="post">
            <label>
                <input name="query" placeholder="검색할 내용">
                <button>검색</button>
            </label>
        </form>
        <c:if test="${not empty search}">
            <section>
                <p>${search}</p>
            </section>
        </c:if>
    </c:when>
    <%--    <c:otherwise>--%>
    <%--        <p>로그인이 필요합니다</p>--%>
    <%--    </c:otherwise>--%>
</c:choose>