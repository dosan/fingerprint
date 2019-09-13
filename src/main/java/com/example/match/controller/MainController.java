package com.example.match.controller;
;
import com.example.match.model.User;
import com.example.match.service.FingerprintService;
import com.example.match.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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


    @PostMapping("/in")
    public User ComingFingerprintCompare(@RequestParam("file") MultipartFile file) throws IOException { ;
	    System.out.println(file);
	    User match = fingerprintService.compare(file);
        if(match == null){
            return new User();
        }
        System.out.println(match);
        return match;
    }

    @PostMapping("/out")
    public String LeavingFingerprintCompare(@RequestParam("file") MultipartFile file) throws IOException { ;
        System.out.println(file);
        User match = fingerprintService.compare(file);
        if(match == null){
            return "Not Found";
        }
        System.out.println(match);
        return match.getName();
    }

    @PostMapping("/save")
    public User fingerprintSave(@RequestParam("file") MultipartFile file,
                                  @RequestParam("name") String name,
                                  @RequestParam("surname") String surname,
                                  @RequestParam("iin") String iin) throws IOException {
        logger.info(name);
        logger.info(surname);
        logger.info(iin);
        User user = userService.saveUser(name,surname,iin);
        fingerprintService.saveSerializeFingerTemplate(file,user);
        return user;
    }


}
