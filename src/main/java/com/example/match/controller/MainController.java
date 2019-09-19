package com.example.match.controller;
;
import com.example.match.model.User;
import com.example.match.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private FingerprintService fingerprintService;
    @Autowired
    private UserService userService;
    @Autowired
    private ComeService comeService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private TimeAccessService timeAccessService;


    @PostMapping("/register")
    public User ComingFingerprintCompare(@RequestParam("file") MultipartFile file) throws IOException { 
	    System.out.println(file);
	    User match = fingerprintService.compare(file);
        if(match == null){
            return new User();
        }

        Date date = new Date();
        if(timeAccessService.accessDay(date.getDay())){
        if(date.getHours() >= timeAccessService.accessHours().getComeHour() && date.getHours() <= timeAccessService.accessHours().getComeHourEnd()){
            comeService.register(match);
        }else if(date.getHours() > timeAccessService.accessHours().getLeaveHour() && date.getHours() <= timeAccessService.accessHours().getLeaveHourEnd()){
            leaveService.register(match);
        }else{
            return null;
        }
        }else{
            return null;
        }
        logger.info(match.getName(),match.getSurname(),match.getIin());
        return match;
    }
    @GetMapping("/time")
    public Integer currentTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return date.getHours();
    }
    @GetMapping("/day")
    public String saveDay(){
        timeAccessService.saveDays();
        return "Saved";
    }
    @GetMapping("/today")
    public boolean todayIsAccessDay(){
        Date date = new Date();
        return timeAccessService.accessDay(date.getDay());
    }
    @GetMapping("/hour")
    public String saveHours(){
        Date date = new Date();
        timeAccessService.saveHours();
        return "saved";
    }

    @PostMapping("/save")
    public String fingerprintSave(@RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("iin") String iin) throws IOException {
        logger.info(name);
        logger.info(surname);
        logger.info(iin);
        User existingUser = userService.findByAttirbutes(name,surname,iin);
        if(existingUser==null) {
            User user = userService.saveUser(name, surname, iin);
            fingerprintService.saveSerializeFingerTemplate(file,user);
            int count = fingerprintService.countByUser(user);
            System.out.println(count);
            if(count == 3){
                return "Регистрация успешно завершена";
            }
            return Integer.toString(count);
        }
        fingerprintService.saveSerializeFingerTemplate(file,existingUser);
        int count = fingerprintService.countByUser(existingUser);
        System.out.println(count);
        if(count == 3){
            return "Регистрация успешно завершена";
        }
	if(count>3){
	    return "Вы уже зарегистрированы";
	}
        return Integer.toString(count);

    }


}
