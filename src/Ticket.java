
public class Ticket 
{
    String TicketAuthor; //The staff member who created the ticket, this should maybe be the email as it is unique?
    String Description;
    TicketSeverity Severity; //Enum as it can only be a select few values
    TicketStatus Status = TicketStatus.Open; //Enum as it can only be a select few values
    Boolean Archived = false;
    CustomDateTime OpenDate;
    CustomDateTime ClosedDate = null;

    /* Constructors */

    public Ticket(String auth, String desc) 
    {
        TicketAuthor = auth;
        Description = desc;
        setSeverity(TicketSeverity.Low);
        setOpenDate();
    }

    public Ticket(String auth, String desc, TicketSeverity severity)
    {
        TicketAuthor = auth;
        Description = desc;
        setSeverity(severity);
        setOpenDate();
    }

    /* Getters and Setters */

    //Only need getter, should not be changed after initialized
    public String getTicketAuthor() 
    {
        return TicketAuthor;
    }

    //Only need getter, should not be changed after initialized
    public String getDescription() 
    {
        return Description;
    }

    public TicketSeverity getSeverity() 
    {
        return Severity;
    }

    public void setSeverity(TicketSeverity severity) 
    {
        //Must be open before proceeding
        if(Status.equals(TicketStatus.Open))
        {
            Severity = severity;
        }
        else
        {
            System.out.println("Ticket is (%s). Reopen ticket first.");
        }
        
    }

    public TicketStatus getStatus() 
    {
        return Status;
    }

    public void setStatus(TicketStatus status) 
    {
        //Check if it is archived
        if(!getArchived())
        {
            //If the ticket is being repoened, remove the previous close date
            if(status == TicketStatus.Open)
            {
                ClosedDate = null;
            }

            Status = status;
        }

        System.out.println("Ticket has been closed for more than 24 hours and cannont be opened.");
    }

    public Boolean getArchived() 
    {  
        //This will check to see if the ticket is currently older than 24 hours first
        //If so, it will set it to archived. The ticket must have be closed (have a closed date) 
        if(ClosedDate != null & ClosedDate.GreaterThanTwentyFourHours())
        {
            setArchived(true);
        }   

        return Archived;
    }

    //This is should only be called internally
    private void setArchived(Boolean archived) 
    {
        Archived = archived;
    }

    public CustomDateTime getOpenDate() 
    {
        return OpenDate;
    }

    public void setOpenDate() 
    {
        OpenDate = new CustomDateTime();
    }

    public CustomDateTime getClosedDate() 
    {
        //Check if null first
        if(getStatus() != TicketStatus.Open)
        {
            return ClosedDate;
        }

        //System.out.println(String.format("Ticket is still open (%s)", getOpenDate()));
        return null;
        
    }

    public void setClosedDate() 
    {
        //No need to provide date, just set it to now by initializing the object
        if(Status == TicketStatus.Open)
        {
            ClosedDate = new CustomDateTime();
        }
        
        System.out.println(String.format("Ticket is already closed (%s).", getClosedDate()));
    }

    /* Override methods */
    @Override
    public String toString()
    {
        //If closed date is null, will print "N/A" for "Date Closed" field
        return String.format("Author: %s\nDate Opened: %s\nDate Closed: %s\nStatus: %s\nSeverity: %s\nDescription: %s", 
            getTicketAuthor(), getOpenDate(), getClosedDate() != null ? "N//A" : getClosedDate() , getStatus(), getSeverity(), getDescription());
    }

}
