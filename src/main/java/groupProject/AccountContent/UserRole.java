package groupProject.AccountContent;

public class UserRole {
	public enum rollList {
	    ADMIN,
	    STUDENT,
	    INSTRUCTOR
	}
	
	public rollList getAdmin() {
		return rollList.ADMIN;
	}
	
	public rollList getStudent() {
		return rollList.STUDENT;
	}
	
	public rollList getInstructor() {
		return rollList.INSTRUCTOR;
	}
}
