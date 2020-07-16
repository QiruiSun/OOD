package qirui.parking;


import java.time.*;

public class Ticket {
	private LocalDateTime entryTime;
	private Level level;
	private ParkingSpot spot;
	private boolean paid;
	private LocalDateTime paidTime;

	// private ParkingLot lot;
	

	public Ticket(LocalDateTime time, Level level, ParkingSpot spot) {
		this.entryTime = time;
		this.level = level;
		this.spot = spot;
		// this.lot = lot;
	}

	public int duration() {
		long mins = Duration.between(entryTime, LocalDateTime.now()).toMinutes();
		int hours = (int)Math.ceil(mins / (double) 60);
		return hours;
	}

	public boolean hasPaid() {
		return paid;
	}

	public void pay() {
		this.paid = true;
		this.paidTime = LocalDateTime.now();
	}

	public ParkingSpot getSpot() {
		return this.spot;
	}
}