import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CustomDateTime 
{
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime date;

    public CustomDateTime()
    {
        date = LocalDateTime.now();
    } 
    
    public boolean GreaterThanTwentyFourHours()
    {
        Period d = Period.between(date.toLocalDate(), LocalDate.now());
        
        if(d.getDays() > 0)
        {
            return true;
        }

        return false;
    }

    /* Getter and Setters */

    public LocalDateTime getDate() 
    {
        return date;
    }

    public void setDate(LocalDateTime date) 
    {
        this.date = date;
    }

    /* Override */

    @Override
    public String toString()
    {
        return dateFormatter.format(date);
    }
}
