package groupProject.AccountContent;

public class UserRole {
	public enum roleList {
	    ADMIN,
	    STUDENT,
	    INSTRUCTOR
	}
	
	public roleList getAdmin() {
		return roleList.ADMIN;
	}
	
	public roleList getStudent() {
		return roleList.STUDENT;
	}
	
	public roleList getInstructor() {
		return roleList.INSTRUCTOR;
	}
}
