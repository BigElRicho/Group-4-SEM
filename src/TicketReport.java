import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TicketReport 
{
    private int Total;
    private int UnresolvedCount = 0;
    private int ResolvedCount = 0;
    private ArrayList<Ticket> TicketList;

    TicketReport(ArrayList<Ticket> tickets)
    {
        TicketList = tickets;
        Total = tickets.size();

        for(Ticket ticket : TicketList)
        {
            if(ticket.Status == TicketStatus.ClosedResolved)
            {
                ResolvedCount++;
            }
            else if(ticket.Status == TicketStatus.ClosedUnresolved || ticket.Status == TicketStatus.Open)
            {
                UnresolvedCount++;
            }
        }
    }

    //Used to calculate duration of a ticket 
    private long GetTicketDuration(Ticket ticket)
    {
        return ChronoUnit.DAYS.between(ticket.getOpenDate().getDate(), ticket.getClosedDate().getDate());
    }
    
    //Function to print the necessary fields for the resolved tickets
    private void GetResolvedTickets()
    {
        for(Ticket ticket : TicketList)
        {
            if(ticket.Status == TicketStatus.ClosedResolved)
            {
                System.out.println(String.format("Ticket ID: %s\nAuthor: %s\nOpen Date: %sAttending Technician: %s\nResolution Time (in days): %s\n", ticket.getId(), ticket.AuthorEmail, ticket.getOpenDate(), "Techn ID", GetTicketDuration(ticket)));
            }
        }
    }

    //Function to print the necessary fields for the Outstanding tickets
    private void GetOutstandingTickets()
    {
        for(Ticket ticket : TicketList)
        {
            if(ticket.Status == TicketStatus.ClosedUnresolved || ticket.Status == TicketStatus.Open)
            {
                System.out.println(String.format("Ticket ID: %s\nAuthor: %s\nOpen Date: %s\nSeverity: %s\n", ticket.getId(), ticket.AuthorEmail, ticket.getOpenDate(), ticket.getSeverity()));
            }
        }
    }

    public void PrintReport() 
    {
        System.out.println(String.format("The total amount of tickets submited for the period was: %d\n", this.Total));

        //Resolved tickets
        if(ResolvedCount > 0)
        {
            System.out.println(String.format("%d ticket(s) resolved during this period.\n", ResolvedCount));
            GetResolvedTickets();
        }
        else
        {
            System.out.println("There were no resolved cases during this period");
        }

        //Unresoled Or open tickets
        if(UnresolvedCount > 0)
        {
            System.out.println(String.format("%d ticket(s) are unresolved (either still open or closed but unresolved).\n", UnresolvedCount));
            GetOutstandingTickets();
        }
        else
        {
            System.out.println("There were no resolved cases during this period");
        }
       
    }


}
