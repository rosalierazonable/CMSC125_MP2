package com.rosalieraz.cmsc125;

import java.util.Comparator;

public class ArrivalComparator implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2)
    {
        return Integer.compare(p1.getArrival(), p2.getArrival());
    }
}
