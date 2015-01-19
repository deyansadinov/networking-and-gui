package clientsever;


import java.util.Calendar;
import java.util.Date;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class CalendarUtil  {

  public  static Date january(int year, int day){
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.MONTH,0);
    calendar.set(Calendar.DAY_OF_MONTH,day);
    calendar.set(Calendar.YEAR,year);
    calendar.set(Calendar.HOUR,0);
    calendar.set(Calendar.SECOND,0);
    calendar.set(Calendar.MILLISECOND,0);

    return calendar.getTime();

  }
}
