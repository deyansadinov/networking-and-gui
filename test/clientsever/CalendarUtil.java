package clientsever;


import java.util.Calendar;
import java.util.Date;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class CalendarUtil  {

  public  static Date january(int year, int day){
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR,year);
    calendar.set(Calendar.DAY_OF_MONTH,day);

    return calendar.getTime();

  }
}
