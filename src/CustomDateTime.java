import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CustomDateTime
{
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private LocalDateTime date;

    public CustomDateTime()
    {
        date = LocalDateTime.now();
    } 

    public CustomDateTime(String dateAsString)
    {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        setDate(LocalDate.parse(dateAsString, dateFormatter).atTime(LocalTime.parse("00:00:00")));
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
        return this.date;
    }

    public void setDate(LocalDateTime date) 
    {
        this.date = date;
    }

    //Compares if this date is after the date supplied
    public boolean After(CustomDateTime otherDate)
    {
        return getDate().isAfter(otherDate.getDate());
    }

    //Compares if this date is before the date supplied
    public boolean Before(CustomDateTime otherDate)
    {
        return getDate().isBefore(otherDate.getDate());
    }

    /* Override */

    @Override
    public String toString()
    {
        return dateTimeFormatter.format(date);
    }
}
