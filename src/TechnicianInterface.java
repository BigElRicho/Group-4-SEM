public interface TechnicianInterface{

    public int technicianLevel = 0;

    public int getTechnicianLevel();

    public int getNumberOfTicketsCurrentlyAssigned();

    public Ticket[] getCurrentTicketList();

    public String getFirstName();

    public String getLastName();

    public String getUsername();

    public String getPassword();

    public String escalateTicket(Ticket ticket);

    public String deescalateTicket(Ticket ticket);

    public String closeTicketWithoutResolution(Ticket ticket);

    public String closeAndResolveTicket(Ticket ticket);

    public String changeUsername(String newUsername);

    public String changePassword(String newPassword);
}