package dao;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import model.Task;

public interface IDAOTask {
	public JsonObject insertTask(Connection connection, JsonObject json) throws Exception ;
	public ArrayList<Task> getTasks(Connection connection, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
