package Amazon;
import java.util.*;

public class OA2 {
	
	public static long getSongPairCount(List<Integer> songs) {
		List<Integer> filtered = new ArrayList<>();

		for (int song : songs) {
			if (song > 60) {
				filtered.add(song % 60);
			} else {
				filtered.add(song);
			}
		}

		Map<Integer, Integer> memo = new HashMap<>();

		long res = 0;

		for (int i = 0; i < filtered.size(); i++) {
			int song = filtered.get(i);
			if (song == 60) {
				res += memo.getOrDefault(60, 0);
			} else {
				res += memo.getOrDefault(60 - song, 0);
			}
			memo.put(song, memo.getOrDefault(song, 0) + 1);
		}
		return res;
    }
}
