package com.rosalieraz.cmsc125;

import java.util.ArrayList;

public class SJF {
    private final ArrayList<Process> processList;
    private float avgWaitingTime;
    private float avgTurnaroundTime;

    public SJF(ArrayList<Process> processList) {
        this.processList = processList;
    }

    // Helper Methods
    //sort process by arrival time
    float calculateAvgWaitingTime() {
        float sum = 0;
        for(Process  p: this.processList) {
            sum+=p.getWaiting_time();
        }
        return sum / this.processList.size();
    }
    float calculateAVgTurnaroundTime() {
        float sum = 0;
        for(Process  p: this.processList) {
            sum+=p.getTurnaround_time();
        }
        return sum / this.processList.size();
    }
    void sortProcessByAT() {
        this.processList.sort(new ArrivalComparator());
    } // this will only be applied if there is no assumption that all processes arrived at t = 0
    void sortProcessByBT() {
        this.processList.sort(new BurstTimeComparator());
    } // sort process by burst time
    void displayProcess() {
        System.out.println("--------------------------------------- SHORTEST JOB FIRST SCHEDULING ---------------------------------------");
        System.out.println();
        this.displayGantChart();
        for(Process p: this.processList) {
            System.out.println("Process Scheduled: " + p.getOrder());
            System.out.println("Burst Time: " + p.getBurstTime() + " units");
            System.out.println("Finish Time: " + p.getFinishTime() + " units");
            System.out.println("Turnaround Time: " + p.getTurnaround_time() + " units");
            System.out.println("Waiting Time: " + p.getWaiting_time() + " units");
            System.out.println();
        }
        System.out.println("=================================");
        System.out.println("AVERAGE TURNAROUND TIME: " + this.avgTurnaroundTime + " units");
        System.out.println("AVERAGE WAITING TIME: " + this.avgWaitingTime + " units");
        System.out.println();
    }
    void displayGantChart() {
        System.out.println("Gant Chart:");
        for(Process p: this.processList) {
            for(int i = 0; i < p.getBurstTime(); i++)
                System.out.print("[ " + p.getOrder() + " ]");
        }
        System.out.println();
        System.out.println();
    }

    // Mutator Methods
    void setAvgWaitingTime(float waitingTime) {
        this.avgWaitingTime = waitingTime;
    }
    void setAvgTurnaroundTime(float avgTurnaroundTime) {
        this.avgTurnaroundTime = avgTurnaroundTime;
    }
    void setTimeAttributes() {

        this.sortProcessByAT(); // sort process list by arrival time
        this.sortProcessByBT(); // sort process list by burst time

        this.processList.get(0).setFinishTime(this.processList.get(0).getBurstTime());
        this.processList.get(0).setTurnaround_time(this.processList.get(0).getBurstTime());
        this.processList.get(0).setWaiting_time(0);

        for(int i = 1; i < this.processList.size(); i++) {
            this.processList.get(i).setTurnaround_time(this.processList.get(i-1).getFinishTime() + this.processList.get(i).getBurstTime());
            this.processList.get(i).setFinishTime(this.processList.get(i).getTurnaround_time());
            this.processList.get(i).setWaiting_time(this.processList.get(i).getTurnaround_time() - this.processList.get(i).getBurstTime());
        }

        this.setAvgWaitingTime(this.calculateAvgWaitingTime());
        this.setAvgTurnaroundTime(this.calculateAVgTurnaroundTime());
    }
}
