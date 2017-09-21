<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tasks Editor [JSON2]</title>
<script type="text/javascript" src='jquery/jquery-2.1.1.min.js'></script>
<script type='text/javascript'>

// jQuery(document).ready(): Callback-Function will be executed if DOM is created
jQuery(document).ready(
	function() {
		// Communication-Parameter: AJAX (Asynchronous JavaScript and XML, use JSON)
		jQuery.ajax({
			type : "POST",
			// @WebServlet("/GetTasks"): See Java Controls
			url : "GetTasks",
			dataType : "json",
			// data: Return values of @WebServlet("/GetTasks")
			// Examples: {"Tasks":[{"task_id":"14","task":"66666","priority":1},{"task_id":"13","task":"5555","priority":1},{"task_id":"12","task":"5555","priority":1},{"task_id":"11","task":"7777","priority":1},{"task_id":"10","task":"9999","priority":1},{"task_id":"9","task":"4444","priority":1},{"task_id":"8","task":"3333","priority":1},{"task_id":"7","task":"22222","priority":1},{"task_id":"6","task":"1111111","priority":1},{"task_id":"5","task":"1234","priority":1},{"task_id":"4","task":"1234","priority":1},{"task_id":"3","task":"1234","priority":1},{"task_id":"2","task":"111","priority":3},{"task_id":"1","task":"123","priority":1}]}
			success : function(data) {
				if (data.Tasks.length) {
					// each: for all Element in data.Tasks, index von 0..n-1
					jQuery.each(data.Tasks, function(index, data) {
						var task_data = "<div  id='task"+data.task_id+"'>"
							// + index + "/"
							+ data.task + "/" + data.priority + "</div>";
						// appendTo: append taskdata on division "#content"	
						jQuery(task_data).appendTo("#Content");
	
					});
					} else {
						jQuery("#content").html("No tasks!");
					}
				}
		});
		// click: Eventhandling for HTML-Element with ID '#InsertTask'
		jQuery('#InsertTask').click(function() {
			var task = $("#Task").val();
			var dataString = 'Task=' + task;
			var priority = $("#Priority").val();
			dataString += '&Priority=' + priority;
			// Read parameter in HTML-Form
			// alert( dataString);
			
			// Create JSON
			var jsonTask = "{\"Task\":\"" + jQuery("#Task").val() + "\", \"" + "Priority\":\"" + jQuery("#Priority").val() + "\"}";				
			jQuery.ajax({
				type : "POST",
				// @WebServlet("/InsertTask"): See Java Controls
				url : "InsertTask",
				dataType: "json",
				// Contentype and encoding for JSON
			    contentType: "application/json; charset=utf-8",
			    data: jsonTask,
			    // traditional: true: Create a serialized string for JSON
			    traditional: true,
			    // complete: Callback-Funcion will be executed every time
			    // jqXHR: XMLHttpRequest-Object for @WebServlet("/InsertTask")
			    complete:function(jqXHR) {
				    // Erase content Text-Area
			    	jQuery("#Task").val('');
			    	if(jqXHR.responseText != null ) {
			    		var jsonOutput = jQuery.parseJSON(jqXHR.responseText);
			    		// Insert new task on top
			    		//jQuery("#Content").prepend("<div>" + jsonOutput.Task + "/" + jsonOutput.Priority + "</div>");
			    		// Insert new task as last entry
			    		jQuery("<div>" + jsonOutput.Task + "/" + jsonOutput.Priority + "</div>").appendTo("#Content");
			    	} else {
			    		alert("Error: Task is not saved!");
			    	}		    	
			    	jQuery("#Task").focus();
				}
			});
			return false;
		});

	});
</script>
<style>
#Content div {
	padding: 4px;
	margin-bottom: 2px;
	background-color: #dedede;
	font-family: arial;
}
</style>
</head>
	<body>
		<form>
			<p>
				Task:<br>
				<textarea id='Task'></textarea>
			</p>
			<p>
				Priority<br>
				<select id="Priority" size="1">
					<option>1</option>
					<option>2</option>
					<option>3</option>
				</select>
			</p>
			<p>
				<input type='button' value='Save task' id='InsertTask' />
			</p>
			<p id="Tasks">
				<div id='Content'></div>
		</form>
	</body>
</html>
