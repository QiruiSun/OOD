package qirui.parking;

class ParkingSpot {
	private Vehicle current;
	private VehicleSize size;
	private int row;
	private int col;

	public ParkingSpot(VehicleSize size, int row, int col) {
		this.size = size;
		this.row = row;
		this.col = col;
	}

	public ParkingSpot checkIn(Vehicle car) {
		this.current = car;
		return this;
	}

	public Vehicle checkOut() {
		Vehicle temp = current;
		current = null;
		return temp;
	}

	public boolean canPark(Vehicle car) {
		if (current != null || car.getSize().getValue() > size.getValue()) {
			return false;
		}
		return true;
	}
}




/*

Parking Spot 
	- checkIn (Vehicle) -> ticket 

should ticket contains car info?





*/