package qirui.parking;

public enum VehicleSize {
	COMPACT(0),
	REGULAR(1),
	LARGE(2);

	private int value;

	private VehicleSize(int size) {
		this.value = size;
	}
	
	public int getValue() {
		return value;
	}
}