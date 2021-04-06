
public enum TicketSeverity
{
    Low("Low"),
    Medium("Medium"),
    High("High");

    private String Name;

    TicketSeverity(String name)
    {
        Name = name;
    }

    @Override 
    public String toString()
    {
        return Name;
    }
}