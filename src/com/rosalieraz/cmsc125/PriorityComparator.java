package com.rosalieraz.cmsc125;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Process>{
    @Override
    public int compare(Process p1, Process p2)
    {
        return Integer.compare(p1.getPriority(), p2.getPriority());
    }
}

