package com.example.match.service;

import com.example.match.model.AccessHour;

public interface TimeAccessService {
    boolean accessDay(Integer currentDay);
    AccessHour accessHours();
    void saveDays();
    void saveHours();
}
