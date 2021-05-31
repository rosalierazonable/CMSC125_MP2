package com.rosalieraz.cmsc125;

/*
* ROUND ROBIN SCHEDULING
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RR {
    private final ArrayList<Process> processList;
    private float avgWaitingTime;
    private float avgTurnaroundTime;

    public RR(ArrayList<Process> processList) {
        this.processList = processList;
    }

    // Helper Methods
    void sortProcessByAT() {
        this.processList.sort(new ArrivalComparator());
    } // this will only be applied if there is no assumption that all processes arrived at t = 0
    void calculateAvgWT() {
        int sum = 0;
        for(Process p: this.processList) {
            sum+=p.getWaiting_time();
        }
        this.avgWaitingTime = (float) sum / this.processList.size();
    }
    void calculateAvgTT() {
        int sum = 0;
        for(Process p: this.processList) {
            sum+=p.getTurnaround_time();
        }
        this.avgTurnaroundTime = (float) sum / this.processList.size();
    }

    Queue<String> preempt() {
        int temp, idx, stop_at = 0, size;
        ArrayList<Integer> currBT = new ArrayList<>();
        Queue<String> gantArr = new LinkedList<>();

        this.sortProcessByAT();

        for(Process p: this.processList) {
            currBT.add(p.getBurstTime());
            stop_at+=p.getBurstTime();
        }

        idx = this.processList.get(0).getArrival();

        while(idx < stop_at) {
            for(int i = 0; i < this.processList.size(); i++) {

                if(currBT.get(i) == 0 )
                    continue;

                if(currBT.get(i) > 4) {
                    for(int j = 0; j < 4; j++) {
                        gantArr.add(this.processList.get(i).getOrder());
                        temp = currBT.get(i) - 1;
                        currBT.set(i, temp);
                        idx++;
                    }
                } else {
                    if(currBT.get(i) < this.processList.get(i).getBurstTime())
                        size = currBT.get(i);
                    else
                        size = this.processList.get(i).getBurstTime();

                    for(int j = 0; j < size; j ++) {
                        gantArr.add(this.processList.get(i).getOrder());
                        temp = currBT.get(i) - 1;
                        currBT.set(i, temp);
                        idx++;
                    }
                }
                this.processList.get(i).setFinishTime(idx);
            }
        }

        for(Process p: this.processList) {
            p.setTurnaround_time(p.getFinishTime() - p.getArrival());
            p.setWaiting_time(p.getTurnaround_time() - p.getBurstTime());
        }

        this.calculateAvgWT();
        this.calculateAvgTT();

        return gantArr;
    }
    void displayProcess() {
        System.out.println("----------------------------- ROUND ROBIN SCHEDULING -----------------------------");
        System.out.println();

        this.displayGant(this.preempt());

        for(Process p: this.processList) {
            System.out.println();
            System.out.println("Process Scheduled: " + p.getOrder());
            System.out.println("Arrival Time: " + p.getArrival() + " units");
            System.out.println("Burst Time: " + p.getBurstTime() + " units");
            System.out.println("Finish Time: " + p.getFinishTime() + " units");
            System.out.println("Turnaround Time: " + p.getTurnaround_time() + " units");
            System.out.println("Waiting Time: " + p.getWaiting_time() + " units");
        }
        System.out.println("=================================");
        System.out.println("AVERAGE TURNAROUND TIME: " + this.avgTurnaroundTime + " units");
        System.out.println("AVERAGE WAITING TIME: " + this.avgWaitingTime + " units");
    }
    void displayGant(Queue<String> gant) {
        System.out.println("Gant Chart:" );
        for(String s: gant) {
            System.out.print("[ " + s + " ]");
        }
        System.out.println();
    }
}
