import java.util.Scanner;

class ITServiceDesk{

    static Scanner input = new Scanner(System.in);
    static Staff[] staffAccount = new Staff[20];
    static int staffAccountCount = 0;
    static Ticket[] ticket = new Ticket[20];
    static int ticketCount = 0;
    static int menuChoice;
    static Boolean loggedIn = false;
    static String accountName = "";
    static Ticket archivedTickets[] = new Ticket[100];
    public static void main (String[] args){
        welcomeMenu();
    }
    
    public static void welcomeMenu(){
        do{
            
            System.out.println(
                "\nWelcome to Cinco IT Service Desk\n"+
                "--------------------------------");
            if(loggedIn == false){
                //If not logged in will display options for logging in or creating account
                System.out.println(
                    "You are currently not logged in\n"+
                    "Please select an option to continue\n"+
                    "1. Staff Login\n"+
                    "2. Forgot Password\n"+
                    "3. Create New Staff Account\n"+
                    "4. Technician Login\n"+
                    //"9. TEST ARRAYS\n"+
                    "0. Exit");

                //Loop for input validation
                while (!input.hasNextInt()) { 
                    System.out.println("Please select a number from the menu: ");
                    input.next();
                }
                    menuChoice = input.nextInt();
                    input.nextLine();

                if(menuChoice == 1){
                    staffLogin();
                }
                if(menuChoice == 2){
                    forgotPassword();
                }
                if(menuChoice == 3){
                    createAccount();
                }
                if(menuChoice == 4){
                    technicianLogin();
                }
                if(menuChoice == 9){// Currently used for testing information stored in arrays
                    testArray();
                }
            }else{
                //If logged in will display name and give options for tickets
                System.out.println(
                    "You are currently logged in as " + accountName +
                    "\nPlease select an option to continue\n"+
                    "1. Logout\n"+
                    "2. Submit new ticket\n"+
                    "3. Check Status of ticket\n"+
                    //"9. TEST ARRAYS\n"+
                    "0. Exit");
        
                //Loop for input validation
                while (!input.hasNextInt()) { 
                    System.out.println("Please select a number from the menu: ");
                    input.next();
                }
                
                menuChoice = input.nextInt();
                input.nextLine();

                if(menuChoice == 1){
                    loggedIn = false;
                }
                if(menuChoice == 2){
                    submitTicket();
                }
                if(menuChoice == 3){
                    System.out.println("This feature coming soon");
                        // checkTicketStatus();
                }
                if(menuChoice == 9){// Currently used for testing information stored in arrays
                    testArray();
                }
            }
        }
        while (menuChoice != 0);
        input.close();
    }

    // Login for staff
    public static void staffLogin(){
        System.out.println("Staff login");
        System.out.print("Email: ");
        String emailInput = input.nextLine();
        // Temporary variable for testing email and 
        // password against staffAccount array
        Staff temp = null;
        // Search staffAccount array for email
        for (int i = 0; i < staffAccountCount; i++){
            if (staffAccount[i].getEmail().equals(emailInput)){
                temp = staffAccount[i];
            }
        }
        // If email is not found, message displayed to user
        if(temp == null){
            System.out.println("\nEmail entered does not have an account");
        }else{
            // Email found, user enters password
            System.out.print("Password: ");
            String passwordInput = input.nextLine();
            // Checks if entered password is correct
            if(temp.getPassword().equals(passwordInput)){
            accountName = temp.getName();
            loggedIn = true;
            }else{
                System.out.println("\nIncorrect Password");
            }
        }
    }

    //Forgot password feature
    public static void forgotPassword() {
        // Gets users email address
        System.out.print("Forgot Password\n"+
        "Enter email: ");
        String email = input.nextLine();
        // Uses the unique email to find the account
        for (int i = 0; i < staffAccountCount; i++){
            if(email.equals(staffAccount[i].getEmail())){
                // Gets current password stored in staffAccount array
                String oldPassword = staffAccount[i].getPassword();
                // User enters new password
                System.out.print("Enter new password: ");
                String newPassword = input.nextLine();
                // Users password is reset to new password
                staffAccount[i].ResetPassword(oldPassword, newPassword);
            }
        }
    }

    //Create new acccount
    public static void createAccount() {
        System.out.println("Create new account");
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        Staff temp = null;
        // Searches staffAccount array for duplicate email
        for (int i = 0; i < staffAccountCount; i++){
            if(staffAccount[i].getEmail().equals(email)){
                temp = staffAccount[i];
            }
        }
        // If no email duplicates are found, email is unique
        if(temp == null){
            // Add new staff account to staffAccount array
            staffAccount[staffAccountCount] = new Staff(email, name, phoneNumber, password);
            staffAccountCount++;
        }else{
            // If duplicate is found, message displayed to user
            System.out.println("\nThe email entered is already registered.\n");
            // Gives options to either try again or return to menu.
            System.out.println(
                "1. Try again\n"+
                "2. Return to welcome menu");
            //Loop for input validation
            while (!input.hasNextInt()) { 
                System.out.println("Please select a number from the menu: ");
                input.next();
            }
            menuChoice = input.nextInt();
            input.nextLine();
            // Re-try creating account
            if(menuChoice == 1){
                createAccount();
            }
            // Return to welcome menu
            if(menuChoice == 2){
                welcomeMenu();
            }
        }
    }

    // Future login for technicians 
    public static void technicianLogin() {
        //TODO finish technicianLogin().
        String userNamePrompt = "Username: ";
        String passwordPrompt = "Password";
        String techUserName;
        String techPassword;
        System.out.print("Technician Login");
        System.out.println(userNamePrompt);
        techUserName = input.nextLine();
        System.out.println(passwordPrompt);
        techPassword = input.nextLine();

    }

    // Currently used for testing information stored in arrays
    public static void testArray() {
        //Test for staff accounts
        System.out.println("\nstaffAccount Array");
        for (int i = 0; i < staffAccountCount; i++){
            System.out.println("Name: " + staffAccount[i].getName());
            System.out.println("Email: " + staffAccount[i].getEmail());
            System.out.println("Phone: " + staffAccount[i].getPhoneNumber());
            System.out.println("Password: " + staffAccount[i].getPassword()+"\n");
        }
        // Test for tickets
        System.out.println("\nTicket Array");
        for (int i = 0; i < ticketCount; i++){
            System.out.println("Author: " + ticket[i].getTicketAuthor());
            System.out.println("Descrip: " + ticket[i].getDescription());
            System.out.println("Severity: " + ticket[i].getSeverity());
            System.out.println("Status: " + ticket[i].getStatus());
            System.out.println("openDate: " + ticket[i].getOpenDate()+"\n");
        }
    }

    // Submit ticket
    public static void submitTicket() {
        // Gets details of IT issue for ticket
        
        if(loggedIn == false){
            staffLogin();
        }
        System.out.println(
        "\n\nTicket author: " + accountName + "\n"+
        "Please complete the following information");
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Severity of issue (low, medium, high): ");
        String severity = input.nextLine();
        System.out.print("Submit ticket? Y/N: ");
        String submit = input.nextLine();
        
        //If yes to submit, checks user input for severity and assigns correct enum
        if (submit.equalsIgnoreCase("Y")){
            if(severity.equalsIgnoreCase("LOW")){
                ticket[ticketCount] = new Ticket(Integer.toString(ticketCount), accountName, description, TicketSeverity.Low);
                ticketCount++;
            }
            else if(severity.equalsIgnoreCase("MEDIUM")){
                ticket[ticketCount] = new Ticket(Integer.toString(ticketCount), accountName, description, TicketSeverity.Medium);
                ticketCount++;
            }
            else if(severity.equalsIgnoreCase("HIGH")){
                ticket[ticketCount] = new Ticket(Integer.toString(ticketCount), accountName, description, TicketSeverity.High);
                ticketCount++;
            }
            else{// Error in submitting ticket returns user to beginning of ticket process.
                System.out.println("\nError in submitting ticket. Please try again");
                submitTicket();
            }
            
            //If user does not want to submit ticket, they are returned to welcome menu.
        }else if(submit.equalsIgnoreCase("N")){
            welcomeMenu();
        }else{
            System.out.println("Unexpected error.");
            welcomeMenu();
        }
    }

    // TODO Create method to setup pre-created technician accounts.
    private void setupTechnicians(){
        
    }
}
    