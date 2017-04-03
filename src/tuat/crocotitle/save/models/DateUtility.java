package tuat.crocotitle.save.models;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Author	: Le Vinh - Vietnam
 * Time	 	: 5/7/2013
 * Name		: DataUtilis Class
 * Function	: Get date and time from system
 * Others	:  
 */
@SuppressLint("SimpleDateFormat")
public class DateUtility {
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	public static String now(String dateFormat)
	{
		Calendar 		 cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}
}
