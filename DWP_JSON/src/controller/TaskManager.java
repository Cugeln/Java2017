package controller;

import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.DAOTaskImplementation;
import model.Task;

public class TaskManager {
	public JsonObject insertTask(Connection connection, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsonObject jsonInput = null;
		JsonObject jsonOutput = null;
		try {
			jsonInput = (JsonObject) new JsonParser().parse(request.getReader().readLine());
			System.out.println("Task=" + jsonInput.get("Task"));
			System.out.println("Priority=" + jsonInput.get("Priority"));
			if (jsonInput != null) {
				DAOTaskImplementation project = new DAOTaskImplementation();
				jsonOutput = project.insertTask(connection, jsonInput);
			}
		} catch (Exception e) {
			throw e;
		}
		return jsonOutput;
	}

	public ArrayList<Task> getTasks(Connection connection, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Task> tasks = null;
		try {
			DAOTaskImplementation project = new DAOTaskImplementation();
			tasks = project.getTasks(connection, request, response);

		} catch (Exception e) {
			throw e;
		}
		return tasks;
	}

}
