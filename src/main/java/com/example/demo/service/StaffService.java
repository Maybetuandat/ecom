package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Staff;

@Service
public interface StaffService {
    public List<Staff> getAllStaff();

    public Staff getStaffById(Integer id);

    public Staff saveStaff(Staff staff);

    public void deleteStaff(Integer id);

    public Staff updateStaff(Integer id, Staff staff);

    public Staff getStaffByEmail(String email);
}
