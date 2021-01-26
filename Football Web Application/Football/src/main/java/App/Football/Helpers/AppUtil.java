package App.Football.Helpers;


import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class AppUtil {

    public static final String PathUpload = "http://localhost:8090/uploads/team/";

    public static Date convertStringToDate(String dateString, String format)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static Date convertStringToDate(String dateString)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return date;
    }

    public static String YearString(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.YEAR);
        return Integer.toString(calendar.get(Calendar.YEAR));
    }

    public static Date convertToDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(date);
        Date newDate = null;
        try
        {
            newDate = simpleDateFormat.parse(dateString);
        }
        catch (ParseException e)
        {
            System.out.println(e.getMessage());
        }
        return newDate;
    }

    public static String convertToTime(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static boolean validateEmail(String email) {
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
