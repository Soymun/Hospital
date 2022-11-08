package com.example.demo.Mappers;

import com.example.demo.DTO.RecordDto;
import com.example.demo.DTO.ReviewsDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.Record;
import com.example.demo.Entity.Reviews;
import com.example.demo.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    Reviews reviewsDtoToReviews(ReviewsDto reviewsDto);

    ReviewsDto reviewsToReviewsDto(Reviews reviews);

    Record recordDtoToRecord(RecordDto recordDto);

    RecordDto recordToRecordDto(Record record);
}
