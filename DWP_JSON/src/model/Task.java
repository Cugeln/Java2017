package model;

public class Task {

	private String task_id;
	private String task;
	private int priority;

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String msg_id) {
		this.task_id = msg_id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "" + this.getTask_id() + "-" + this.getTask() + "-" + this.getPriority();
	}

}
