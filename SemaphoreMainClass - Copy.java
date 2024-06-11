package com.mycompany.semaphore;

public class SemaphoreMainClass 
{
    
    public static void main(String[] args) 
    {
        String person[] = {"Deepak","Muskan","Ahmed","Rahul","Sardar","Ameeque","Haseeb","Ahsan","Nikhil","Raveena"};
        
        JobPrinterQueue PQ = new JobPrinterQueue();
        
        Thread thread[] = new Thread[10];
        
        for (int i = 0; i < 10; i++) 
        {
            thread[i] = new Thread(new JobPrinter(PQ), "Person   " + person[i]);
        }
        
        for (int i = 0; i < 10; i++) 
        {
            thread[i].start();
        }
    }
}
