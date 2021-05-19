package com.rosalieraz.cmsc125;

public class Process {
    private int order;
    private int arrival;
    private int burst_time;
    private int priority;

    // Mutator Methods
    void setOrder(int order) {
        this.order = order;
    }
    void setArrival(int arrival) {
        this.arrival = arrival;
    }
    void setBurstTime(int bTime) {
        this.burst_time = bTime;
    }
    void setPriority(int priority) {
        this.priority = priority;
    }

    // Accessor Methods
    int getOrder() {
        return this.order;
    }
    int getArrival() {
        return this.arrival;
    }
    int getBurstTime() {
        return this.burst_time;
    }
    int getPriority() {
        return this.priority;
    }

    // Helper Methods
    void displayProcessDetails() {
        System.out.println("Order: " + this.getOrder() + " Arrival: " + this.getArrival() + " Burst Time: " + this.getBurstTime() + " Priority: " + this.getPriority());
    }
}
