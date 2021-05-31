package com.rosalieraz.cmsc125;

public class Process {
    private int updatedBT;
    private int id;
    private int arrival;
    private int burst_time;
    private int finish_time;
    private int turnaround_time;
    private int waiting_time;
    private int priority;

    // Mutator Methods
    void setOrder(int order) {
        this.id = order;
    }
    void setUpdatedBT(int uBT) {
        this.updatedBT = uBT;
    }
    void setArrival(int arrival) {
        this.arrival = arrival;
    }
    void setBurstTime(int bTime) {
        this.burst_time = bTime;
    }
    void setFinishTime(int finish_time) {
        this.finish_time = finish_time;
    }
    void setTurnaround_time(int turnaround_time) {
        this.turnaround_time = turnaround_time;
    }
    void setWaiting_time(int waiting_time) {
        this.waiting_time = waiting_time;
    }
    void setPriority(int priority) {
        this.priority = priority;
    }

    // Accessor Methods
    int getUID() {
        return this.id;
    }
    String getOrder() {
        return "P" + this.id;
    }
    int getUpdatedBT() {
        return this.updatedBT;
    }
    int getArrival() {
        return this.arrival;
    }
    int getBurstTime() {
        return this.burst_time;
    }
    int getFinishTime() {
        return this.finish_time;
    }
    int getWaiting_time() {
        return this.waiting_time;
    }
    int getTurnaround_time() {
        return this.turnaround_time;
    }
    int getPriority() {
        return this.priority;
    }

    // Helper Methods
    void displayProcessDetails() {
        System.out.println("Order: " + this.getOrder() + " Arrival: " + this.getArrival() + " Burst Time: " + this.getBurstTime() + " Priority: " + this.getPriority());
    }
}
