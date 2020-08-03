import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	Calendar c=Calendar.getInstance();
	    c.set(2017, 8, 6);
	    System.out.println(c.get(Calendar.MONTH));
	    //设置时区 GMT为格林时间
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
	    Calendar cal = Calendar.getInstance();
	    Date date = cal.getTime();
	    SimpleDateFormat sdf0=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    System.out.println(sdf0.format(date));
	    
	    Date d=new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    System.out.println(sdf.format(d));
	    try {
	    	//字符串转日期
			Date newD= sdf.parse("2017-08-06 17:59:59");
			SimpleDateFormat sdf_day=new SimpleDateFormat("yyyy-MM-dd");
			//日期转字符串
		    String dateStr=sdf_day.format(newD);
			System.out.println(dateStr);
			Date dddd=new Date();
			System.out.println(sdf_day.format(dddd));
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	   

	}

}
