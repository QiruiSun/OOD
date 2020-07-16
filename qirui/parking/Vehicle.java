package qirui.parking;

public abstract class Vehicle {
	private String plate;

	public Vehicle(String plate) {
		this.plate = plate;
	}

	public String getPlate() {
		return plate;
	}
	public abstract VehicleSize getSize();
}





// Vehicle 