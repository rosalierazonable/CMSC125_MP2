package com.rosalieraz.cmsc125;

/*
*
* CMSC 125 - MP2 : OPERATING SYSTEMS
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

        ArrayList<Process> process = new ArrayList<>();

        try {
            File dataSet= new File("C:\\Users\\Rosalie Razonable\\Downloads\\process1.txt");
            Scanner dataReader = new Scanner(dataSet);
            dataReader.nextLine();
            while (dataReader.hasNextLine()) {
                int i  = 1;
                String data = dataReader.nextLine();
//                System.out.println(data);

                Process temp = new Process();

                StringTokenizer tokenized = new StringTokenizer(data, "\t");
                while(tokenized.hasMoreTokens()) {
//                    System.out.println(tokenized.nextToken());
                    if(i == 1)
                        temp.setOrder(Integer.parseInt(tokenized.nextToken()));
                    else if(i == 2)
                        temp.setArrival(Integer.parseInt(tokenized.nextToken()));
                    else if(i == 3)
                        temp.setBurstTime(Integer.parseInt(tokenized.nextToken()));
                    else
                        temp.setPriority(Integer.parseInt(tokenized.nextToken()));

                    i++;
                }
                process.add(temp);
                System.out.println("Size: " + process.size());
                temp.displayProcessDetails();
            }
            dataReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
        }
    }
}

/*
* REFERENCES:
* https://www.w3schools.com/java/java_files_read.asp
* https://www.javatpoint.com/string-tokenizer-in-java
*
*/
