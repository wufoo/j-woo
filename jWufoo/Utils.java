package jWufoo;

import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
	
	public static Boolean getBoolean(String booleanString) {
		return booleanString.contains("1");
	}
		
	public static Date getDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.US);
		try {
			Date date = sdf.parse(dateString);
			return date;
		}
		catch (ParseException error) {
			return null;
		}
	}
}
