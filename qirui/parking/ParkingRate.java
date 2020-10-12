package qirui.parking;
import java.util.*;

public class ParkingRate {
    private final static int GENERAL_RATE = 1500; // in cents
    private final Map<VehicleSize, Integer> rates;

    public ParkingRate(List<VehicleSize> sizes, List<Integer> prices) {
        this.rates = new HashMap<>();
        for (int i = 0; i < sizes.size(); i++) {
            rates.put(sizes.get(i), prices.get(i));
        }
    }

    public int getRate(VehicleSize size) {
        if (rates.containsKey(size)) {
            return rates.get(size);
        }
        return GENERAL_RATE;
    }
}
