package com.rosalieraz.cmsc125;

import java.util.ArrayList;
import java.util.Collections;

public class FCFS {
    private int numOfProcesses; // number of processes
    private ArrayList<Process> processList = new ArrayList<>();

    FCFS(ArrayList<Process> pList) {
        this.processList = pList;
        this.numOfProcesses = pList.size();
    }

    // Helper Functions
    int calculateWaitingTime(int turnaround_time, int burst_time) {
        return turnaround_time - burst_time;
    }
    int calculateTurnaroundTime(int finish_time, int arrival_time) {
        return finish_time + arrival_time;
    }
    int calculateFinishTime(int burst_time, int finish_time) {
        // finish_time (i - 1)
        return burst_time + finish_time; // for i = 0, this will be arrival time + burst time
    }
    int calculateAvgWaitingTime() {
        int sum = 0;
        for(Process  p: this.processList) {
            sum+=p.getWaiting_time();
        }
        return sum/this.processList.size();
    }
    int calculateAVgTurnaroundTime() {
        int sum = 0;
        for(Process  p: this.processList) {
            sum+=p.getTurnaround_time();
        }
        return sum/this.processList.size();
    }
    void sortProcess() {
        this.processList.sort(new ArrivalComparator());
    }
    void displayProcess() {
        for(Process p: this.processList) {
            System.out.print(p.getOrder() + " ");
        }
        System.out.println();
    }

    //
}
