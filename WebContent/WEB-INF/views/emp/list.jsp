<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var label = $(this).next(":hidden").val();
			var flag = confirm("Are you sure to delete " + label + " ?");
			if (flag) {
				var url = $(this).attr("href");
				$("#_form").attr("action", url);
				$("#_method").val("DELETE");
				$("#_form").submit();
			}
			return false;
		});

	});
</script>

</head>
<body>


	<!-- 用于删除操操作，实现POST->Delete请求操作，从Get请求转。 -->
	<form action="" method="POST" id="_form">
		<input type="hidden" id="_method" name="_method" />
	</form>

	<c:if test="${page == null || page.numberOfElements == 0 }">
		已经到了最后一页了，没有了。 
	</c:if>
	<c:if test="${page != null && page.numberOfElements>0}">
		<table border="1" cellpadding="10" cellspacing="0" align="center">
			<tr>
				<th>ID</th>
				<th>LastName</th>

				<th>Email</th>
				<th>Birth</th>

				<th>CreateTime</th>
				<th>Department</th>

				<th>EDIT</th>
				<th>DELETE</th>
			</tr>

			<c:forEach items="${page.content}" var="emp">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.lastName}</td>

					<td>${emp.email}</td>
					<td><fmt:formatDate value="${emp.birth}" pattern="yyyy-MM-dd" />
					</td>
					<td><fmt:formatDate value="${emp.createTime}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<td>${emp.department.department_name}</td>
					<td><a href="${pageContext.request.contextPath}/emp/${emp.id}">Edit</a></td>
					<td><a href="${pageContext.request.contextPath}/emp/${emp.id}"
						class="delete">Delete</a> <input type="hidden"
						value="${emp.lastName}" /></td>

				</tr>
			</c:forEach>
			<tr>

				<td colspan="8">共 <span style="color: blue">${page.totalElements}</span>
					条记录 共 <span style="color: blue">${page.totalPages}</span> 页 当前第 <span
					style="color: red">${page.number+1}</span> 页 <a
					href="?pageNo=${page.number+1-1}">上一页</a> <a
					href="?pageNo=${page.number+1+1}">下一页</a>
				</td>
			</tr>

		</table>

	</c:if>



</body>
</html>