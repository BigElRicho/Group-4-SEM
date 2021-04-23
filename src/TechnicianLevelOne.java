import java.util.ArrayList;

public class TechnicianLevelOne implements TechnicianInterface{

    //Attributes
    //private final int TICKET_QUOTA = 20;
    private ArrayList<String> currentTicketList = new ArrayList<String>();
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
    public String changeTicketSeverity(String ticketID, TicketSeverity newSeverity) {
        String successMsg = "Ticket successfully changed to: ";
        String failMsg = "Issue unable to be changed as it is already set to: ";
        
        //Check if severity is already set to the new severity level.
        if(ITServiceDesk.findTicket(ticketID).getSeverity().equals(newSeverity)){
            return failMsg + newSeverity;
        }
        //...else change the severity
        else{
            ITServiceDesk.findTicket(ticketID).setSeverity(newSeverity);
            //if it gets set to HIGH, then store in the Service desk tempTicket array.
            if(ITServiceDesk.findTicket(ticketID).getSeverity().equals(TicketSeverity.High))
            {
                ITServiceDesk.findTicket(ticketID).setModifyingTechnician(this.getUsername());
                removeTicketfromList(ticketID);
            }
            return successMsg + newSeverity;
        }
    }

    public String removeTicketfromList(String ticketID){

        String successMsg = " removed from this technicians list.";
        String failMsg = "Ticket was not removed from the technician, as it could not be found or for another reason.";

        //Find the ticket and remove from list
        for(int i = 0;i < numberOfTicketsCurrentlyAssigned;i++){
            if(currentTicketList.get(i).equals(ticketID)){
                currentTicketList.remove(i);
                modifyTicketCount(-1);
                return ticketID + successMsg;
            }
        }
        //if it cannot be found, send a failure message.
        return failMsg;
    }

    @Override
    public String closeTicketWithoutResolution(Ticket ticket) {
        // TODO closeTicketWithoutResolution needs testing.
        String successMsg = " was successfully closed without a resolution.";
        //String failMsg = "Something went wrong while trying to close the ticket.";
            ticket.setStatus(TicketStatus.ClosedUnresolved);
            return ticket.getId() + successMsg;
    }

    @Override
    public String closeAndResolveTicket(Ticket ticket) {
        // TODO closeAndResolveTicket needs testing.
        String successMsg = " was successfully closed without a resolution.";
        ticket.setStatus(TicketStatus.ClosedResolved);
        return ticket.getId() + successMsg;
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
    public ArrayList<String> getCurrentTicketList() {
        return currentTicketList;
    }

    public void displayCurrentTickets(){
        if(this.getCurrentTicketList().get(0) == null){
            System.out.println("No tickets currently assigned.");
        }
        else{
            System.out.println("--Current Tickets--");
            for(int i=0;i<numberOfTicketsCurrentlyAssigned;i++){
                System.out.println("Ticket Id: " + ITServiceDesk.findTicket(currentTicketList.get(i)).TicketID);//change to ITServiceDesk.findTicket(ticketID)
                System.out.println("Ticket Author: " + ITServiceDesk.findTicket(currentTicketList.get(i)).TicketAuthor);
                System.out.println("Author Email: " + ITServiceDesk.findTicket(currentTicketList.get(i)).AuthorEmail);
                System.out.println("Ticket Description: " + ITServiceDesk.findTicket(currentTicketList.get(i)).Description);
                System.out.println("Ticket Severity: " + ITServiceDesk.findTicket(currentTicketList.get(i)).Severity.toString());
                System.out.println("Ticket Status: " + ITServiceDesk.findTicket(currentTicketList.get(i)).Status.toString());
                System.out.println("Modifying Technician: " + ITServiceDesk.findTicket(currentTicketList.get(i)).getModifyingTechnician() + "\n");
            }
        }
    }

    @Override
    public String addTicket(String ticketID) {
        String successMsg = " was successfully added.";
        String failMsg = "Ticket was unable to be added. Try again";

        if(ticketID != null){
            currentTicketList.add(ticketID);
            modifyTicketCount(1);
            return ticketID + successMsg;
        }
        else{
           return failMsg;
        }
    }

    @Override
    public String toString(){
        return this.firstName +" "+ this.lastName;
    }

    @Override
    public String modifyTicketCount(int modifier) {
        String oldCount = Integer.toString(numberOfTicketsCurrentlyAssigned);
        if(numberOfTicketsCurrentlyAssigned >= 0){
            numberOfTicketsCurrentlyAssigned += modifier;
            return "ticket count updated from" + oldCount + "to:" + numberOfTicketsCurrentlyAssigned;
        }
        else return "Ticket count cannot be made negative.";
    }

}