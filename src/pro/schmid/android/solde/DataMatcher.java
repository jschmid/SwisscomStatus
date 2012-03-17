package pro.schmid.android.solde;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMatcher extends ContentMatcher {
	private static Pattern pattern = Pattern
			.compile("[^\\d]*(?:(\\d+(?:\\.\\d+)?) ?MB)?[^\\d]*(\\d+(?:\\.\\d+)?) ?MB[^\\d]*(\\d{2}\\.\\d{2}\\.\\d{4})[^\\d]*(\\d{2}\\.\\d{2}\\.\\d{4})");

	public DataMatcher(String text) {
		Matcher matcher = pattern.matcher(text);

		if (matcher.find() && matcher.groupCount() == 4) {
			setValid(true);

			String grp1 = matcher.group(1);
			String grp2 = matcher.group(2);
			String grp3 = matcher.group(3);
			String grp4 = matcher.group(4);

			if (grp1 != null && grp1.length() > 0) {
				setTotalCredits(Integer.valueOf(grp1));
			}

			if (grp2 != null && grp2.length() > 0) {
				setRemainingCredits(Double.valueOf(grp2).intValue());
			}

			if (grp3 != null && grp3.length() > 0) {
				setValidUntil(DateParser.parse(grp3));
			}

			if (grp4 != null && grp4.length() > 0) {
				setState(DateParser.parse(grp4));
			}
		}
	}
}
