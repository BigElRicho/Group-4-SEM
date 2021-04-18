
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

class ITServiceDesk{

    static Scanner input = new Scanner(System.in);
    static Staff[] staffAccount = new Staff[50];
    static TechnicianInterface[] technicianAccounts = new TechnicianInterface[50];
    static int technicianAccountCount = 5;
    static int staffAccountCount = 0;
    static String loggedinUserType = "";
    static Ticket[] ticket = new Ticket[20];
    static int ticketCount = 0;
    static int menuChoice;
    static Boolean loggedIn = false;
    static String accountName = "";
    static String accountEmail = "";
    static Ticket archivedTickets[] = new Ticket[100];
    static Ticket[] tempTickets = new Ticket[1];

    public static void main (String[] args){
        setupTechnicians();
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
                // Used for testing information stored in arrays
                if(menuChoice == 9){// Used for testing information stored in arrays
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
                    "4. Change Ticket Severity\n"+
                    "5. Display all archived tickets\n" +
                    "6. View closed tickets\n"+
                    "7. View all assigned tickets\n"+
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
                    checkTicketStatus();
                }
                if(menuChoice == 4){
                    ChangeTicketSeverity();
                }
                if(menuChoice == 5){
                    int i=0;
                    // System.out.println("This feature is coming soon");
                    while(archivedTickets[i] != null)
                    {
                        System.out.println(archivedTickets[i]);
                    }
                }
                if(menuChoice == 6){
                    System.out.println("This feature is coming soon");
                }
                if(menuChoice == 7){
                    System.out.println("This feature is coming soon");
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
            accountEmail = temp.getEmail();
            loggedIn = true;
            loggedinUserType = "staff";
            }else{
                System.out.println("\nIncorrect Password");
            }
        }
    }

    //Forgot password feature
    public static void forgotPassword() {
        // Gets users email address
        System.out.print(
            "\nForgot Password\n" +
            "---------------\n" +
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

                // Validates user input for password rules
                while(!passwordValidation(newPassword)){       
                    System.out.print("Password: ");
                    newPassword = input.nextLine();
                }
                // Users password is reset to new password
                staffAccount[i].ResetPassword(oldPassword, newPassword);
            }else{
                System.out.println("This email does not have an account");
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

        // Validates user input for password rules
        while(!passwordValidation(password)){       
            System.out.print("Password: ");
            password = input.nextLine();
        }

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

    // Validates user input for password
    public static boolean passwordValidation(String password){
        boolean isValid = true;
        // Tests for password length of at least 20
        if(password.length() < 20){
            System.out.println("**Password must be at least 20 characters");
            isValid = false;
        }
        // Tests for lowercase characters
        if(!Pattern.compile("[a-z]").matcher(password).find()){
            System.out.println("**Password must contain at least one lowercase character");
            isValid = false;
        }
        // Tests for uppercase characters
        if(!Pattern.compile("[A-Z]").matcher(password).find()){
            System.out.println("**Password must contain at least one uppercase character");
            isValid = false;
        }
        // Tests for numbers
        if(!Pattern.compile("[0-9]").matcher(password).find()){
            System.out.println("**Password must contain at least one number");
            isValid = false;
        }
        // Returns true or false
        return isValid;
    }

    public static void technicianLogin() {

        String userNamePrompt = "Username: ";
        String passwordPrompt = "Password:";
        String accConfirmMsg = "Account Found: ";
        String pwMatchMsg = "Password correct. You are now logged in as: ";
        String accNotFoundMsg = "Not matching account. Try again.";
        String pwIncorrectMsg = "Password incorrect. Try again.";
        String techUserName;
        String techPassword;
        boolean usernameFound = false;
        boolean passwordMatches = false;
        int foundAccountIndex = 0;

        System.out.println("Technician Login");
        //Get and find matching username.
        System.out.println(userNamePrompt);
        while(usernameFound == false){
            techUserName = input.nextLine();
            for(int i = 0;i<technicianAccountCount;i++){
                if(technicianAccounts[i].getUsername().equalsIgnoreCase(techUserName)){
                    foundAccountIndex = i;
                    usernameFound = true;
                    System.out.println(accConfirmMsg + technicianAccounts[i].getUsername());
                }            
            }
            //If technician account is not found.
            if(usernameFound == false){
                System.out.println(accNotFoundMsg);
                welcomeMenu();
            }
        }
        
        //Get and check password.
        while(passwordMatches == false){
            System.out.println(passwordPrompt);
            techPassword = input.nextLine();
                if(technicianAccounts[foundAccountIndex].getPassword().equalsIgnoreCase(techPassword))
                {
                    System.out.println(pwMatchMsg + technicianAccounts[foundAccountIndex].getUsername());
                    loggedIn = true;
                    passwordMatches = true;
                    accountName = technicianAccounts[foundAccountIndex].getUsername();
                    loggedinUserType = "Technician";
                }
                else{
                    System.out.println(pwIncorrectMsg);
                }
        }
        
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
        if(loggedIn == false){
            staffLogin();
        }
        // Gets details of IT issue for ticket
        System.out.println(
        "\nSubmit New Ticket" +
        "\n-----------------" +
        "\nTicket author: " + accountName +
        "\nPlease complete the following information");
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Severity of issue (low, medium, high): ");
        String severity = input.nextLine();
        System.out.print("Submit ticket? Y/N: ");
        String submit = input.nextLine();
        
        //If yes to submit, checks user input for severity and assigns correct enum
        if (submit.equalsIgnoreCase("Y")){
            if(severity.equalsIgnoreCase("LOW")){
                ticket[ticketCount] = new Ticket(Integer.toString(ticketCount), accountName, accountEmail, description, TicketSeverity.Low);
                ticketCount++;
            }
            else if(severity.equalsIgnoreCase("MEDIUM")){
                ticket[ticketCount] = new Ticket(Integer.toString(ticketCount), accountName, accountEmail, description, TicketSeverity.Medium);
                ticketCount++;
            }
            else if(severity.equalsIgnoreCase("HIGH")){
                ticket[ticketCount] = new Ticket(Integer.toString(ticketCount), accountName, accountEmail, description, TicketSeverity.High);
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

    public static void checkTicketStatus(){
        System.out.println(
            "\nTicket Status\n" +
            "-------------\n" +
            "All open tickets submitted by " + accountName);
        for (int i = 0; i < ticketCount; i++){
            // Finds the correct user by their unique email
            if (ticket[i].getAuthorEmail() == accountEmail){
                // Displays all open tickets the user has submitted
                if(ticket[i].getStatus() == TicketStatus.Open){
                    System.out.println(
                        "\nTicket ID: " + ticket[i].getId() + 
                        "\nDescription: " + ticket[i].getDescription() + 
                        "\nSeverity: " + ticket[i].getSeverity() +
                        "\nOpen Date: " + ticket[i].OpenDate + 
                        "\nStatus: " + ticket[i].getStatus());
                }
            }
        }    
    }

    public static void setupTechnicians(){
        //Level 1 technician instantiations.
        TechnicianLevelOne harryStyles = new TechnicianLevelOne("harry.styles", "password", "Harry", "Styles");
        TechnicianLevelOne niallHoran = new TechnicianLevelOne("niall.horan", "password", "Niall", "Horan");
        TechnicianLevelOne liamPayne = new TechnicianLevelOne("liam.payne", "password", "Liam", "Payne");

        //Level 2 technician instantiations.
        TechnicianLevelTwo louisTomlinson = new TechnicianLevelTwo("louis.tomlinson","password","Louis","Tomlinson");
        TechnicianLevelTwo zayneMalick = new TechnicianLevelTwo("zayn.malik","password","Zayn","Malik");

        //Add to technicianAccounts array.
        technicianAccounts[0] = harryStyles;
        technicianAccounts[1] = niallHoran;
        technicianAccounts[2] = liamPayne;
        technicianAccounts[3] = louisTomlinson;
        technicianAccounts[4] = zayneMalick;

        technicianAccounts[0].getCurrentTicketList()[0] = ticket[0];
        technicianAccounts[0].getCurrentTicketList()[1] = ticket[1];

        //Print out user names to confirm presence.
        for(int i = 0; i<technicianAccountCount;i++){
            System.out.println("Technician Account " + technicianAccounts[i].getUsername() + " loaded.");
        }
    }

        //Method used for checking the tempTicket array. If there is a ticket found
    //It will atempt to assign it based on severity and technician with the lowest ticket count
    private static void ReAssignTicket()
    {
        if(tempTickets.length > 1)
        {
            AssignTicket(tempTickets[0]);
        }

        tempTickets[0] = null;
    }

    //Assign ticket to technician
    private static void AssignTicket(Ticket ticket)
    {
        int LowestTicketCount = 0;
        int RequiredTechnicianLevel;
        int TechnicianIndex = 0;

        //If a tickets severity is HIGH, then  it should be assigned to a level 2 tech
        //Otherwise it can go to a level 1 tech
        if(ticket.getSeverity() == TicketSeverity.High)
        {
            RequiredTechnicianLevel = 2;
        }
        else
        {
            RequiredTechnicianLevel = 1;
        }

        //Find the technician with the lowest ticket count
        for(int i = 0; i < technicianAccountCount; i++)
        {         
            //If this is the first time through the loop, set default values
            if(i == 0)
            {
                LowestTicketCount = technicianAccounts[i].getNumberOfTicketsCurrentlyAssigned();
            }
            else if(Objects.nonNull(technicianAccounts[i])) //Make sure its not a null object
            {
                //If the current techs level is correct and they have a lower ticket count than the previous tech
                if(technicianAccounts[i].getTechnicianLevel() == RequiredTechnicianLevel && technicianAccounts[i].getNumberOfTicketsCurrentlyAssigned() < LowestTicketCount)
                {
                    //Make note of their index and assign the new lowest ticket count
                    TechnicianIndex = i;
                    LowestTicketCount = technicianAccounts[i].getNumberOfTicketsCurrentlyAssigned();
                }
            }
        }
        
        //Insert the new ticket -> Consider making this a method in the technician to avoid errors
        technicianAccounts[TechnicianIndex].getCurrentTicketList()[LowestTicketCount] = ticket;
    }

    //Validate Technician is Currently loged in
    private static int ValidateCurrentUserTechnician()
    {
        if(loggedIn != false)
        {
            int index  = 0;
            for (TechnicianInterface t : technicianAccounts) 
            {
                if(t.getUsername().equals(accountName))
                {
                    //return the technicians index in the array
                    return index;
                }
                index++;
            }
        }

        //Not a technician
        return -1;
    }

    //Escalate Ticket
    public static void ChangeTicketSeverity()
    {
        int TechnicianIndex = ValidateCurrentUserTechnician();
        
        //If the technician is in the array
        if(TechnicianIndex >= 0)
        {
            System.out.println(String.format("You currently have %d tickets assigned to you\n", technicianAccounts[TechnicianIndex].getNumberOfTicketsCurrentlyAssigned()));
            
            //Print technicians ticket list
            for(Ticket t : technicianAccounts[TechnicianIndex].getCurrentTicketList())
            {
                if(t != null) //Because we can have null objects
                {
                    System.out.println(t);
                }
            }

                System.out.println("Enter the ticket ID of the ticket you wish to escalate: ");

                String TicketID = input.nextLine();

                for(Ticket ticket : technicianAccounts[TechnicianIndex].getCurrentTicketList())
                {
                    if(Objects.nonNull(ticket) && ticket.getId().equals(TicketID)) // Avoid null objects
                    {
                        System.out.println("What severity level would you like to escalte this ticket to?\n1. Low\n2. Medium\n3. High");
                        
                        int newLevel = input.nextInt();

                        //Change severity based on the input 1 -> 3
                        switch(newLevel)
                        {
                            case 1: 
                                technicianAccounts[TechnicianIndex].changeTicketSeverity(ticket, TicketSeverity.Low);
                                break;
                            case 2: 
                                technicianAccounts[TechnicianIndex].changeTicketSeverity(ticket, TicketSeverity.Medium);
                                break;
                            case 3: 
                                technicianAccounts[TechnicianIndex].changeTicketSeverity(ticket, TicketSeverity.High);
                                break;
                            default: 
                                System.out.println("Error. Be sure to enter a number 1-3\n");
                        }

                        //Check to see if the ticket needs to be reassigned
                        ReAssignTicket();
                    }
                }

                System.out.println("Invalid Ticket ID, please try again\n");
        }
        else
        {
            System.out.println("This feature is only available for technicians.\n");
        }
    }

    public static String setupTestTickets(){
        String setupMsg = "Test tickets have been created:";

        //Create a few tickets
        Ticket ticket1 = new Ticket("1", "test1@test.com","Tester 1", "Test 1");
        Ticket ticket2 = new Ticket("2", "test2@test.com","Tester 2", "Test 2" );
        Ticket ticket3 = new Ticket("3", "test1@test.com","Tester 1", "Test 3");
        Ticket ticket4 = new Ticket("4", "test2@test.com","Tester 2", "Test 4");
        Ticket ticket5 = new Ticket("5", "test1@test.com","Tester 1", "Test 5");
        Ticket ticket6 = new Ticket("6", "test2@test.com","Tester 2", "Test 6");
        Ticket ticket7 = new Ticket("7", "test1@test.com","Tester 1", "Test 7");
        Ticket ticket8 = new Ticket("8", "test2@test.com","Tester 2", "Test 8");
        Ticket ticket9 = new Ticket("9", "test1@test.com","Tester 1", "Test 9");
        Ticket ticket10 = new Ticket("10", "test2@test.com","Tester 2", "Test 10");

        ticket[0] = ticket1;
        ticket[1] = ticket2;
        ticket[2] = ticket3;
        ticket[3] = ticket4;
        ticket[4] = ticket5;
        ticket[5] = ticket6;
        ticket[6] = ticket7;
        ticket[7] = ticket8;
        ticket[8] = ticket9;
        ticket[9] = ticket10;

        return setupMsg;
    }

    public static String setupTestStaffAccounts(){
        String setupMsg = "Test staff accounts have been loaded.";

        //Create a couple staff accounts.
        Staff staff1 = new Staff("test1@test.com", "Tester 1", "0498765432", "test1");
        Staff staff2 = new Staff("test2@test.com", "Tester 2", "0412345678", "test2");
        //Store them.
        staffAccount[0] = staff1;
        staffAccount[1] = staff2;

        return setupMsg;
    }
}