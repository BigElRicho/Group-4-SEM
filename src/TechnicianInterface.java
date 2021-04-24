import java.util.ArrayList;

public interface TechnicianInterface{

    public int technicianLevel = 0;

    public int getTechnicianLevel();

    public int getNumberOfTicketsCurrentlyAssigned();

    public ArrayList<String> getCurrentTicketList();

    public String addTicket(String ticketID);

    public String removeTicketfromList(String ticketID);

    public String modifyTicketCount(int modifier);

    public String getFirstName();

    public String getLastName();

    public String getUsername();

    public String getPassword();

    public String changeTicketSeverity(String ticketID, TicketSeverity newSeverity);

    public String closeTicketWithoutResolution(String ticketID);

    public String closeAndResolveTicket(String ticketID);

    public String reopenTicket(String ticketID);

    public String changeUsername(String newUsername);

    public String changePassword(String newPassword);

    public void displayCurrentTickets();

    public void displayClosedTickets();
}