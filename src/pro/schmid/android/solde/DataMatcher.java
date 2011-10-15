package pro.schmid.android.solde;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMatcher extends ContentMatcher {

	// NATEL easy Datenpaket : Verbleibendes Datenvolumen 87 MB, gültig bis 08.11.2011 (Stand vom 12.10.2011). Swisscom
	// Unités utilisées "NATEL xtra liberty medio": crédit mensuel: 500 MB, crédit restant: 350.99 MB, crédit valable jusqu'au: 01.11.2011, état au 03.10.2011
	private static Pattern pattern = Pattern
			.compile("[^\\d]*(?:(\\d+(?:\\.\\d+)?) ?MB)?[^\\d]*(\\d+(?:\\.\\d+)?) ?MB[^\\d]*(\\d{2}\\.\\d{2}\\.\\d{4})[^\\d]*(\\d{2}\\.\\d{2}\\.\\d{4})");

	private boolean valid;
	private int totalCredits;
	private int remainingCredits;
	private Calendar validUntil;
	private Calendar state;

	public DataMatcher(String text) {
		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			if (matcher.groupCount() == 4) {
				valid = true;

				totalCredits = Integer.valueOf(matcher.group(1));
				remainingCredits = Double.valueOf(matcher.group(2)).intValue();
				validUntil = DateParser.parse(matcher.group(3));
				state = DateParser.parse(matcher.group(4));
			} else if (matcher.groupCount() == 3) {
				valid = true;

				remainingCredits = Double.valueOf(matcher.group(1)).intValue();
				validUntil = DateParser.parse(matcher.group(2));
				state = DateParser.parse(matcher.group(3));
			}
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
