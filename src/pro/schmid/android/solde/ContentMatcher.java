package pro.schmid.android.solde;

import java.util.Calendar;

public abstract class ContentMatcher {

	public abstract boolean isValid();

	public abstract int getTotalCredits();

	public abstract int getRemainingCredits();

	public abstract Calendar getValidUntil();

	public abstract Calendar getStateDate();
	
	public int getRemainingPerDay() {
		
		long milliseconds2 = getValidUntil().getTimeInMillis();
		long milliseconds1 = getStateDate().getTimeInMillis();
		
		long diff = milliseconds2 - milliseconds1;
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		
		return getRemainingCredits() / diffDays;
	}

}