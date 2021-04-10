
public enum TicketStatus
{
    Open("Open"),
    ClosedResolved("Closed & Resolved"),
    ClosedUnresolved("Closed & Unresolved");

    private String Name;

    TicketStatus(String name)
    {
        Name = name;
    }

    @Override 
    public String toString()
    {
        return Name;
    }
}