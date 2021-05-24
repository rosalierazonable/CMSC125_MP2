package com.rosalieraz.cmsc125;

import java.util.ArrayList;

public class FCFS {
    private final int numOfProcesses; // number of processes
    private final ArrayList<Process> processList;
    private float avgWaitingTime;
    private float avgTurnaroundTime;

    FCFS(ArrayList<Process> pList) {
        this.processList = pList;
        this.numOfProcesses = pList.size();
    }

    // Helper Functions
    int calculateWaitingTime(int turnaround_time, int burst_time) {
        return turnaround_time - burst_time;
    }
    int calculateTurnaroundTime(int finish_time, int arrival_time) {
        return finish_time - arrival_time;
    }
    int calculateFinishTime(int burst_time, int finish_time) {
        return burst_time + finish_time; // for i = 0, this will be arrival time + burst time
    }
    int calculateAvgWaitingTime() {
        int sum = 0;
        for(Process  p: this.processList) {
            sum+=p.getWaiting_time();
        }
        return sum / this.processList.size();
    }
    int calculateAVgTurnaroundTime() {
        int sum = 0;
        for(Process  p: this.processList) {
            sum+=p.getTurnaround_time();
        }
        return sum / this.processList.size();
    }
    void sortProcess() {
        this.processList.sort(new ArrivalComparator());
    }
    void displayProcess() {
        System.out.println("------ FIRST-COME-FIRST-SERVE SCHEDULING ------");
        System.out.println();
        for(Process p: this.processList) {
            System.out.println("Process Scheduled: " + p.getOrder() + " ");
            System.out.println("Finish Time: " + p.getFinishTime() + " ");
            System.out.println("Waiting Time: " + p.getWaiting_time() + " ");
            System.out.println("Turnaround Time: " + p.getTurnaround_time() + " ");
            System.out.println();
        }
        System.out.println("===================================");
        System.out.println("AVERAGE TURNAROUND TIME: " + this.getAvgTurnaroundTime());
        System.out.println("AVERAGE WAITING TIME: " + this.getAvgWaitingTime());
        System.out.println();
    }

    // Mutator Methods
    void setAvgWaitingTime(int waitingTime) {
        this.avgWaitingTime = waitingTime;
    }
    void setAvgTurnaroundTime(int avgTurnaroundTime) {
        this.avgTurnaroundTime = avgTurnaroundTime;
    }
    void setTimeAttributes() {
        // USES THE ARRIVAL FIELD IN THE TABLE FROM THE FILE
//        this.processList.get(0).setFinishTime(this.calculateFinishTime(this.processList.get(0).getArrival(), this.processList.get(0).getBurstTime()));
//        this.processList.get(0).setTurnaround_time(this.calculateTurnaroundTime(this.processList.get(0).getFinishTime(), this.processList.get(0).getArrival()));
//        this.processList.get(0).setWaiting_time(this.calculateWaitingTime(this.processList.get(0).getTurnaround_time(), this.processList.get(0).getBurstTime()));

        // ASSUMING THAT ALL PROCESSES ARRIVED AT t = 0
        this.processList.get(0).setFinishTime(this.calculateFinishTime(0, this.processList.get(0).getBurstTime()));
        this.processList.get(0).setTurnaround_time(this.calculateTurnaroundTime(this.processList.get(0).getFinishTime(), 0));
        this.processList.get(0).setWaiting_time(this.calculateWaitingTime(this.processList.get(0).getTurnaround_time(), this.processList.get(0).getBurstTime()));

        for(int i = 1; i < this.numOfProcesses; i++) {
            // USES THE ARRIVAL FIELD IN THE TABLE FROM THE FILE
//            this.processList.get(i).setFinishTime(this.calculateFinishTime(this.processList.get(i-1).getFinishTime(), this.processList.get(i).getBurstTime()));
//            this.processList.get(i).setTurnaround_time(this.calculateTurnaroundTime(this.processList.get(i).getFinishTime(), this.processList.get(i).getArrival()));
//            this.processList.get(i).setWaiting_time(this.calculateWaitingTime(this.processList.get(i).getTurnaround_time(), this.processList.get(i).getBurstTime()));

            // ASSUMING THAT ALL PROCESSES ARRIVED AT t = 0
            this.processList.get(i).setFinishTime(this.calculateFinishTime(this.processList.get(i-1).getFinishTime(), this.processList.get(i).getBurstTime()));
            this.processList.get(i).setTurnaround_time(this.calculateTurnaroundTime(this.processList.get(i).getFinishTime(), 0));
            this.processList.get(i).setWaiting_time(this.calculateWaitingTime(this.processList.get(i).getTurnaround_time(), this.processList.get(i).getBurstTime()));
        }

        this.setAvgWaitingTime(this.calculateAvgWaitingTime());
        this.setAvgTurnaroundTime(this.calculateAVgTurnaroundTime());
    }

    // Accessor Methods
    float getAvgWaitingTime() {
        return this.avgWaitingTime;
    }
    float getAvgTurnaroundTime() {
        return this.avgTurnaroundTime;
    }
}
