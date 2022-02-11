package src;
import java.util.Calendar;

public class Time implements Comparable<Time>{

    private int hour;
    private int minute;
    Calendar calendar = Calendar.getInstance();


    public Time()
    {
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    public Time(int h, int m)
    {
        hour = h;
        minute = m;
    }

    public Time(String t) {
    	String[] timeElements = t.split(":");
        hour = Integer.parseInt(timeElements[0]);
        minute = Integer.parseInt(timeElements[1]);
    }

    public Time(Time t)
    {
        minute = t.getMinute();
        hour = t.getHour();
    }

    public int getMinute()
    {
        int m = minute;
        return m;
    }

    public int getHour()
    {
        int h = hour;
        return h;
    }

    public void setHour(int h)
    {
        hour = h;
    }

    public void setMinute(int m)
    {
        minute = m;
    }

    public boolean isValid()
    {
        final int TOTAL_HOURS = 24;
        final int TOTAL_MINUTES = 60;

        if(hour >= TOTAL_HOURS)
        {
            return false;
        }else if(minute >= TOTAL_MINUTES)
        {
            return false;
        }
        return true;
    }

    public boolean equals(Time t)
    {
    	return compareTo(t) == 0;
    }
    
    public int compareTo(Time time)
    {
    	if(hour > time.getHour()) return 1;
    	else if(hour < time.getHour()) return -1;
    	if(minute > time.getMinute()) return 1;
    	else if(minute < time.getMinute()) return -1;
    	return 0;
    }

    public String toString()
    {
        String h = "0", m = "0";
        if(hour < 10)
        {
            h += hour;
        }else
        {
            h = ""+hour;
        }
        if(minute < 10) {
            m += minute;
        }else
        {
            m = ""+minute;
        }
        return h+":"+m;
    }
}
