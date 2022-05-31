package es.iesmz.dam.pro;

public class Activity {
    private int id;
    private String name;
    private int duration;
    private String schedule;
    private int turn;
    private int calories;
    private int capacity;
    private String difficulty;

    public Activity(int id, String name,int duration, String schedule, int turn, int calories, int capacity, String difficulty) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.schedule = schedule;
        this.turn = turn;
        this.calories = calories;
        this.capacity = capacity;
        this.difficulty = difficulty;
    }
    public Activity(String name,int duration, String schedule, int turn, int calories, int capacity, String difficulty) {
        this.name = name;
        this.duration = duration;
        this.schedule = schedule;
        this.turn = turn;
        this.calories = calories;
        this.capacity = capacity;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getTurn() {
        return turn;
    }

    public int getCalories() {
        return calories;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
