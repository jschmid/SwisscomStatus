package pro.schmid.android.solde;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMatcher extends ContentMatcher {
	private static Pattern pattern = Pattern
			.compile("[^\\d]*(?:(\\d+(?:\\.\\d+)?) ?MB)?[^\\d]*(\\d+(?:\\.\\d+)?) ?MB[^\\d]*(\\d{2}\\.\\d{2}\\.\\d{4})[^\\d]*(\\d{2}\\.\\d{2}\\.\\d{4})");

	private boolean valid;
	private int totalCredits;
	private int remainingCredits;
	private Calendar validUntil;
	private Calendar state;

	public DataMatcher(String text) {
		Matcher matcher = pattern.matcher(text);

		if (matcher.find() && matcher.groupCount() == 4) {
			valid = true;

			String grp1 = matcher.group(1);
			String grp2 = matcher.group(2);
			String grp3 = matcher.group(3);
			String grp4 = matcher.group(4);

			if (grp1 != null && grp1.length() > 0)
				totalCredits = Integer.valueOf(grp1);

			if (grp2 != null && grp2.length() > 0)
				remainingCredits = Double.valueOf(grp2).intValue();

			if (grp3 != null && grp3.length() > 0)
				validUntil = DateParser.parse(grp3);

			if (grp4 != null && grp4.length() > 0)
				state = DateParser.parse(grp4);
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
