package pro.schmid.android.solde;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VoiceMatcher extends ContentMatcher {

	private static Pattern pattern = Pattern.compile(".* Voice: [^:]*: (\\d+) CHF, [^:]*: (\\d+) CHF, [^:]*: (\\d{2}\\.\\d{2}\\.\\d{4}), .* (\\d{2}\\.\\d{2}\\.\\d{4})");

	public VoiceMatcher(String text) {
		Matcher matcher = pattern.matcher(text);

		if (matcher.find() && matcher.groupCount() == 4) {
			setValid(true);

			setTotalCredits(Integer.valueOf(matcher.group(1)));
			setRemainingCredits(Integer.valueOf(matcher.group(2)));
			setValidUntil(DateParser.parse(matcher.group(3)));
			setState(DateParser.parse(matcher.group(4)));
		}
	}
}
