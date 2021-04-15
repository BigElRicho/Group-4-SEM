public class TechnicianLevelTwo implements TechnicianInterface{
    
    //Attributes
    private final int TICKET_QUOTA = 20;
    private Ticket currentTicketList[] = new Ticket[TICKET_QUOTA] ;
    private int numberOfTicketsCurrentlyAssigned = 0;
    private final int TECHNICIAN_LEVEL = 2;
    private String userName = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";

    //Constructors
    public TechnicianLevelTwo(String userName, String password, String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    //Methods
    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String changeTicketSeverity(Ticket ticket, TicketSeverity newSeverity) {
        // TODO Perform testing on this function.
        String successMsg = "Ticket successfully changed to: ";
        String failMsg = "Issue unable to be changed as it is already set to: ";
        
        //Check if severity is already set to the new severity level.
        if(ticket.getSeverity().equals(newSeverity)){
            return failMsg + newSeverity;
        }
        //...else change the severity
        else{
            ticket.setSeverity(newSeverity);
            //if it gets set to LOW or HIGH, then store in the Service desk tempTicket array.
            if(ticket.getSeverity().equals(TicketSeverity.Low) || ticket.getSeverity().equals(TicketSeverity.Medium)){
                ITServiceDesk.tempTickets[0] = ticket;
                //Show that its actually in the list.
                System.out.println(ITServiceDesk.tempTickets[0]);
                //Remove ticket from currentList.
                removeTicketfromList(ticket);
            }
            return successMsg + newSeverity;
        }
    }

    private String removeTicketfromList(Ticket ticket) {
        //TODO remove ticket function needs testing.
        String successMsg = " removed from this technicians list.";
        String failMsg = "Ticket was not removed from the technician, as it could not be found or for another reason.";

        //Find the ticket and remove from list
        for(int i = 0;i < numberOfTicketsCurrentlyAssigned;i++){
            if(currentTicketList[i].getId() == ticket.getId()){
                currentTicketList[i].equals(null);
                return ticket.getId() + successMsg;
            }
        }
        //if it cannot be found, send a failure message.
        return failMsg;
    }

    @Override
    public String addTicket(Ticket ticket) {
        //TODO addTicket function needs testing.
        String successMsg = " was successfully added.";
        String failMsg = "Ticket was unable to be added. Try again";

        if(ticket != null){
            currentTicketList[numberOfTicketsCurrentlyAssigned-1] = ticket;
            modifyTicketCount(1);
            return ticket.getId() + successMsg;
        }
        else{
           return failMsg;
        }
    }

    // TODO closeTicketWithoutResolution function.
    @Override
    public String closeTicketWithoutResolution(Ticket ticket) {
        String successMsg = " has been closed without a resolution.";
        String failMsg = "Something went wrong while closing the ticket. Try again";

        if(ticket.setStatus(TicketStatus.ClosedUnresolved)){
            return ticket.getId() + successMsg;
        }
        else{
            return failMsg;
        }        
    }

    @Override
    public String closeAndResolveTicket(Ticket ticket) {
        // TODO closeAndResolveTicket function
        return null;
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
    public String changePassword(String newPassword) {
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
    public int getTechnicianLevel() {
        return TECHNICIAN_LEVEL;
    }

    @Override
    public int getNumberOfTicketsCurrentlyAssigned() {
        return numberOfTicketsCurrentlyAssigned;
    }

    @Override
    public Ticket[] getCurrentTicketList() {
        return this.currentTicketList;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
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

    @Override
    public String archiveTicket(Ticket ticket) {
        // TODO Auto-generated method stub
        return null;
    }
}