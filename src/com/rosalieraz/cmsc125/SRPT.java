 package com.rosalieraz.cmsc125;

 /*
  * SHORTEST REMAINING PROCESSING TIME SCHEDULING
  */

import java.util.*;

 public class SRPT {
    private final ArrayList<Process> processList;
    private float avgWaitingTime;
    private float avgTurnaroundTime;

    public SRPT(ArrayList<Process> processList) {
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
    ArrayList<Integer> getIndicesOfAT(int arrival_time, int process_id) {
        int idx = 0;
        ArrayList<Integer> indices = new ArrayList<>();

        while(idx < this.processList.size()) {
             if(this.processList.get(idx).getArrival() == arrival_time && this.processList.get(idx).getUID() != process_id)
                 indices.add(idx);

             idx++;
        }
        return indices;
    }
    int getIdxByUID(int uid) {
        for (int i = 0; i < this.processList.size(); i++) {
            if(this.processList.get(i).getUID() == uid)
                return i;
        }
        return -1;
    }

    Queue<String> preempt(){
        int currFT = 0, idx;
        Queue<Integer> isComplete = new LinkedList<>();
        Queue<String> gantArr = new LinkedList<>();

        this.sortProcessByAT(); // sort process list by arrival time

        for(int i = 0; i < this.processList.size(); i++) {
            int bT = this.processList.get(i).getBurstTime();
            idx = this.processList.get(i).getArrival();

            if(isComplete.contains(i)) {
                continue;
            }

            for(int j = idx; j < this.processList.size(); j++) {
                if(bT > this.processList.get(j).getBurstTime()) {
                    idx = currFT = idx + (this.processList.get(j).getBurstTime());

                    this.processList.get(j).setFinishTime(currFT);
                    this.processList.get(j).setTurnaround_time(this.processList.get(j).getFinishTime() - this.processList.get(j).getArrival());
                    this.processList.get(j).setWaiting_time(this.processList.get(j).getTurnaround_time() - this.processList.get(j).getBurstTime());

                    for(int counter = 0; counter < this.processList.get(j).getBurstTime(); counter++) {
                        gantArr.add(this.processList.get(j).getOrder());
                    }
                    isComplete.add(j);
                } else {
                    gantArr.add(this.processList.get(i).getOrder());
                    idx = ++currFT;
                    bT--;
                }
            }
            if(bT > 0) {
                currFT += bT;
                this.processList.get(i).setFinishTime(currFT);
                this.processList.get(i).setTurnaround_time(this.processList.get(i).getFinishTime() - this.processList.get(i).getArrival());
                this.processList.get(i).setWaiting_time(this.processList.get(i).getTurnaround_time() - this.processList.get(i).getBurstTime());

                while(bT>0) {
                    gantArr.add(this.processList.get(i).getOrder());
                    bT--;
                }
                isComplete.add(i);
            }
        }

        this.calculateAvgWT();
        this.calculateAvgTT();

        return gantArr;
    }
    void displayProcess() {
        System.out.println("----------------------------- SHORTEST REMAINING TIME FIRST SCHEDULING -----------------------------");
        System.out.println();

        this.displayGant(this.preEmpt());
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
    void displayGant(Queue<String>  gant) {
        System.out.println("Gant Chart:" );
        for(String s: gant) {
            System.out.print("[ " + s + " ]");
        }
        System.out.println();
    }

    Queue<String> preEmpt() {
//    void preEmpt() {
        int lastAT, idx, ctr = 0;
        Queue<String> gantArr = new LinkedList<>();
        Queue<Integer> isComplete = new LinkedList<>();
        ArrayList<Integer> aT;

        this.sortProcessByAT(); // sort process list by arrival time

        lastAT = this.processList.get(this.processList.size()-1).getArrival(); // get the largest arrival time
        idx = this.processList.get(0).getArrival(); // set the first idx to the first arrival time
        Process currProcess = this.processList.get(0); // get current process
        ArrayList<Process> readyPlist = new ArrayList<>();

        readyPlist.add(currProcess);

        while(idx <= lastAT) {

            if(!isComplete.contains(currProcess.getUID())) {
                aT = this.getIndicesOfAT(idx, currProcess.getUID()); // get all indices of the process with the indicated arrival time

                if(!aT.isEmpty()) {
                    for (Integer ind: aT) {
                        readyPlist.add(this.processList.get(ind));
                        readyPlist.sort(new UpdatedBTComparator());
                    }
                }

                if(currProcess.getUpdatedBT() > readyPlist.get(0).getUpdatedBT()) {
                    gantArr.add(readyPlist.get(0).getOrder());
                    readyPlist.get(0).setUpdatedBT(readyPlist.get(0).getUpdatedBT()-1);

                    if(readyPlist.get(0).getUpdatedBT() == 0) {
                        this.processList.get(this.getIdxByUID(readyPlist.get(0).getUID())).setFinishTime(idx+1);
                        isComplete.add(readyPlist.get(0).getUID());
                        readyPlist.remove(0);
                    } else {
                        currProcess = readyPlist.get(0);
                        this.processList.get(this.getIdxByUID(currProcess.getUID())).setFinishTime(idx+1);
                    }
                } else {
                    this.processList.get(this.getIdxByUID(currProcess.getUID())).setFinishTime(idx+1);
                    currProcess.setUpdatedBT(currProcess.getUpdatedBT()-1);
                    gantArr.add(currProcess.getOrder());
                }
            }
            idx++;
        }

        for (Process p: readyPlist) {
            idx += p.getUpdatedBT();
            this.processList.get(this.getIdxByUID(p.getUID())).setFinishTime(idx);
            for (int i = 0; i < p.getUpdatedBT(); i++) {
                gantArr.add(p.getOrder());
            }
        }

        for (Process p: this.processList) {
            p.setTurnaround_time(p.getFinishTime() - p.getArrival());
            p.setWaiting_time(p.getTurnaround_time() - p.getBurstTime());
        }

        this.calculateAvgWT();
        this.calculateAvgTT();

        return gantArr;
    }
}
