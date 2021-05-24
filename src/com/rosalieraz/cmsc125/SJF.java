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
    int calculateWaitingTime(int turnaround_time, int burst_time) {
        return turnaround_time - burst_time;
    }
    int calculateTurnaroundTime(int finish_time, int arrival_time) {
        return finish_time - arrival_time;
    }
    int calculateFinishTime(int burst_time, int finish_time) {
        return burst_time + finish_time; // for i = 0, this will be arrival time + burst time
    }
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
        System.out.println("------ SHORTEST JOB FIRST SCHEDULING ------");
        System.out.println();
        for(Process p: this.processList) {
            System.out.println("Process Scheduled: " + p.getOrder());
            System.out.println("Burst Time: " + p.getBurstTime());
            System.out.println("Finish Time: " + p.getFinishTime());
            System.out.println("Turnaround Time: " + p.getTurnaround_time());
            System.out.println("Waiting Time: " + p.getWaiting_time());
            System.out.println();
        }
        System.out.println("=================================");
        System.out.println("AVERAGE TURNAROUND TIME: " + this.avgTurnaroundTime);
        System.out.println("AVERAGE WAITING TIME: " + this.avgWaitingTime);
    }

    // Mutator Methods
    void setAvgWaitingTime(float waitingTime) {
        this.avgWaitingTime = waitingTime;
    }
    void setAvgTurnaroundTime(float avgTurnaroundTime) {
        this.avgTurnaroundTime = avgTurnaroundTime;
    }
    void setTimeAttributes() {
        // When the arrival time is not assumed to be 0 for all processes, uncomment this
        /*this.processList.get(0).setFinishTime(this.calculateFinishTime(this.processList.get(0).getBurstTime(), 0 *//*arrival time at t = 0*//*));
        this.processList.get(0).setTurnaround_time(this.calculateTurnaroundTime(this.processList.get(0).getFinishTime(), 0));
        this.processList.get(0).setWaiting_time(this.calculateWaitingTime(this.processList.get(0).getTurnaround_time(), this.processList.get(0).getBurstTime()));

        for(int i = 1; i < this.processList.size(); i++) {
            if(this.processList.get(i).getArrival() > this.processList.get(i-1).getFinishTime())
                this.processList.get(i).setFinishTime(this.processList.get(i).getBurstTime() + this.processList.get(i).getArrival());
            else
                this.processList.get(i).setFinishTime(this.processList.get(i-1).getFinishTime() + this.processList.get(i).getBurstTime());

            this.processList.get(i).setTurnaround_time(this.processList.get(i).getFinishTime() - this.processList.get(i).getArrival());
            this.processList.get(i).setWaiting_time(this.processList.get(i).getTurnaround_time() - this.processList.get(i).getBurstTime());
        }*/

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

    // Accessor Methods
    float getAvgWaitingTime() {
        return this.avgWaitingTime;
    }
    float getAvgTurnaroundTime() {
        return this.avgTurnaroundTime;
    }
}
