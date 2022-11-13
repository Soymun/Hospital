package com.example.demo.Facade;

import com.example.demo.DTO.*;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Response.ResponseDto;
import com.example.demo.Service.DoctorService;
import com.example.demo.Service.Ipm.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserFacade {

    private final UserServiceImp userServiceImp;

    private final DoctorService doctorService;

    @Autowired
    public UserFacade(UserServiceImp userServiceImp, DoctorService doctorService) {
        this.userServiceImp = userServiceImp;
        this.doctorService = doctorService;
    }

    public ResponseEntity<?> getUserById(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("User not found");
        }
        UserDto userDto = userServiceImp.getUser(id);
        if(userDto == null){
            log.debug("User is null");
            throw new NotFoundException("User not found");
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(userDto);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> updateUser(UserDto userDto){
        if(userDto == null){
            log.debug("UserDto is null");
            throw new NotFoundException("Use not found");
        }
        UserDto userDto1 = userServiceImp.getUser(userDto.getId());
        if(userDto1 == null){
            log.debug("User is null");
            throw  new NotFoundException("User not found");
        }
        UserDto userDto2 = userServiceImp.updateUser(userDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(userDto2);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> deleteUser(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("User not found");
        }
        userServiceImp.deleteUser(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody("User delete. Suggest!");
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> getTroublesByUserId(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("Troubles not founds");
        }
        List<TroublesDto> troublesDtoList = doctorService.getAllTroublesByUserId(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(troublesDtoList);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> getScheduleByUserId(Long id){
        if(id == null){
            log.debug("Id is null");
            throw  new NotFoundException("Schedule not found");
        }
        List<ScheduleDto> list = doctorService.getAllScheduleByUserId(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(list);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> getALLRecordByUserId(Long id){
        if(id == null){
            log.debug("Id is null");
            throw new NotFoundException("Records not founds");
        }
        List<RecordDto> recordDtos = userServiceImp.getAllRecordByUserId(id);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(recordDtos);
        return ResponseEntity.ok(responseDto);
    }

    public ResponseEntity<?> saveReviews(ReviewsDto reviewsDto){
        if(reviewsDto == null){
            log.debug("ReviewsDto is null");
            throw new RuntimeException("Reviews save failed");
        }
        ReviewsDto reviews = userServiceImp.saveReviews(reviewsDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setBody(reviews);
        return ResponseEntity.ok(responseDto);
    }
}
