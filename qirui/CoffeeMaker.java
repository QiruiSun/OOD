package qirui;

import java.util.*;

public interface CoffeeMaker {
    Coffee brewCoffee(CoffeeSelection type) throws ExhaustedBrewUnitException;
}

enum CoffeeSelection {
    FILTERED,
    EXPRESSO;
}

class CoffeePack {
    private int capacity;
    private CoffeePack type;
    private int size;

    public CoffeePack(int capacity) {
        this.capacity = capacity;
        this.size = capacity;
    }

    public int refill(int number) {
        size += number;
        int extra = size % capacity;
        size -= extra;
        return extra;
    }
}

interface BrewUnit {
    Coffee brew(CoffeeSelection flavor) throws ExhaustedBrewUnitException;
}

class BasicBrewUnit implements BrewUnit {
    private int timesLeft;

    public BasicBrewUnit() {
        timesLeft = 10;
    }

    public Coffee brew(CoffeeSelection type) throws ExhaustedBrewUnitException {
        if (timesLeft > 0) {
            Coffee coffee = new Coffee(type);
            timesLeft--;
            return coffee;
        }
        throw new ExhaustedBrewUnitException();
    }
}

class ExpressoBrewUnit implements BrewUnit {

    @Override
    public Coffee brew(CoffeeSelection flavor) throws ExhaustedBrewUnitException {
        return new Espresso(flavor);
    }
}

class ExhaustedBrewUnitException extends Exception {
    public ExhaustedBrewUnitException() {
        super("Brew unit end of life, requires replacement");
    }
}

class Coffee {
    protected long brewTime;
    protected CoffeeSelection type;

    public Coffee(CoffeeSelection type) {
        this.type = type;
        brewTime = System.currentTimeMillis();
    }
}

class Espresso extends Coffee {

    public Espresso(CoffeeSelection type) {
        super(type);
    }
}

class BasicCoffeeMaker implements CoffeeMaker {
    private Map<CoffeeSelection, CoffeePack> options;
    private BrewUnit brewUnit;

    public BasicCoffeeMaker() {
        this.brewUnit = new BasicBrewUnit();
        this.options = new HashMap<>() {{
            put(CoffeeSelection.FILTERED, new CoffeePack(10));
        }};
    }

    @Override
    public Coffee brewCoffee(CoffeeSelection selection) throws ExhaustedBrewUnitException {
        return this.brewUnit.brew(selection);
//        try {
//
//        } catch ( e) {
//            System.out.println(e.getMessage());
//            return null;
//        }
    }
}

class AdvancedCoffeeMaker implements CoffeeMaker {
    private Map<CoffeeSelection, CoffeePack> options;
    private BrewUnit brewUnit;

    public AdvancedCoffeeMaker() {
        this.brewUnit = new BasicBrewUnit();
        this.options = new HashMap<>() {{
            put(CoffeeSelection.FILTERED, new CoffeePack(10));
        }};
    }

    @Override
    public Coffee brewCoffee(CoffeeSelection type) throws ExhaustedBrewUnitException {
        if (type == CoffeeSelection.EXPRESSO) {
            return new ExpressoBrewUnit().brew(type);
        }
        return this.brewUnit.brew(type);
    }
}


class CoffeeTester {
    public static void main(String[] args) {
        CoffeeMaker a = new AdvancedCoffeeMaker();
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";

        for (int i = 0; i < input.length(); i++) {
            char df = input.charAt(i);
            System.out.println(df);
        }
        String processed = input.replaceAll("\\n", "").replaceAll("\\t", "/");
        System.out.println(' '- 'a');
    }
}