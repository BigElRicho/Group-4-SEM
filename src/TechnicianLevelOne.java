import jdk.jfr.Description;

public class TechnicianLevelOne implements TechnicianInterface{

    //Attributes
    private final int TICKET_QUOTA = 20;
    private Ticket CurrentTicketList[] = new Ticket[TICKET_QUOTA] ;
    private int numberOfTicketsCurrentlyAssigned = 0;
    private final int TECHNICIAN_LEVEL = 1;
    private String userName = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";


    //Constructors
    public TechnicianLevelOne(int startingTicktes, String userName, String password, String firstName, String lastName ){
        this.numberOfTicketsCurrentlyAssigned = startingTicktes;
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
    public String escalateTicket(Ticket ticket) {
        // TODO Finish escalate issue function.
        String successMsg = "Issue escalated to: ";
        String failMsg = "Issue unable to be escalated.";

        if(ticket.getSeverity().equals(TicketSeverity.Low)){
            ticket.setSeverity(TicketSeverity.Medium);
            return successMsg + ticket.getSeverity();
        }
        else if(ticket.getSeverity().equals(TicketSeverity.Medium)){
            ticket.setSeverity(TicketSeverity.High);
            //TODO Have this method move ticket two a level 2 technician.
            return successMsg + ticket.getSeverity();
        }
        else{
            return failMsg;
        }
    }

    @Override
    @Description("Changes the issue from Level 2 to level 1")
    public String deescalateTicket(Ticket ticket) {
        // TODO Finish de-escalate issue function.
        String successMsg = "Issue deescalated to: ";
        String failMsg = "Issue unable to be deescalated.";

        //Medium to low.
        if(ticket.getSeverity().equals(TicketSeverity.Medium)){
            ticket.setSeverity(TicketSeverity.Low);
            return successMsg + ticket.getSeverity();
        }
        else{
            return failMsg;
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
    public String toString(){
        return this.firstName +" "+ this.lastName;
    }

}