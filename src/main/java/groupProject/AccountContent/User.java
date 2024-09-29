package groupProject.AccountContent;

import java.util.ArrayList;

public class User {
	
	public String username;
	// Needs to be changed to a char array
	public String password;
	public Boolean hasAdminRole;
	public Boolean hasStudentRole;
	public Boolean hasInstructorRole;
	public String email;
	public String firstName;
	public String lastName;
	public String middleName;
	public String preferredName;
	public Boolean accountNeedsSetup;

	
	// Constructor parameters and body structure will most likely need to be changed
	public User (
		// Constructor parameters
		String usernameInput,
	        String passwordInput,
	        Boolean hasAdminRoleInput,
	        Boolean hasStudentRoleInput,
	        Boolean hasInstructorRoleInput
    	) {
		// Constructor body
		// User variables that have their values set up when the account is created
		// Page inputs
	        this.username = usernameInput;
	        this.password = passwordInput;
	        // Managed by account creation code
	        this.hasAdminRole = hasAdminRoleInput;
	        this.hasStudentRole = hasStudentRoleInput;
	        this.hasInstructorRole = hasInstructorRoleInput;
	        
	        // User variables that need to be set up on the account setup page after the user has created the account
	        this.email = "";
	        this.firstName = "";
	        this.lastName = "";
	        this.middleName = "";
	        this.preferredName = "";
	        
	        // Controls if the account needs to be set up further - better control safety then relying on the state of other variables being "" or not
	        this.accountNeedsSetup = true;
	}

	
	// Private variable only accessible to class
	// Static as it will be the same across all instances
	private static UserRole roles = new UserRole();

	
	// Returns an ArrayList with the roles associated with the user
	public ArrayList<UserRole.roleList> getUserRoles() {
		ArrayList<UserRole.roleList> currentAvailableRoles = new ArrayList<>();
		if(this.hasAdminRole)
		{
			currentAvailableRoles.add(roles.getAdmin());
		}
		if(this.hasStudentRole)
		{
			currentAvailableRoles.add(roles.getStudent());
		}
		if(this.hasInstructorRole)
		{
			currentAvailableRoles.add(roles.getInstructor());
		}
		
		return currentAvailableRoles;
	}

	
	// Sets the variable indicating the account needs further set up to false;
	public void userSetupAccount(
		// Account set up inputs
		String emailInput,
	        String firstNameInput,
	        String lastNameInput,
	        String middleNameInput,
	        String preferredNameInput
        ) {
		// Account set up body
		this.email = emailInput;
	        this.firstName = firstNameInput;
	        this.lastName = lastNameInput;
	        this.middleName = middleNameInput;
	        this.preferredName = preferredNameInput;
	        // Account no longer needs to be set up
		this.accountNeedsSetup = false;
	}

	
	// Used to check if the user will need to access the role selection page
	public boolean hasMultipleRoles() {
		ArrayList<UserRole.roleList> availableRolls = getUserRoles();
		if(availableRoles.size() > 1)
		{
			return true;
		}
		return false;
	}
}

