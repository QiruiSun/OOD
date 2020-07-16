package qirui.parking;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
	private int amount;

	public InsufficientFundsException(int amount) {
		super("Insufficient funds for payment. You are " + amount + " dollars short.");
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	 }
}