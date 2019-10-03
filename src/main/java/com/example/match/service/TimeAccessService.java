package com.example.match.service;

import com.example.match.model.AccessDay;
import com.example.match.model.AccessHour;

public interface TimeAccessService {
    AccessDay accessDay(Integer currentDay);
    AccessHour accessHours();
    void saveDays();
    void saveHours();
}
