package com.example.demo.Schedule;


import com.example.demo.Service.Ipm.DoctorServiceImp;
import com.example.demo.Service.Ipm.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecordSchedule {

    private final UserServiceImp userServiceImp;

    private final DoctorServiceImp doctorServiceImp;

    @Autowired
    public RecordSchedule(UserServiceImp userServiceImp, DoctorServiceImp doctorServiceImp) {
        this.userServiceImp = userServiceImp;
        this.doctorServiceImp = doctorServiceImp;
    }

    @Scheduled(zone = "GTM+3.00", initialDelay = 10000, fixedDelayString = "5000")
    public void saveSchedule(){

    }
}
