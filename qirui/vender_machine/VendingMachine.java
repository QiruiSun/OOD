package qirui.vender_machine;
import java.util.*;

interface PaymentService {
    boolean pay(int amount, int price);
    int refund();
    int returnChange();
    boolean hasChange();
}


enum ItemType {
    COKE,
    CHIPS,
    PROTEIN_BAR
}

class Item {
    ItemType type;
    String barCode;

    public Item(ItemType type, String barCode) {
        this.type = type;
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}

class Inventory {
    private final ItemType type;
    private final int capacity;
    private int amount;
    private final int price; // in cents
    private Item[] items;

    public Inventory(ItemType type, int capacity, int price) {
        this.type = type;
        this.capacity = capacity;
        this.items = new Item[capacity];
        this.price = price;

        fillItem();
    }

    public Item dispense() {
        if (amount > 0) {
            Item item = items[amount-1];
            items[amount-1] = null;
            amount--;
            return item;
        }
        return null; // can also raise exception here
    }

    public boolean canBuy() {
        return this.amount > 0;
    }

    public boolean isEnoughFund(int money) {
        return money >= this.price;
    }

    public int getPrice() {
        return this.price;
    }

    public void fillItem() {
        for (int i = amount; i < capacity; i++) {
            items[i] = new Item(type, generateBarCode());
        }
        this.amount = capacity;
    }

    private String generateBarCode() {
        byte[] bytes = new byte[8];
        new Random().nextBytes(bytes);
        return new String(bytes);
    }


}

enum PaymentType {
    CARD,
    CASH
}

class CashPayment implements PaymentService {
    int currentFund;
    int toPay;
    int total;

    @Override
    public boolean pay(int amount, int price) {
        this.currentFund = amount;
        this.toPay = price;
        return true;
    }

    @Override
    public int refund() {
        int refund = this.currentFund;
        reset();
        return refund;
    }

    @Override
    public int returnChange() {
        int change = this.currentFund - this.toPay;
        this.total += this.toPay;
        reset();
        return change;
    }

    public boolean hasChange() {
        return this.currentFund > this.toPay;
    }

    private void reset() {
        this.currentFund = 0;
        this.toPay = 0;
    }
}

public class VendingMachine {
    private final static int inventoryCapacity = 20;
    Map<ItemType, Inventory> listing;
    Map<PaymentType, PaymentService> paymentMethod;
    Inventory currentPick;
    PaymentService currentMethod;
    boolean paid;

    public VendingMachine(Map<ItemType, Integer> items) {
        paymentMethod = new HashMap<>(){{
            put(PaymentType.CASH, new CashPayment());
        }};
        listing = new HashMap<>();
        for (Map.Entry<ItemType, Integer> entry : items.entrySet()) {
            listing.put(entry.getKey(), new Inventory(entry.getKey(), inventoryCapacity, entry.getValue()));
        }
    }

    public Map<ItemType, Integer> getList() {
        Map<ItemType, Integer> map = new HashMap<>();
        for (Map.Entry<ItemType, Inventory> entry : listing.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getPrice());
        }
        return map;
    }

    public void pickItem(ItemType type) {
        Inventory inventory = listing.get(type);
        if (inventory == null) {
            throw new RuntimeException("No such item");
        }
        if (!inventory.canBuy()) {
            throw new RuntimeException("No item left");
        }
        this.currentPick = inventory;
    }

    public void cashPay(int amount) { // in cents
        PaymentService cash = paymentMethod.get(PaymentType.CASH);
        if (currentMethod != null && currentMethod != cash) {
            throw new RuntimeException("Payment method conflict");
        }
        this.currentMethod = cash;
        if (this.currentPick.isEnoughFund(amount)) {
            this.currentMethod.pay(amount, this.currentPick.getPrice()); // no exception
            this.paid = true;
        }
    }

    public void cardPay() {
        PaymentService card = paymentMethod.get(PaymentType.CARD);
        if (currentMethod != null && currentMethod != card) {
            throw new RuntimeException("Payment method conflict");
        }
        this.currentMethod = card;
        this.currentMethod.pay(this.currentPick.getPrice(), this.currentPick.getPrice()); // no exception
        this.paid = true;
    }

    public int refund() {
        return currentMethod.refund();
    }

    public boolean hasChange() {
        return currentMethod.hasChange();
    }

    public int giveChange() {
        if (hasChange()) {
            return currentMethod.returnChange();
        }
        return 0;
    }

    public Item dispense() {
        if (!this.paid) {
            return  null;
        }
        Item item = currentPick.dispense();
        reset();
        return item;
    }

    private void reset() {
        currentMethod = null;
        currentPick = null;
        paid = false;
    }
}



class MachineTester {
    public static void main(String[] args) {
        Map<ItemType, Integer> items = new HashMap<>() {{
           put(ItemType.COKE, 6000);
           put(ItemType.CHIPS, 7000);
           put(ItemType.PROTEIN_BAR, 100000);
        }};
        VendingMachine vm = new VendingMachine(items);

        vm.pickItem(ItemType.CHIPS);
        vm.cashPay(80000);
        if (vm.hasChange()) {
            System.out.println(vm.giveChange());
        }
        System.out.println(vm.dispense());
        System.out.println(vm.getList());
    }
}










