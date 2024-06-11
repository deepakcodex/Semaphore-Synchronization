package com.mycompany.semaphore;
 
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
/**
 *
 * @author deepak
 */
public class JobPrinterQueue 
{
 
    private final Semaphore semaphore;
 
    private final Lock PL;
    
    private boolean freePrinters[];
    public JobPrinterQueue()
    {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];
        Arrays.fill(freePrinters, true);
        PL = new ReentrantLock();
    }
 
    public void printJob(Object document)
    {
        try
        {
    
            semaphore.acquire();
        
            int assignedPrinter = getPrinter();
        
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName()+ ": Printer " + assignedPrinter+ " : Printing a Job during " + (duration / 1000)+ " seconds :: Time - " + new Date());
            Thread.sleep(duration);
        
            releasePrinter(assignedPrinter);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
        
            semaphore.release();
        }
    }
    
    private int getPrinter() 
    {
        int foundPrinter = -1;
        try 
        {
    
            PL.lock();
    
            for (int i = 0; i < freePrinters.length; i++)
            {
    
                if (freePrinters[i])
                {
                    foundPrinter = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        }

        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally
        {
    
            PL.unlock();
        }
    return foundPrinter;
    }
    
        private void releasePrinter(int i) 
        {
                PL.lock();
    
                freePrinters[i] = true;
                PL.unlock();
        }
}