<html>
	<head>
		<title>CXF Rest Upload</title>
		<script type="text/javascript">
			function formUpload(val) {
				alert(val);
				document.forms[0].btn.value = val;
				document.forms[0].submit();
			}
		</script>
	</head>
	<body>
		<form action="/services/upload" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="age" /></td>
			</tr>
			<tr>
				<td>Resume</td>
				<td><input type="file" name="resume" /></td>
			</tr>
			<tr>
				<td><input type="button" name="insert" value="Insert" onclick="formUpload(this.valueOf)"/></td>
				<td><input type="button" name="update"  value="Update" onclick="formUpload(this.valueOf)" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" name="delete" value="Delete"  onclick="formUpload(this.valueOf)" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="hidden" name="btn" /></td>
			</tr>
		</table>
		</form>
	</body>
</html>