package com.example.testandroid1;


import java.util.ArrayList;

public class Task
{
    protected String title;
    protected String desc;

    protected Interval time;

    protected int priority; //1-3
    protected Interval prefferedTime;

    public String GetTitle() {
        return title;
    };
    public Interval GetTime() {
        return time;
    };

    public void SetNewTime(Interval time) {
        this.time = time;
    };
    //TODO other setters and getters...

    public Interval Intersect(Task other){
    return time.Intersect(other.time);
}

    public ArrayList<Interval> UsedTime(Interval period){
    Interval i = time.Intersect(period);
    ArrayList<Interval> R = new ArrayList<Interval>();
    if(i!=null){
        i.SetRefferedTask(this);
        R.add(i);
    }
    return R;
}


    public Task(){
        this.title = "New Task";
        this.desc = "Generic Task Generated By An Empty Constructor";
        this.time = new Interval();
        this.priority = 1;
    }

    public Task(String title, String desc, Interval time, int priority){
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.priority = priority;
    }

    public String ToString()
{
    return title + " " + desc;
}
}

class Routine extends Task
        {
protected boolean[] repeatDays;

public Routine(){
        this.title = "New Routine";
        this.desc = "Generic Routine Generated By An Empty Constructor";
        this.time = new Interval();
        repeatDays = new boolean[7];
        }

public Routine(String title, String desc, Interval time, boolean[] repeatDays){
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.repeatDays = repeatDays;
        }

        //TODO IMPLEMENT MORE...
        }

class AdvancedRoutine extends Routine{
protected int repeatWeeks; //Every 2nd, every 3rd week etc.
protected int weekOffset; // between 0 and (repeatWeeks-1)

public AdvancedRoutine(){
        this.title = "New AdvancedRoutine";
        this.desc = "Generic AdvancedRoutine Generated By An Empty Constructor";
        this.time = new Interval();
        this.repeatDays = new boolean[7];
        this.repeatWeeks = 2;
        this.weekOffset = 0;
        }

public AdvancedRoutine(String title, String desc, Interval time, boolean[] repeatDays, int repeatWeeks, int weekOffset){
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.repeatDays = repeatDays;
        this.repeatWeeks = repeatWeeks;
        this.weekOffset = weekOffset;
        }

        //TODO IMPLEMENT MORE...
        }
