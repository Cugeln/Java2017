package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import dao.Database;
import model.Task;

/**
 * Servlet implementation class GetTasks
 */
@WebServlet("/GetTasks")
public class GetTasks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTasks() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {

			Database database = new Database();
			TaskManager projectManager = new TaskManager();
			ArrayList<Task> tasks = null;
			Connection connection = database.getConnection();
			tasks = projectManager.getTasks(connection, request, response);
			Gson gson = new Gson();
			String tasksAsString = gson.toJson(tasks);
			System.out.println("Tasklist ----> {\"Tasks\":" + tasksAsString.toString() + "}");
			printWriter.println("{\"Tasks\":" + tasksAsString + "}");
		} catch (Exception ex) {
			printWriter.println("Error: " + ex.getMessage());
		} finally {
			printWriter.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
