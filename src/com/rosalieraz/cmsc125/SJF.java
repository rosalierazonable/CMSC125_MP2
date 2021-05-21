package com.rosalieraz.cmsc125;

import java.util.ArrayList;

public class SJF {
    private final ArrayList<Process> processList;

    public SJF(ArrayList<Process> processList) {
        this.processList = processList;
    }

    // Helper Methods
    //sort process by arrival time
    void sortProcessByAT() {
        this.processList.sort(new ArrivalComparator());
    } // this will only be applied if there is no assumption that all processes arrived at t = 0
    void sortProcessByBT() {
        this.processList.sort(new BurstTimeComparator());
    } // sort process by burst time
    void displayProcess() {
        for(Process p: this.processList) {
            System.out.println("Process: " + p.getOrder() + " Burst Time: " + p.getBurstTime());
        }
    }
}
