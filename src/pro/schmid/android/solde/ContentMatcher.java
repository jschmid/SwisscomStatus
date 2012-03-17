package pro.schmid.android.solde;

import java.util.Calendar;

public abstract class ContentMatcher {

	private boolean valid;
	private int totalCredits;
	private int remainingCredits;
	private Calendar validUntil;
	private Calendar state;

	public boolean isValid() {
		return valid;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public int getRemainingCredits() {
		return remainingCredits;
	}

	public Calendar getValidUntil() {
		return validUntil;
	}

	public Calendar getStateDate() {
		return state;
	}

	protected void setValid(boolean valid) {
		this.valid = valid;
	}

	protected void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	protected void setRemainingCredits(int remainingCredits) {
		this.remainingCredits = remainingCredits;
	}

	protected void setValidUntil(Calendar validUntil) {
		this.validUntil = validUntil;
	}

	protected void setState(Calendar state) {
		this.state = state;
	}

	public int getRemainingPerDay() {

		long milliseconds2 = getValidUntil().getTimeInMillis();
		long milliseconds1 = getStateDate().getTimeInMillis();

		long diff = milliseconds2 - milliseconds1;
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));

		return getRemainingCredits() / diffDays;
	}

}