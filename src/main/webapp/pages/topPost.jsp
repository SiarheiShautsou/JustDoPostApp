<%--
  Created by IntelliJ IDEA.
  User: Siarhei
  Date: 26.07.2023
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Explore page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="row justify-content-center">
  <div class="col-4">
    <nav class="navbar bg-body-tertiary">
      <div class="container-fluid">
        <form action="/user/searchuser" class="d-flex" role="search">
          <input name="username" class="form-control me-2" type="search" placeholder="Search user" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <c:if test="${userNotFound != null}">
          <div class="alert alert-danger" role="alert">
              ${userNotFound}
          </div>
        </c:if>
      </div>
    </nav>
  </div>
</div>

<div class="container">
  <div class="row">
    <c:forEach items="${postList}" var="operation">
      <fmt:parseDate value="${post.createdAt}" var="parsedTime" pattern="yyyy-MM-dd'T'HH:mm" type="date"/>
      <fmt:formatDate value="${parsedTime}" pattern="dd.MM.yyyy HH:mm" var="formattedTime"/>
      <div class="col-sm-4">
        <div class="card mt-5" style="width: 30rem;">
          <p style="margin-left: 20px; padding: unset; text-decoration: unset">
            <a href="/user/profile?username=${post.author.username}" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">
                ${post.author.username}
            </a>
          </p>
          <a href="/user/viewpost?id=${post.id}" style="padding: unset; text-decoration: unset">
            <img src="${post.image}" alt="post image">
          </a>
          <div class="card-body">
            <p class="card-text">${post.description}</p>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
