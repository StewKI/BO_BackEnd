package com.example.testandroid1;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime {

    //Minutes passed since January 1st 2020. at 00:00
    private int minutes; //Core data of class DateTime

    final int daysInYear = 365;
    final int hoursInDays = 24;
    final int minutesInHour = 60;
    final int minutesInDay = minutesInHour*hoursInDays;
    final int minutesInYear = minutesInDay*daysInYear;

    public int GetYear() { return CurrentYear() + 2020; }
    public int GetMonth() { return DaysToMonth(DaysInCurrentYear(), isLeapYear(GetYear())); }
    public int GetDay() { return DaysInCurrentYear() - MonthToDays(GetMonth(),isLeapYear(GetYear())); }
    public int GetHour(){ return MinutesInCurrentDay()/minutesInHour; }
    public int GetMinute(){ return MinutesInCurrentDay()%minutesInHour; }
    public int GetLongMinutes() { return minutes; }

    public boolean isLeapYear(int year){if(year%4 == 0) return true; else return false;}

    private int MinutesInCurrentDay(){
        return (minutes%minutesInDay);
    }

    private int MinutesInCurrentYear(){
        return minutes%minutesInYear;
    }

    private int DaysInCurrentYear(){
        return MinutesInCurrentYear() / minutesInDay + 1;
    }

    private int CurrentYear(){
        return minutes/minutesInYear;
    }

    private int MonthToDays(int month, boolean leapYear){
        int days = 0;
        switch (month){
            case 12: days+=30;
            case 11: days+=31;
            case 10: days+=30;
            case 9: days+=31;
            case 8: days+=31;
            case 7: days+=30;
            case 6: days+=31;
            case 5: days+=30;
            case 4: days+=31;
            case 3: days+=28;
            case 2: days+=31;
        }

        if(leapYear && month>2) days++;

        return days;
    }

    private int DaysToMonth(int days, boolean leapYear){
        int feb = (leapYear?1:0);
        if(days<=31) return 1;
        else if(days<=59+feb) return 2;
        else if(days<=90+feb) return 3;
        else if(days<=120+feb) return 4;
        else if(days<=151+feb) return 5;
        else if(days<=181+feb) return 6;
        else if(days<=212+feb) return 7;
        else if(days<=243+feb) return 8;
        else if(days<=273+feb) return 9;
        else if(days<=304+feb) return 10;
        else if(days<=334+feb) return 11;
        else if(days<=365+feb) return 12;
        else return DaysToMonth(days-365-feb, leapYear);
    }

    private int LeapDays(int year){
        return year/4+1;
    }

    public DateTime(int year, int month, int day, int hour, int minute){
        int min = 0;
        year-=2020;
        min += year*minutesInYear;
        //min += LeapDays(year) * minutesInDay;
        min += MonthToDays(month,isLeapYear(year+2020))*minutesInDay;
        min += (day-1) * minutesInDay;
        min += hour * minutesInHour;
        min += minute;

        this.minutes = min;
    }

    public DateTime(int longMinutes){
        this.minutes = longMinutes;
    }
    public DateTime() {minutes = 0;}

    public TimeSpan Difference(DateTime other){
        return new TimeSpan(Math.abs(minutes - other.minutes));
    }

    public boolean After(DateTime other){
        return minutes > other.minutes;
    }

    public boolean Before(DateTime other){
        return minutes < other.minutes;
    }

    public DateTime AddDur(TimeSpan dur){
        return new DateTime(minutes + dur.GetLongMinutes());
    }

    public static DateTime Now(){
        Date now = Calendar.getInstance().getTime();
        return new DateTime(now.getYear(),now.getMonth(),now.getDay(),now.getHours(),now.getMinutes());
    }

    private String TwoNums(int i){
        if(i<10) return "0"+i;
        else return i+"";
    }

    public String ToString(){
        return TwoNums(GetHour())+":"+TwoNums(GetMinute())+" "+
                GetDay()+"."+GetMonth()+"."+GetYear()+".";
    }
}

