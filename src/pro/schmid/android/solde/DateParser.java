package pro.schmid.android.solde;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {
	
	private static Pattern parser = Pattern.compile("(\\d{2})\\.(\\d{2})\\.(\\d{4})");

	public static Calendar parse(String s) {
		Matcher m = parser.matcher(s);
		
		if(!m.find() || m.groupCount() != 3) {
			return null;
		}
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(m.group(1)));
		c.set(Calendar.MONTH, Integer.parseInt(m.group(2)) - 1);
		c.set(Calendar.YEAR, Integer.parseInt(m.group(3)));

		return c; 
	}
}
