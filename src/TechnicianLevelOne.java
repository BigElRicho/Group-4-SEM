import jdk.jfr.Description;

public class TechnicianLevelOne implements TechnicianInterface{

    //Attributes
    private final int TICKET_QUOTA = 20;
    private Ticket currentTicketList[] = new Ticket[TICKET_QUOTA] ;
    private int numberOfTicketsCurrentlyAssigned = 0;
    private final int TECHNICIAN_LEVEL = 1;
    private String userName = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";


    //Constructors
    public TechnicianLevelOne(String userName, String password, String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    //Methods
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }
    

    @Override
    @Description("Changes the issue from level 1 to level 2.")
    public String changeTicketSeverity(Ticket ticket, TicketSeverity newSeverity) {
        // TODO perform testing on this function
        String successMsg = "Ticket successfully changed to: ";
        String failMsg = "Issue unable to be changed as it is already set to: ";
        
        //Check if severity is already set to the new severity level.
        if(ticket.getSeverity().equals(newSeverity)){
            return failMsg + newSeverity;
        }
        //...else change the severity
        else{
            ticket.setSeverity(newSeverity);
            //if it gets set to HIGH, then store in the Service desk tempTicket array.
            if(ticket.getSeverity().equals(TicketSeverity.High)){
                ITServiceDesk.tempTickets[0] = ticket;
                //Show that its actually in the list.
                System.out.println(ITServiceDesk.tempTickets[0]);
                //Remove ticket from currentList.
                removeTicketfromList(ticket);
            }
            return successMsg + newSeverity;
        }
    }

    @Override
    public String closeTicketWithoutResolution(Ticket ticket) {
        // TODO Build close without resolution function.
        return null;
    }

    @Override
    public String closeAndResolveTicket(Ticket ticket) {
        // TODO Build close and resolve method.
        return null;
    }

    @Override
    public String changePassword(String newPassword){

        String successMsg = "Password changed to: " + password + ".";
        String failMsg = "Password was not changed. New password was blank or there was another issue.";
        
        if(newPassword != null){
            this.password = newPassword;
            return successMsg;
        }
        else{
            return failMsg;
        }
    }

    @Override
    public String changeUsername(String newUsername) {

        String successMsg = "Username changed to: " + userName + ".";
        String failMsg = "Username was not changed. New username was either blank or there was another issue.";

        if(newUsername != null){
            this.userName = newUsername;
            return successMsg;
        }
        else{
            return failMsg;
        }
        
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getTechnicianLevel() {
        return TECHNICIAN_LEVEL;
    }

    @Override
    public int getNumberOfTicketsCurrentlyAssigned() {
        return numberOfTicketsCurrentlyAssigned;
    }

    @Override
    public Ticket[] getCurrentTicketList() {
        return currentTicketList;
    }

    private String removeTicketfromList(Ticket ticket){

        String successMsg = " removed from this technicians list.";
        String failMsg = "Ticket was not removed from the technician, as it could not be found or for another reason.";

        //Find the ticket and remove from list
        for(int i = 0;i < getCurrentTicketList().length;i++){
            if(currentTicketList[i].getId() == ticket.getId()){
                currentTicketList[i].equals(null);
                return ticket.getId() + successMsg;
            }
        }
        //if it cannot be found, send a failure message.
        return failMsg;
    }

    @Override
    public String toString(){
        return this.firstName +" "+ this.lastName;
    }
}