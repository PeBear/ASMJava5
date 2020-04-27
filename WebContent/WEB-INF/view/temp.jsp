<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="staffManage/insertPhoto.htm" class="was-validated" method="post"
	id="staffForm" name="staffForm" enctype="multipart/form-data">

	<div class="form-group">
		<input type="file" id="imgInp" name="filePhoto" />
	</div>

	<button class="btn btn-primary" id="btnSave" type="submit">Save</button>

</form>