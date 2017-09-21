package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.Database;

/**
 * Servlet implementation class InsertTask
 */
@WebServlet("/InsertTask")
public class InsertTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		try {
			Database database = new Database();
			TaskManager projectManager = new TaskManager();
			JsonObject jsonOutput = null;
			Connection connection = database.getConnection();
			jsonOutput = projectManager.insertTask(connection, request, response);
			if (jsonOutput != null) {
				printWriter.println(jsonOutput);
			}
		} catch (Exception exception) {
			printWriter.println("Error: " + exception.getMessage());
		} finally {
			printWriter.close();
		}
	}
}
