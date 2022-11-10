package com.example.demo.Service;

import com.example.demo.DTO.RecordDto;
import com.example.demo.DTO.ReviewsDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService extends UserDetailsService {

    UserDto saveUser(User userDto);

    UserDto getUser(Long id);

    UserDto updateUser(UserDto userDto);

    void deleteUser(Long id);

    ReviewsDto saveReviews(ReviewsDto reviewsDto);

    ReviewsDto updateReviews(ReviewsDto reviewsDto);

    RecordDto saveRecord(RecordDto recordDto);

    List<RecordDto> getAllRecordByUserId(Long id);

    RecordDto updateRecord(RecordDto recordDto);

    void deleteRecord(Long id);

    User getUserByEmail(String email);
}
