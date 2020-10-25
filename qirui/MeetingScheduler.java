package qirui;
import javax.management.Notification;
import java.util.*;
//import org.json.*;

public interface MeetingScheduler {
    Meeting book(Calendar start, int duration, List<Person> people);

    List<Meeting> getHistory();

    boolean checkRoom(int roomNumber, Calendar start, int duration);
}

class TimeSlot {
    Calendar start;
    Calendar end;
}

class Person {
    String name;
    String email;
}

class MeetingRoom {
    private int id;
    private List<Meeting> history;
    private TreeSet<Meeting> bookings;

    public MeetingRoom(int id) {
        this.id = id;
        this.history = new ArrayList<>();
        this.bookings = new TreeSet<>();
    }

    public boolean isAvailable(Calendar start, int duration) {
        if (this.bookings.size() == 0 ) {
            return true;
        }
        Calendar end = (Calendar) start.getInstance();
        end.add(Calendar.MINUTE, duration);
        if (start.after(bookings.last().end)) {
            return true;
        } else if (end.before(bookings.first().start)) {
            return true;
        }
        return false;
    }

    public boolean takeMeeting(Meeting meeting) {
        if (!isAvailable(meeting.start, meeting.duration)) {
            return false;
        }
        this.bookings.add(meeting);
        return true;
    }

    public int getId() {
        return this.id;
    }
}

class NotificationService {

    public static void notifyMeeting(Person p, Meeting meeting) {
        String payLoad = "";
        sendEmail(p, payLoad);
    }

    private static void sendEmail(Person p, String payload) {
        System.out.println("Sending email to " + p.email + ", about: " + payload);
    }

}

class Meeting implements Comparable<Meeting> {
//    private TimeSlot timeSlot;
    private MeetingRoom room;
    private List<Person> attendees;
    Calendar start;
    Calendar end;
    int duration;
    State state;

    public Meeting(MeetingRoom room, List<Person> people, Calendar start, int duration) {
        Calendar current = Calendar.getInstance();
        if (current.after(start)) {
            throw new IllegalArgumentException("passed start time already!");
        }
        this.room = room;
        this.attendees = people;
        this.start = start;
        Calendar end = (Calendar) start.getInstance();
        end.add(Calendar.MINUTE, duration);
        this.end = end;
        this.state = State.ACTIVE;
        this.duration = duration;
        notifyAttendee();
    }

    private void notifyAttendee() {
        for (Person p : attendees) {
            NotificationService.notifyMeeting(p, this);
        }
    }

    @Override
    public int compareTo(Meeting another) {
        return another.start == start ? end.compareTo(another.end) : start.compareTo(another.start);
    }

    public int getRoomNumber() {
        return this.room.getId();
    }

    public void cancel() {
        this.state = State.CANCELLED;
    }

    public State getState() {
        if (state == State.CANCELLED) {
            return state;
        }
        Calendar current = Calendar.getInstance();
        if (current.after(end)) {
            state = State.COMPLETED;
        } else if (current.after(start) && current.before(end)) {
            state = State.ON_GOING;
        } else {
            state = State.ACTIVE;
        }
        return state;
    }

    enum State {
        ACTIVE,
        COMPLETED,
        CANCELLED,
        ON_GOING
    }
}


class BasicScheduler implements MeetingScheduler {
    private List<MeetingRoom> rooms;
    private List<Meeting> meetings;
    private Map<Integer, MeetingRoom> roomMap;


    public BasicScheduler(List<Integer> roomNumbers) {
        this.rooms = new ArrayList<>();
        this.roomMap = new HashMap<>();
        this.meetings = new ArrayList<>();
        for (int id : roomNumbers) {
            MeetingRoom room = new MeetingRoom(id);
            rooms.add(room);
            roomMap.put(room.getId(), room);
        }
    }

    @Override
    public Meeting book(Calendar start, int duration, List<Person> people) {
        Meeting newBooking = null;
        for (MeetingRoom room : rooms) {
            if (room.isAvailable(start, duration)) {
                newBooking = new Meeting(room, people, start, duration);
                room.takeMeeting(newBooking);
                this.meetings.add(newBooking);
                break;
            }
        }
        return newBooking;
    }

    @Override
    public List<Meeting> getHistory() {
        int count = 20;
        int start = this.meetings.size() - 1;
        List<Meeting> result = new ArrayList<>();
        while (start >= 0 && count > 0) {
            result.add(meetings.get(start--));
            count--;
        }
        return result;
    }

    @Override
    public boolean checkRoom(int roomNumber, Calendar start, int duration) {
        MeetingRoom room = roomMap.get(roomNumber);
        if (room == null) {
            return false;
        }
        return room.isAvailable(start, duration);
    }
}



class SchedulerTester {
    public static void main(String[] args) {
        BasicScheduler bs = new BasicScheduler(Arrays.asList(1, 2, 3, 4,7, 8));
        Calendar start = Calendar.getInstance();
        start.add(Calendar.MINUTE, 5);

        Meeting m  = bs.book(start, 40, Arrays.asList(new Person(), new Person(), new Person(), new Person()));

        System.out.println(m);

        System.out.println(bs.getHistory());

        boolean availability = bs.checkRoom(m.getRoomNumber(), start, 40);

        System.out.println(availability);

        start.add(Calendar.MINUTE, 50);

        availability = bs.checkRoom(m.getRoomNumber(), start, 40);

        System.out.println(availability);

    }
}


/*

n given meeting rooms

write an API for client who will give date and time and
API should return meeting room with booked scheduled time.

client should also query for history of last 20 booked meetings.

Is meeting room available? etc

Use case:

1. Book a meeting in any meeting room at given interval(starting time, end time).
2. Send notifications to all person who are invited for meeting.
3. Retrieve history of booked meeting

Note:

use Calendar for time


APIs:

1. book meeting
    input: time, date, persons
    output: meeting room with scheduled time.

2. getHistory
    input: none
    ouput: a list of meeting records with meeting room and time

3. checkRoom
    input: room number, time range
    output: a list of available slots within the time range?


Classes:

1. Scheduler
2. MeetingRoom
3. Meeting
4. TimeSlot (start, end)
5. NotificationService
6. Person





 */