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

    public String closeTicketWithoutResolution(Ticket ticket);

    public String closeAndResolveTicket(Ticket ticket);

    // public String archiveTicket(Ticket ticket);

    public String changeUsername(String newUsername);

    public String changePassword(String newPassword);

    public void displayCurrentTickets();
}