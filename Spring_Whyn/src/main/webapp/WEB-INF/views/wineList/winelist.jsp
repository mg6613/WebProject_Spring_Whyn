<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h2 align="center">와인리스트</h2>
<br>
<table class="wineSearchTable">

<tr><td><select >
	<option>와인이름 </option>
	<option>나라 </option>
	<option>색</option>
	<option>와인특징 </option>
</select></td>


<td><input type="text" placeholder="와인이름/나라/색/와인특징을 입력 해주세요." name="wineSearchBar" size="50"></td>
<td><input type="submit"value="검색"></td></tr>

</table>
<br><br><br>
<table border="1" class="wineTable">
	<tr>
		<th width="41">번호</th>
		<th  width="90">와인이미지</th>
		<th  width="200">와인이름</th>
		<th  width="80">나라</th>
		<th>색</th>
		<th width="400">와인특징</th>
		<th width="40"><input type="checkbox" name="all" class="check-all"> </th>
	</tr>
	
 	<c:forEach items="${winelist}" var="dto" begin="${beginNum}" end="${ endNum}">
		<tr>
			<td><a href="wineContentView?pno=${dto.pno}">${dto.pno}</a></td>
			<td align="center"><img src="${pageContext.request.contextPath}/resources/${dto.pimage}" width="40" ></td>
			<td>${dto.pname}</td>
			<td>${dto.pcountry}</td>
			<td>${dto.pcolor}</td>
			<td >${dto.ptext}</td>
			<td> <form action="delete"> <input type="checkbox" name ="deleteCheck" class="ab" value="${dto.pno }" > </td>
			
		</tr>
	
	</c:forEach> 
	
	
		<tr>
		<td colspan="7" align="right">
		<input type="submit" value="삭제"  onclick="if(!confirm('삭제 하시겠습니까?')){return false;}" style="color: #505050; width: 45px;"></form></td>
		</tr>
			
		
		
		
</table>
					<a href="write_view" media="post">
					<input type="submit" value="글작성" />

	
	
<center>
<c:forEach begin="1" end="${totalPage}" varStatus="status">
<a href="AWineLPaging?page=${status.count}">[${status.count}]</a>	


</c:forEach>

			
<br><br><br><br>

</body>
</html>