package com.example.match.service.implementation;

import com.example.match.model.AccessDay;
import com.example.match.model.AccessHour;
import com.example.match.repository.AccessDayRepository;
import com.example.match.repository.AccessHourRepository;
import com.example.match.service.TimeAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class TimeAccessServiceImpl implements TimeAccessService {

    @Autowired
    AccessDayRepository accessDayRepository;
    @Autowired
    AccessHourRepository accessHourRepository;

    @Override
    public AccessDay accessDay(Integer currentDay) {
        AccessDay access = accessDayRepository.getAccessDayByDayInteger(currentDay);
        System.out.println(access.getDayInteger());
        return access;
    }

    @Override
    public AccessHour accessHours() {

        AccessHour accessHour = new AccessHour();
        for (AccessHour temp : accessHourRepository.findAll()) {
                accessHour = temp;
        }
        return accessHour;
    }

    @Override
    public void saveDays() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
        AccessDay accessDay = new AccessDay();
        //accessDay.setDay(dateFormat.format(date));
        accessDay.setAccess(true);
        accessDay.setDayInteger(date.getDay());
        accessDayRepository.save(accessDay);
    }

    @Override
    public void saveHours() {
        Date date = new Date();
        AccessHour accessHour = new AccessHour();
        accessHour.setComeHour(7);
        accessHour.setComeHourEnd(12);
        accessHour.setLeaveHour(13);
        accessHour.setLeaveHourEnd(24);
        accessHour.setAccess(true);
        accessHourRepository.save(accessHour);
    }
}
