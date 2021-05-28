package com.rosalieraz.cmsc125;

/*
*
* COMPUTER SCIENCE 125 - MP2 : OPERATING SYSTEMS
* Processor Management and Job Scheduling
* FCFS, SJF, SRPT, Priority and Round-robin scheduling
*
*/

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Process> processList = new ArrayList<>();
        String filename, path;

        System.out.print("Enter filename: ");
        Scanner filenameScanner= new Scanner(System.in);

        filename = filenameScanner.nextLine();
        path = "C:\\Users\\Rosalie Razonable\\Downloads\\" + filename;

        System.out.println();
        // File reading and parsing
        try {
            File dataSet= new File(path);
            Scanner dataReader = new Scanner(dataSet);
            dataReader.nextLine();
            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();

                int i  = 1;
                Process tempList = new Process();

                StringTokenizer tokenized = new StringTokenizer(data, "\t");
                while(tokenized.hasMoreTokens()) {
                    if(i == 1)
                        tempList.setOrder(tokenized.nextToken());
                    else if(i == 2)
                        tempList.setArrival(Integer.parseInt(tokenized.nextToken()));
                    else if(i == 3)
                        tempList.setBurstTime(Integer.parseInt(tokenized.nextToken()));
                    else
                        tempList.setPriority(Integer.parseInt(tokenized.nextToken()));

                    i++;
                }
                processList.add(tempList);
            }
            dataReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        }

        // First-Come-First-Serve Scheduling
        FCFS fcfsAlgo = new  FCFS(processList);
        fcfsAlgo.setTimeAttributes();
        fcfsAlgo.displayProcess();

        // Shortest Job First Scheduling
        SJF sjfAlgo = new SJF(processList);
        sjfAlgo.setTimeAttributes();
        sjfAlgo.displayProcess();

        // Shortest Remaining Process Time First Scheduling
        SRPT srptAlgo = new SRPT(processList);
        srptAlgo.displayProcessSRPT();

        // Non-Preemptive Priority Scheduling
        Priority priorityAlgo = new Priority(processList);
        priorityAlgo.setTimeAttributes();
        priorityAlgo.displayProcess();

        // Round Robin Scheduling
        RR rrAlgo = new RR(processList);
        rrAlgo.displayProcess();
    }
}

/*
* REFERENCES:
* https://www.w3schools.com/java/java_files_read.asp
* https://www.javatpoint.com/string-tokenizer-in-java
* https://javagoal.com/java-sort-arraylist/
* https://iq.opengenus.org/first-come-first-serve-cpu-scheduling/
* https://www.geeksforgeeks.org/program-for-shortest-job-first-or-sjf-cpu-scheduling-set-1-non-preemptive/
* https://www.javatpoint.com/os-round-robin-scheduling-algorithm
*/
