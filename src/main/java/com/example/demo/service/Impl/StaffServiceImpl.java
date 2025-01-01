package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository repository;

    @Override
    public List<Staff> getAllStaff() {
        return repository.findAll();
    }

    @Override
    public Staff getStaffById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return repository.save(staff);
    }

    @Override
    public void deleteStaff(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Staff updateStaff(Integer id, Staff staff) {
        Staff existingStaff = repository.findById(id).orElse(null);
        existingStaff.setFullname(staff.getFullname());
        existingStaff.setEmail(staff.getEmail());
        existingStaff.setPhoneNumber(staff.getPhoneNumber());
        existingStaff.setAddress(staff.getAddress());
        existingStaff.setDateOfBirth(staff.getDateOfBirth());
        existingStaff.setUsername(staff.getUsername());
        existingStaff.setPassword(staff.getPassword());
        existingStaff.setRole(staff.getRole());
        return repository.save(existingStaff);
    }

}
