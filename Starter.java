import qirui.parking.*;
import qirui.filesystem.*;
import java.util.*;

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
		// System.out.println(ticket.toString());
		// System.out.println(p.leave(ticket));
		// System.out.println(p.checkBalance(ticket));
		// try {
		// 	p.pay(90, ticket);
		// } catch(InsufficientFundsException e) {
		// 	System.out.println(e.getAmount());
		// 	System.out.println(e.getMessage());
		// }


		// FileSystem

		FileSystem f = new FileSystem();
		f.mkdir("/home");
		System.out.println(f.list());
		f.mkdir("qsun1");
		f.list();
		f.cd("qsun1");
		f.list();
		f.mkdir("node");
		f.list();
		f.cd();
		f.list();
		f.cd("/qsun1/node");
		f.list();
		System.out.println(f.currentPath());
		f.cd("/qsun1/node/sadf");
	}

}



// public int minCuts(String input) {
//     // Write your solution here
//     int size = input.length();
//     int[] M = new int[size+1];
//     boolean[][] dp = new boolean[size+1][size+1];
//     M[0] = -1;
//     M[1] = 0;

//     for (int i = 2; i <= size; i++) {
//       int min = i;
//       for (int j = i-1; j >= 0; j--) {
//         if ((input.charAt(i-1) == input.charAt(j) && (dp[i-1][j+1] || i == j + 2)) || j + 1 == i) {
//           dp[i][j] = true;
//           min = Math.min(min, M[j] + 1);
//         }
//       }
//       M[i] = min; 
//     }
//     return M[size];
//   }

//   public int minCuts(String input) {
//     // Write your solution here
//     if (input == null || input.length() == 0) {
//       return 0;
//     }
      
//     int size = input.length();
//     int[] M = new int[size+1];
//     M[0] = -1;
//     M[1] = 0;
    
//     boolean[][] isP = new boolean[size+1][size+1];
    
//     for (int i = 2; i < M.length; i++) {
//       M[i] = i;
//       for (int j = i - 1; j >=0; j--) {
//         int rightStart = j + 1;
//         if (rightStart == i || ((rightStart + 1 == i || isP[rightStart + 1][i-1]) && input.charAt(rightStart - 1) == input.charAt(i - 1))) {
//             isP[rightStart][i] = true;
//             M[i] = Math.min(M[i], M[j] + 1);
//         }
//       }
//     }
//     return M[size];
//   }