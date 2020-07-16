package qirui.parking;

class Level {
	private final ParkingSpot[][] spots;
	private VehicleSize type;

	public Level(int rows, int cols, VehicleSize type) {
		this.type = type;
		spots = new ParkingSpot[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				spots[i][j] = new ParkingSpot(type, i, j);
			}	
		}
	}

	public ParkingSpot findSpot(Vehicle car, boolean anyCanFit) {
		int carSize = car.getSize().getValue();
		int mySize = type.getValue();

		if (anyCanFit) {
			if (carSize > mySize) {
				return null;
			}
		} else {
			if (carSize != mySize) {
				return null;
			}
		}
		return searchSpot(car);
	}

	private ParkingSpot searchSpot(Vehicle car) {
		for (int i = 0; i < spots.length; i++) {
			for (int j = 0; j < spots[0].length; j++) {
				if (spots[i][j].canPark(car)) {
					return spots[i][j];
				}
			}	
		}
		return null;
	}

}



/* 

Level
	- spots
	- hasSpot
	- 
*/