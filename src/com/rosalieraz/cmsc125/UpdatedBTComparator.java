package com.rosalieraz.cmsc125;

import java.util.Comparator;

public class UpdatedBTComparator implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2)
    {
        return Integer.compare(p1.getUpdatedBT(), p2.getUpdatedBT());
    }
}
