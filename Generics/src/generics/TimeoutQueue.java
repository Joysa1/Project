/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Sten
 */

public class TimeoutQueue <T> {
    ArrayList<Long> timeOfDying;
    ArrayList<T> list;

    public TimeoutQueue() {
        list = new ArrayList<T>();
        timeOfDying = new ArrayList<Long>();
    }
    
    void offer(T t, long millis)
    {
        long startTime;
        Calendar checkDate = Calendar.getInstance();
        startTime = checkDate.getTimeInMillis();
        timeOfDying.add(startTime+millis);
        list.add(t);
    }
    T poll()
    {
        Calendar checkDate = Calendar.getInstance();
        for(int k=0; k<list.size(); k++)
        {
          //  System.out.println("Time of dying: " + timeOfDying.get(k)+"current time: "+ checkDate.getTimeInMillis());
            if(this.timeOfDying.get(k)>checkDate.getTimeInMillis())
            {
                T obj = list.get(k);
                list.remove(k);
                timeOfDying.remove(k);
                return obj;
            }
        }
        return null;
    }
    int size()
    {
        int i=0;
        Calendar checkDate = Calendar.getInstance();
        for(int k=0; k<list.size(); k++)
        {
            
            if(this.timeOfDying.get(k)>checkDate.getTimeInMillis())
            {
                i++;
            }
        }
        return i;
    }
}
