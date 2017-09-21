package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import model.Task;

public class DAOTaskImplementation implements IDAOTask {
	public JsonObject insertTask(Connection connection, JsonObject json)
			throws Exception {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO tasks (task,priority) VALUES(?,?)");
			preparedStatement.setString(1, json.get("Task").toString().replaceAll("\"", ""));
			preparedStatement.setInt(2, Integer.parseInt(json.get("Priority").toString().replaceAll("\"", "")));
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				//return json.get("Task").toString().replaceAll("\"", "") + "/" + json.get("Priority").toString().replaceAll("\"", "");
				return json;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Task> getTasks(Connection connection,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Task> taskData = new ArrayList<Task>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT task_id,task, priority FROM tasks ORDER BY task_id DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Lese Tasks ...");
			while (resultSet.next()) {
				Task task = new Task();
				task.setTask_id(resultSet.getString("task_id"));
				task.setTask(resultSet.getString("task"));
				task.setPriority(resultSet.getInt("priority"));
				taskData.add(task);
				//System.out.println(task.toString());
			}
			return taskData;
		} catch (Exception exception) {
			throw exception;
		}
	}

}
