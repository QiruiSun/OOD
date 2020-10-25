package qirui;
import java.util.*;

public interface Elevator {
    void takeCommand(Request request);
    int stop () throws OutofSpaceException;
    void move();
    int getCurrentFloor();
    boolean isFull();
    boolean isStopped();
    boolean hasRequests();
}

enum Direction {
    UP,
    DOWN
}

abstract class Request {
    int destination;
    protected int number;

    public Request(int floor, int number) {
        this.destination = floor;
        this.number = number;
    }

    abstract public int fulfill();


}

class OnAboard extends Request {

    public OnAboard(int floor, int number) {
        super(floor, number);
    }
    @Override
    public int fulfill() {
        return this.number;
    }
}

class GetOff extends Request {

    public GetOff(int floor, int number) {
        super(floor, number);
    }
    @Override
    public int fulfill() {
        return -this.number;
    }
}

class OutofSpaceException extends Exception {
    public OutofSpaceException(int number, int capacity) {
        super("Out of space! Current capacity: " + capacity + "," + " Current number of people: " + number);
    }
}

class SimpleElevator implements Elevator {
    Map<Integer, Floor> map;
    Floor current;
    Direction currentDirection;
    private final int capacity;
    private int numberOfPpl;
    Map<Floor, List<Request>> counter; // records all requests for a given floor number
    PriorityQueue<Floor> goingUp;
    PriorityQueue<Floor> goingDown;
    private boolean forceStopped;

    public SimpleElevator(Map<Integer, Floor> map, int capacity) {
        this.map = map;
        this.current = map.get(0);
        this.currentDirection = Direction.UP;
        this.counter = new HashMap<>();
        this.capacity = capacity;
        this.goingUp = new PriorityQueue<>();
        this.goingDown = new PriorityQueue<>(Collections.reverseOrder());
        this.forceStopped = false;
    }

    public boolean hasRequests() {
        if (forceStopped) {
            return false;
        }
        return goingUp.size() > 0 || goingDown.size() > 0;
    }

    @Override
    public void takeCommand(Request request) {
        if (!map.containsKey(request.destination)) {
            throw new IllegalArgumentException("Invalid floor number");
        }
        Floor goTo = map.get(request.destination);
        if (!counter.containsKey(goTo)) {
            counter.put(goTo, new ArrayList<>());
        }

        if (request.destination == current.number) {
            return;
        }

        if (counter.get(goTo).size() > 0) {
            counter.get(goTo).add(request);
            return;
        }

        if (request.destination > current.number) {
            goingUp.offer(goTo);
        } else {
            goingDown.offer(goTo);
        }
        counter.get(goTo).add(request);
    }

    @Override
    public int stop() throws OutofSpaceException {
        if (!counter.containsKey(current) || forceStopped) {
            return 0;
        }
        int pplGetOff = 0;
        for (Request req : counter.get(current)) {
            numberOfPpl += req.fulfill();
            if (req instanceof GetOff) {
                pplGetOff -= req.fulfill();
            }
        }
        if (numberOfPpl >= this.capacity) {
            this.forceStopped = true;
            throw new OutofSpaceException(numberOfPpl, capacity);
        }
        counter.get(current).clear();
        numberOfPpl = numberOfPpl < 0 ? 0 : numberOfPpl;
        return pplGetOff;
    }

    public boolean isStopped() {
        return this.forceStopped;
    }

    @Override
    public void move() {
        if (this.forceStopped) {
            return;
        }
        if (currentDirection == Direction.UP && goingUp.size() == 0) {
            currentDirection = Direction.DOWN;
        } else if (currentDirection == Direction.DOWN && goingDown.size() == 0) {
            currentDirection = Direction.UP;
        } else if (goingDown.size() == 0 && goingUp.size() == 0) {
            currentDirection = current.number <= (map.size() + 1) / 2 ? Direction.UP : Direction.DOWN;
            return;
        }
        Floor toStop = null;
        if (currentDirection == Direction.UP) {
            toStop = goingUp.poll();
        } else {
            toStop = goingDown.poll();
        }
        this.current = toStop;
    }

    @Override
    public int getCurrentFloor() {
        return current.number;
    }

    @Override
    public boolean isFull() {
        return this.numberOfPpl == this.capacity;
    }
}

class Floor implements Comparable<Floor> {
    int number;

    public Floor(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(Floor another) {
        return Integer.compare(number, another.number);
    }
}

class Building {
    Map<Integer, Floor> floorMap;
    List<Elevator> elevators;


    public Building(int numberOfFloors, int number) {
        this.floorMap = new HashMap<>();
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            this.floorMap.put(i, new Floor(i));
        }
        while (number > 0) {
            elevators.add(new SimpleElevator(floorMap, 30));
            number--;
        }
    }
}

class Tester {
    public static void main(String[] args) {
        Building building = new Building(10, 1);
        Elevator ele = building.elevators.get(0);

        ele.takeCommand(new OnAboard(4, 3));
        ele.takeCommand(new OnAboard(7, 10));
        ele.takeCommand(new GetOff(9, 6));
        ele.takeCommand(new OnAboard(3, 7));


        while (ele.hasRequests()) {
            ele.move();
            try {
                ele.stop();
            } catch (OutofSpaceException e) {
                System.out.println(e.getMessage());
            }
            if (ele.getCurrentFloor() > 6) {
                ele.takeCommand(new GetOff(2, 6));
            }
            if (ele.getCurrentFloor() > 8) {
                ele.takeCommand(new OnAboard(4, 3));
            }
            if (ele.getCurrentFloor() < 3) {
                ele.takeCommand(new OnAboard(8, 3));
            }
        }
        System.out.println(ele.isStopped());
    }
}
