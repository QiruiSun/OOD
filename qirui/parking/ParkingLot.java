package qirui.parking;

import java.time.*;

public class ParkingLot {
	private final Level[] levels;
	private int hourlyRate; 

	/*
	levels - int[][] - each level has how many rows and cols, e.g.  [ [3, 4], [7, 8] ] - two levels while the first level has 3 rows 4 cols, and second has 7 rows and 8 cols
	types - VehicleSize[] - type for each level
	*/
	public ParkingLot(int[][] levelConfig, VehicleSize[] type, int rate) {
		this.levels = new Level[levelConfig.length];
		for ( int i=0; i <levelConfig.length; i++) {
			int rows = levelConfig[i][0];
			int cols = levelConfig[i][1];
			this.levels[i] = new Level(rows, cols, type[i]);
		}
		this.hourlyRate = rate;
	}

	public int getHourlyRate(){
		return hourlyRate;
	}

	public int checkBalance(Ticket ticket) {
		// return ticket.duration() * hourlyRate;
		return 1000;
	}

	public int pay(int amount, Ticket ticket) throws InsufficientFundsException {
		int toBePaid = checkBalance(ticket);
		if (amount < toBePaid) {
			throw new InsufficientFundsException(toBePaid - amount);
		} 
		ticket.pay();
		return amount - toBePaid;
	}

	// should we return a ticket here?
	public boolean leave(Ticket ticket) {
		if (!ticket.hasPaid()) {
			return false;
		}
		ParkingSpot spot = ticket.getSpot();
		if (spot != null) {
			spot.checkOut();
			return true;
		}
		
		return false;
	}

	public Ticket park(Vehicle car) {
		ParkingSpot curr = null;
		Level chosen = null;
		for (Level l : levels) {
			curr = l.findSpot(car, false);
			if (curr != null) {
				chosen = l;
				break;
			}
		}

		curr.checkIn(car);
		Ticket ticket = new Ticket(LocalDateTime.now(), chosen, curr);
		return ticket;
	}
}








/* 

Parking Lot
	- check rate
	- check balance input: ticket/receipt ?
	- pay ? input: ticket, money, output: changes, receipt
	- leave input: receipt, output: boolean 

Vehicle

Parking Spot 
	- checkIn (Vehicle) -> ticket 

Level

Ticket

Receipt
	- buffer
	- paid time
	- amount paid
	- 
*/