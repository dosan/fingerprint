package com.example.match.controller;

import com.example.match.model.Owner;
import com.example.match.model.OwnerEntry;
import com.example.match.repository.UserRepository;
import com.example.match.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import  com.example.match.iin.*;

@RestController
@RequestMapping("/upload")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private FingerprintService fingerprintService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Qualifier("comeServiceImpl")
    @Autowired
    private OwnerEntryService comeEntryService;
    @Qualifier("leaveServiceImpl")
    @Autowired
    private OwnerEntryService leaveEntryService;
    @Autowired
    private TimeAccessService timeAccessService;
    @Autowired
    private GroupService groupService;


    @PostMapping("/register")
    public Owner ComingFingerprintCompare(@RequestParam("file") MultipartFile file,
                                          @RequestParam("orgId") Integer organizationId) throws IOException {
	    System.out.println(file);
	    Owner match = fingerprintService.compare(file,organizationId);
        if(match == null){
            return new Owner();
        }

        long HOUR = 3600*1000;
        Date newDate = new Date();
        Date date = new Date(newDate.getTime() + 6 * HOUR);
        System.out.println(date.getDay());
        System.out.println(timeAccessService.accessDay(date.getDay()));
        if(timeAccessService.accessDay(date.getDay()).getDayInteger()==date.getDay()){
            System.out.println("Вошел");
            if(organizationId != null){
                comeEntryService.register(match,organizationId);
            }else {
                System.out.println("Organization is empty");
                return null;
            }
        }else{
            System.out.println("day false");
            return null;
        }
	    System.out.println(match.getName());
        return match;
    }
    @GetMapping("/time")
    public ResponseEntity currentTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/day")
    public String saveDay(){
        Date date = new Date();
        return Integer.toString(date.getDay());
    }

    @GetMapping("/hour")
    public String saveHours(){
        //Date date = new Date();
        //timeAccessService.saveHours();
        String pw_hash = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(pw_hash);
        if (BCrypt.checkpw("123456", "$2a$13$dahuOYlFmSQkxvOUY4oySOtWNYWNQzftA4SNgk7HkquT31pQ1VwHy"))
            System.out.println("It matches");
        else
            System.out.println("It does not match");

        long HOUR = 3600*1000;
        Date date = new Date(System.currentTimeMillis());
        Date newDate = new Date(date.getTime() + 6 * HOUR);
        System.out.println(newDate);
        return Integer.toString(newDate.getHours());
    }

    @PostMapping("/save")
    public String fingerprintSave(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name,
                                  @RequestParam("surname") String surname,
                                  @RequestParam("iin") String iin,
                                  @RequestParam("organizationId") Integer organizationId) throws IOException {
        logger.info(name);
        logger.info(surname);
        logger.info(iin);
        PrivatePersonId iinCheker = new PrivatePersonId(iin);
        System.out.println(iinCheker.isValid());
        System.out.println(iinCheker.getBirthDate().toString());
        System.out.println(iinCheker.getSex());
        if(!iinCheker.isValid()){
            return "ИИН не валидный";
        }
        Owner existingOwner = userService.findByAttirbutes(name,surname,iin);
        if(existingOwner ==null) {
            Owner owner = userService.saveUser(name, surname, iin);
            fingerprintService.saveSerializeFingerTemplate(file, owner);
            int count = fingerprintService.countByUser(owner);
            System.out.println(count);
            if(count == 3){
                groupService.save(organizationId,owner.getId());
                return "Регистрация успешно завершена";
            }
            return Integer.toString(count);
        }
        fingerprintService.saveSerializeFingerTemplate(file, existingOwner);
        int count = fingerprintService.countByUser(existingOwner);
        System.out.println(count);
        if(count == 3){
            groupService.save(organizationId,existingOwner.getId());
            return "Регистрация успешно завершена";
        }
	if(count>3){
	    return "Вы уже зарегистрированы";
	}
        return Integer.toString(count);

    }


}
