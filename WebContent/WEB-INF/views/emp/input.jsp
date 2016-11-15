<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Employee</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#lastName")
				.change(
						function() {
							var val = $(this).val();
							val = $.trim(val);
							$(this).val(val);

							// 若修改的LastName与之前的LastName同名，则不发送Ajax.
							var _oldLastName = $("#_oldLastName").val();
							_oldLastName = $.trim(_oldLastName);

							if (_oldLastName != null && _oldLastName != ""
									&& _oldLastName == val) {
								alert("LastName 可用");
								return;
							}

							var url = "${pageContext.request.contextPath}/ajaxValidateLastName";
							var args = {
								"lastName" : val,
								"date" : new Date()
							};

							$.post(url, args, function(data) {
								if (data == "0") {
									alert("LastName 可用");
								} else if (data == "1") {
									alert("LastName 不可用");

								} else {
									alert("网络异常！");
								}
							});

						})
	})
</script>
</head>
<body>
	
	<c:set value="${pageContext.request.contextPath }/emp" var="url">
	
	</c:set>
	<c:if test="${employee.id !=null}">
		<c:set value="${pageContext.request.contextPath}/emp/${employee.id}" var="url"></c:set>
	</c:if>

	<form:form action="${url}"
		method="POST" modelAttribute="employee">

		<c:if test="${employee.id !=null}">
			<input type="hidden" id="_oldLastName" value="${employee.lastName}">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT" />
		</c:if>

		<table align="center"
			style="border: 1px; border-color: red; padding: 10;">
			<tr>
				<td colspan="2" style="font-size: 20px;">添加新员工</td>
			</tr>
			<tr>
				<td>LastName:</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Birth:</td>
				<td><form:input path="birth" /></td>
			</tr>
			<tr>
				<td>Department:</td>
				<td><form:select path="department.id" items="${departments}"
						itemLabel="department_name" itemValue="id"></form:select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value=" 保存  "></td>
			</tr>
		</table>

	</form:form>



</body>
</html>