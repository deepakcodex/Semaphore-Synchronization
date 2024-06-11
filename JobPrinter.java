package com.mycompany.semaphore;
  
/**
  *
  * @author deepak
  */
public class JobPrinter implements Runnable
{
    private JobPrinterQueue PQ;
    JobPrinter(JobPrinterQueue PQ){
        this.PQ=PQ;
    }
    public void run(){
    System.out.println("Going to print a book \n"+Thread.currentThread().getName());
        PQ.printJob(new Object());
    }
}