package pro.schmid.android.solde;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsMatcher extends ContentMatcher {
	
	private static Pattern pattern = Pattern.compile(".* SMS: [^:]*: (\\d+), [^:]*: (\\d+), [^:]*: (\\d{2}\\.\\d{2}\\.\\d{4}), .* (\\d{2}\\.\\d{2}\\.\\d{4})");
	
	private boolean valid;
	private int totalCredits;
	private int remainingCredits;
	private Calendar validUntil;
	private Calendar state;

	public SmsMatcher(String text) {
		Matcher matcher = pattern.matcher(text);

		if(matcher.find() && matcher.groupCount() == 4) {
			valid = true;

			totalCredits = Integer.valueOf(matcher.group(1));
			remainingCredits = Integer.valueOf(matcher.group(2));
			validUntil = DateParser.parse(matcher.group(3));
			state = DateParser.parse(matcher.group(4));
		}
	}
	
	@Override
	public boolean isValid() {
		return valid;
	}

	@Override
	public int getTotalCredits() {
		return totalCredits;
	}

	@Override
	public int getRemainingCredits() {
		return remainingCredits;
	}

	@Override
	public Calendar getValidUntil() {
		return validUntil;
	}

	@Override
	public Calendar getStateDate() {
		return state;
	}
}
