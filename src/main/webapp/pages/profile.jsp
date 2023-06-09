<%--
  Created by IntelliJ IDEA.
  User: Siarhei
  Date: 12.06.2023
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>User</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--%>
  <%--    <link rel="stylesheet" href="valid.css">--%>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid bg-3 text-center border-bottom">
  <br>
  <div class="row">
    <div class="col-md d-flex align-items-center justify-content-center">
      <c:choose>
        <c:when test="${requestScope.account.avatar != null}">
          <img src="${requestScope.account.avatar}"
               class="rounded-circle border border-dark" width="150" height="150">
        </c:when>
        <c:otherwise>
          <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png"
               class="rounded-circle border border-dark" width="150" height="150">
        </c:otherwise>
      </c:choose>
    </div>
    <div class="col-md text-md-start">
      <h3>${requestScope.account.name}</h3>
      <h4>@${requestScope.account.username}</h4>

      <c:if test="${sessionScope.user.username == requestScope.account.username}">
        <a href="/user/account/edit" class="btn btn-sm btn-danger" role="button" aria-pressed="true">Edit</a>
      </c:if>
    </div>
    <div class="col-md">
      <div class="container">
        <div class="row">
          <a class="col" href="#">${requestScope.followersCnt} Followers</a>
          <a class="col" href="#">${requestScope.followingCnt} Following</a>
        </div>
        <c:if test="${sessionScope.user.username != requestScope.account.username}">
          <br>
          <div class="row">
            <form action="/#" method="post">
              <c:choose>
                <c:when test="${!requestScope.isAlreadyFollowed}">
                  <button type="button" class="btn btn-sm btn-success">Follow</button>
                </c:when>
                <c:otherwise>
                  <button type="button" class="btn btn-sm btn-danger">Unfollow</button>
                </c:otherwise>
              </c:choose>
            </form>
          </div>
        </c:if>
      </div>

    </div>
  </div>
  <br>
</div>
<br>


<div class="border-bottom">
  <div class="container-fluid text-center">
    <div class="row">
      <c:if test="${requestScope.posts[0].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[0].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
      <c:if test="${requestScope.posts[3].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[3].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
      <c:if test="${requestScope.posts[6].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[6].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
    </div>
    <br>
    <div class="row">
      <c:if test="${requestScope.posts[1].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[1].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
      <c:if test="${requestScope.posts[4].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[4].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
      <c:if test="${requestScope.posts[7].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[7].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
    </div>
    <br>
    <div class="row">
      <c:if test="${requestScope.posts[2].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[2].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
      <c:if test="${requestScope.posts[5].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[5].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
      <c:if test="${requestScope.posts[8].image != null}">
        <div class="col-sm-4">
          <div class="card" style="width: 100%">
            <a href="#"><img class="card-img-top" src="${requestScope.posts[8].image}"
                             style="width:100%; height: auto;  aspect-ratio: 16/9"
                             alt="Image"></a><br>
          </div>
        </div>
      </c:if>
    </div>
  </div>
</div>
<br>

<%--Pagination--%>
<c:if test="${requestScope.countOfPages > 1}">
  <div class="text-center">
    <ul class="pagination justify-content-center">
      <c:choose>
        <c:when test="${requestScope.page == null || requestScope.page == 1}">
          <li class="page-item disabled"><a class="page-link" aria-disabled="true" href="#">Previous</a></li>
        </c:when>
        <c:otherwise>
          <c:url value="/user/account" var="prevPageURL">
            <c:param name="username" value="${requestScope.account.username}"/>
            <c:param name="page" value="${requestScope.page - 1}"/>
          </c:url>
          <li class="page-item"><a class="page-link" href='<c:out value="${prevPageURL}" />'>Previous</a></li>
        </c:otherwise>
      </c:choose>


      <c:forEach var="i" begin="1" end="${requestScope.countOfPages}">
        <c:url value="/user/account" var="pageURL">
          <c:param name="username" value="${requestScope.account.username}"/>
          <c:param name="page" value="${i}"/>
        </c:url>
        <c:choose>
          <c:when test="${requestScope.page == null || requestScope.page == i}">
            <li class="page-item active"><a class="page-link"
                                            href='<c:out value="${pageURL}" />'><c:out
                    value="${i}"/></a></li>
          </c:when>
          <c:otherwise>
            <li class="page-item"><a class="page-link" href='<c:out value="${pageURL}" />'><c:out
                    value="${i}"/></a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>


      <c:choose>
        <c:when test="${requestScope.page == null || requestScope.page == requestScope.countOfPages}">
          <li class="page-item disabled"><a class="page-link" aria-disabled="true" href="#">Next</a></li>
        </c:when>
        <c:otherwise>
          <c:url value="/user/account" var="nextPageURL">
            <c:param name="username" value="${requestScope.account.username}"/>
            <c:param name="page" value="${requestScope.page + 1}"/>
          </c:url>
          <li class="page-item"><a class="page-link" href='<c:out value="${nextPageURL}" />'>Next</a>
          </li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>

</body>
</html>

