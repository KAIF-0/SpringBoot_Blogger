package com.blogger.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UserScheduler {


    // @Scheduled(cron = "0 0/1 * * * ?") //runs every 1 minute
    public void scheduledUserTask() {
        System.out.println("User scheduled task executed.");
    }
    
}
