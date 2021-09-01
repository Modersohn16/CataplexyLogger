package com.example.catlogger;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Log
{
    int duration;
    int average;
    LocalDate time;

    // constructor. Duration set to average
    public Log(LocalDate time)
    {
        this.time = time;
        duration = average;
    }
    // constructor, both time and duration set with constructor
    public Log(LocalDate time, int duration)
    {
        this.time = time;
        this.duration = duration;
    }

    // getters for time, duration and average
    public LocalDate getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public int getAverage() {
        return average;
    }

    // setters for duration, average, time
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
