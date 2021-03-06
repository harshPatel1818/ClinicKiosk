package src;
import java.util.Calendar;

/**
 * Contains information about a single date.
 * This includes the month, day of month, and year.
 * Can determine if the date is a valid calendar date.
 * @author Aaron Browne, Harshkumar Patel
 */
public class Date implements Comparable<Date>
{
	private int year;
	private int month;
	private int day;

	/**
	 * When given no arguments, the constructor creates an instance of date with the current date,
	 * as provided by Java's Calendar library.
	 */
	public Date() 
	{
		Calendar calendar = Calendar.getInstance();
		this.month  = calendar.get(Calendar.MONTH);
		this.year = calendar.get(Calendar.YEAR);
		this.day = calendar.get(Calendar.DATE);
	}

	/**
	 * When given a string, this constructor parses the string and creates an instance of date with it.
	 * @param date The date string in mm/dd/yyyy format.
	 */
    public Date(String date)
    {
    	String[] dateElements = date.split("/");
        month = Integer.parseInt(dateElements[0]);
        day = Integer.parseInt(dateElements[1]);
        year = Integer.parseInt(dateElements[2]);
    }

    /**
     * Creates a clone of the date provided.
     * @param d The date to clone.
     */
	public Date(Date d)
	{
		this.month = d.getMonth();
		this.day = d.getDay();
		this.year = d.getYear();
	}

	/**
	 * Creates an instance of Date with the month, day and year provided.
	 * @param m The month.
	 * @param d The day of the month.
	 * @param y The year.
	 */
	public Date(int m, int d, int y) 
	{
		this.month = m;
		this.day = d;
		this.year = y;
	}

	/**
	 * Returns the day of the month
	 * @return The day of the month
	 */
	public int getDay()
	{
		int d = this.day;
		return d;
	}

	/**
	 * Returns the month.
	 * @return The month.
	 */
	public int getMonth() 
	{
		int m = this.month;
		return m;
	}

	/**
	 * Returns the year.
	 * @return The year.
	 */
	public int getYear() 
	{
		int y = this.year;
		return y;
	}

	/**
	 * Changes the day of the month.
	 * @param d The new day of the month.
	 */
	public void setDay(int d) 
	{
		this.day = d;
	}

	/**
	 *  Changes the month.
	 * @param m The new month.
	 */
	public void setMonth(int m) 
	{
	    this.month = m;
	}

	/**
	 * Changes the year.
	 * @param y The new year.
	 */
	public void setYear(int y) 
	{
		this.year = y;
	}

	/**
	 * Determines whether or not the date is a valid calendar date.
	 * Does not allow years before 1900 or after the current one.
	 * Takes into account the amount of days of different months and leap years.
	 * @return true if the date is valid, false if not.
	 */
	public boolean isValid()
	{
		final int TOTAL_MONTH = 12;
		final int MIN_MONTH = 1;
		final int TOTAL_FEB_DAYS = 28;
		final int TOTAL_FEB_LEAP_DAYS = 29;
		final int TOTAL_AJSN_DAYS = 30; //AJSN = April, June, September, and November
		final int TOTAL_MONTH_DAYS = 31;
		boolean leapYear = isLeapYear(year);
		Calendar today = Calendar.getInstance();

		if(year < 1900 || year > today.get(Calendar.YEAR)) return false;

		if(month > TOTAL_MONTH || month < MIN_MONTH) return false;

		if(day < 1) return false;
		if(month == Month.FEB)
		{
			if(leapYear)
			{
				if(day > TOTAL_FEB_LEAP_DAYS) return false;
			} else
			{
				if(day > TOTAL_FEB_DAYS) return false;
			}
		} else if(month == Month.APR || month  == Month.JUN || month == Month.SEP || month == Month.NOV) //MONTHS WITH 30 DAYS
		{
			if(day > TOTAL_AJSN_DAYS) return false;
		} else  //MONTHS WITH 31 DAYS
		{
			if(this.day > TOTAL_MONTH_DAYS) return false;
		}

		return true;
	}

	/**
	 * Determines whether or not the given year is a leap year.
	 * @param y The year to analyze.
	 * @return true if the year is a leap year, false if not.
	 */
	private boolean isLeapYear(int y) 
	{
		final int QUADRENNIAL = 4;
		final int CENTENNIAL = 100;
	    final int QUATERCENTENNIAL = 400;
	    if(y % QUADRENNIAL == 0) 
	    {
	    	if(y % CENTENNIAL == 0) 
	    	{
	    		if(y % QUATERCENTENNIAL == 0) 
	    		{
	    			return true;
	    		}else 
	    		{
	    			return false;
	    		}
	    	}else 
	    	{
	    		return true;
	    	}
	    }else 
	    {
	    	return false;
	    }
	}

	/**
	 * Determine whether two dates are equal.
	 * @param date The date to compare against.
	 * @return true if the dates are equal, false if not.
	 */
	public boolean equals(Date date)
	{
		return compareTo(date) == 0;
	}

	/**
	 * Compares two dates.
	 * @param date The date to compare the current one to.
	 * @return 1 if the current date occurs after the given one, -1 if the current date occurs before, and 0 if the the dates are the same.
	 */
	@Override
	public int compareTo(Date date)
	{
		if(year > date.getYear()) return 1;
		else if(year < date.getYear()) return -1;
		if(month > date.getMonth()) return 1;
		else if(month < date.getMonth()) return -1;
		if(day > date.getDay()) return 1;
		else if(day < date.getDay()) return -1;
		return 0;
	}

	/**
	 * Converts the date into a string
	 * @return A string representation of the date in mm/dd/yyyy format.
	 */
	public String toString()
	{
		return month + "/" + day + "/" + year;
	}

	/**
	 * Tries a series of tests to make sure if isValid() function works.
	 * All the tests should return false except for the last one.
	 * @param args
	 */
	public static void main(String[] args)
	{
		Date d;

		//test case 1
		d = new Date("5/22/1883");
		System.out.println(d.isValid());

		//test case 2
		d = new Date("16/3/1984");
		System.out.println(d.isValid());

		//test case 3
		d = new Date("0/15/2002");
		System.out.println(d.isValid());

		//test case 4
		d = new Date("2/29/1993");
		System.out.println(d.isValid());

		//test case 5
		d = new Date("2/-1/1993");
		System.out.println(d.isValid());

		//test case 6
		d = new Date("2/31/1984");
		System.out.println(d.isValid());

		//test case 7
		d = new Date("6/31/2000");
		System.out.println(d.isValid());

		//test case 8
		d = new Date("6/0/2000");
		System.out.println(d.isValid());

		//test case 9
		d = new Date("12/500/1994");
		System.out.println(d.isValid());

		//test case 10
		d = new Date("12/0/1994");
		System.out.println(d.isValid());

		//test case 11
		d = new Date("3/3/2019");
		System.out.println(d.isValid());
	}
}
