import qirui.parking.*;


public class Starter {
	
	public static void main(String[] args) {

		VehicleSize[] types = {VehicleSize.COMPACT, VehicleSize.LARGE, VehicleSize.REGULAR};
        int[][] tester = {{4,3},{6,7},{3,2}};
        ParkingLot p = new ParkingLot(tester, types, 40);

        Vehicle car1 = new CompactCar("pso898");

        Ticket ticket = p.park(car1);

        // try {
        //     Thread.sleep(1000 * 62);
        // } catch(InterruptedException e) {
        //   System.out.println(p.checkBalance(ticket));
		// }
		System.out.println(ticket.toString());
		System.out.println(p.leave(ticket));
		System.out.println(p.checkBalance(ticket));
		try {
			p.pay(90, ticket);
		} catch(InsufficientFundsException e) {
			System.out.println(e.getAmount());
			System.out.println(e.getMessage());
		}
		
	}

}