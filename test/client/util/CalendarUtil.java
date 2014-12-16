package client.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class CalendarUtil {


  public  Date february(int year, int day) {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.DAY_OF_MONTH, day);

    return calendar.getTime();
  }

}
