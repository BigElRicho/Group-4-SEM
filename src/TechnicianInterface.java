public interface TechnicianInterface{

    public int technicianLevel = 0;

    public int getTechnicianLevel();

    public int getNumberOfTicketsCurrentlyAssigned();

    public Ticket[] getCurrentTicketList();

    public String addTicket(Ticket ticket);

    public String modifyTicketCount(int modifier);

    public String getFirstName();

    public String getLastName();

    public String getUsername();

    public String getPassword();

    public String changeTicketSeverity(Ticket ticket, TicketSeverity newSeverity);

    public String closeTicketWithoutResolution(Ticket ticket);

    public String closeAndResolveTicket(Ticket ticket);

    // public String archiveTicket(Ticket ticket);

    public String changeUsername(String newUsername);

    public String changePassword(String newPassword);
}