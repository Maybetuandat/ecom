// package com.example.demo.service.Impl;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.example.demo.model.Staff;
// import com.example.demo.repository.StaffRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

// @Autowired
// private StaffRepository staffRepository;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// Staff staff = staffRepository.findByUsername(username);
// if (staff == null) {
// throw new UsernameNotFoundException("User not found");
// }

// // Chuyển đổi từ Staff sang UserDetails của Spring Security
// return User.builder()
// .username(staff.getUsername())
// .password(staff.getPassword())
// .roles(staff.getRole()) // Giả sử role được lưu dưới dạng string "USER",
// "ADMIN"
// .build();
// }
// }
