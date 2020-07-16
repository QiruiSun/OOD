package qirui.parking;

public class CompactCar extends Vehicle {

	public CompactCar(String plate) {
		super(plate);
	}
	
	public VehicleSize getSize() {
		return VehicleSize.COMPACT;
	}
}