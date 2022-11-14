package com.example.demo.Service.Ipm;

import com.example.demo.DTO.RecordDto;
import com.example.demo.DTO.ReviewsDto;
import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.*;
import com.example.demo.Entity.Record;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repositories.RecordRepository;
import com.example.demo.Repositories.ReviewsRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ReviewsRepository reviewsRepository;

    private final RecordRepository recordRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, ReviewsRepository reviewsRepository, RecordRepository recordRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.reviewsRepository = reviewsRepository;
        this.recordRepository = recordRepository;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDto saveUser(User userDto) {
        log.info("User saving");
        User userSaved = userRepository.save(userDto);
        log.info("User saved");
        return userMapper.userToUserDto(userSaved);
    }

    @Override
    public UserDto getUser(Long id) {
        log.info("User get with id: {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserDto> cq = cb.createQuery(UserDto.class);
        Root<User> root = cq.from(User.class);

        Join<User, Plot> join = root.join(User_.PLOT);
        cq.where(cb.equal(root.get(User_.ID), id));

        cq.multiselect(
                root.get(User_.ID),
                root.get(User_.NAME),
                root.get(User_.SURNAME),
                root.get(User_.AGE),
                root.get(User_.OMS),
                root.get(User_.SNILS),
                root.get(User_.PLOT_ID),
                join.get(Plot_.NAME_PLOT)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("User updating");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<User> cu = cb.createCriteriaUpdate(User.class);
        Root<User> root = cu.getRoot();

        cu.set(User_.ID, userDto.getId());
        cu.set(User_.NAME, userDto.getName());
        cu.set(User_.SURNAME, userDto.getSurname());
        cu.set(User_.AGE, userDto.getAge());
        cu.set(User_.SNILS, userDto.getSnils());
        cu.set(User_.OMS, userDto.getOMS());
        cu.set(User_.PLOT_ID, userDto.getPlotId());

        cu.where(cb.equal(root.get(User_.ID), userDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("User updated");
        return userDto;
    }

    @Override
    public void deleteUser(Long id) {
        log.info("User deleting");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> cd = cb.createCriteriaDelete(User.class);
        cd.where(cb.equal(cd.getRoot().get(User_.ID), id));
        entityManager.createQuery(cd).executeUpdate();
        log.info("User deleted");
    }

    @Override
    public ReviewsDto saveReviews(ReviewsDto reviewsDto) {
        log.info("Save review");
        Reviews reviews = userMapper.reviewsDtoToReviews(reviewsDto);
        Reviews savedReviews = reviewsRepository.save(reviews);
        log.info("Review saved with id {}", savedReviews.getId());
        return userMapper.reviewsToReviewsDto(savedReviews);
    }

    @Override
    public ReviewsDto updateReviews(ReviewsDto reviewsDto) {
        log.info("Review updating");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Reviews> cu = cb.createCriteriaUpdate(Reviews.class);
        Root<Reviews> root = cu.getRoot();

        cu.set(Reviews_.ID, reviewsDto.getId());
        cu.set(Reviews_.MARKS, reviewsDto.getMarks());
        cu.set(Reviews_.ABOUT, reviewsDto.getAbout());
        cu.set(Reviews_.USER_ID, reviewsDto.getUserId());
        cu.set(Reviews_.DOCTOR_ID, reviewsDto.getDoctorId());

        cu.where(cb.equal(root.get(User_.ID), reviewsDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Reviews updated");
        return reviewsDto;
    }

    @Override
    public RecordDto saveRecord(RecordDto recordDto) {
        log.info("Save record");
        Record record = userMapper.recordDtoToRecord(recordDto);
        Record savedRecord = recordRepository.save(record);
        log.info("Records saved with id {}", savedRecord.getId());
        return userMapper.recordToRecordDto(savedRecord);
    }

    @Override
    public List<RecordDto> getAllRecordByUserId(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecordDto> cq = cb.createQuery(RecordDto.class);
        Root<Record> root = cq.from(Record.class);

        cq.where(cb.equal(root.get(Record_.USER_ID), id));

        cq.multiselect(
                root.get(Record_.ID),
                root.get(Record_.DATE),
                root.get(Record_.DATE_RECORD),
                root.get(Record_.USER_ID),
                root.get(Record_.DOCTOR_ID)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public RecordDto updateRecord(RecordDto recordDto) {
        log.info("Record updating");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Record> cu = cb.createCriteriaUpdate(Record.class);
        Root<Record> root = cu.getRoot();

        cu.set(Record_.ID, recordDto.getId());
        cu.set(Record_.DATE, recordDto.getDate());
        cu.set(Record_.DATE_RECORD, recordDto.getDateRecord());
        cu.set(Record_.USER_ID, recordDto.getUserId());
        cu.set(Record_.DOCTOR_ID, recordDto.getDoctorId());

        cu.where(cb.equal(root.get(User_.ID), recordDto.getId()));
        entityManager.createQuery(cu).executeUpdate();
        log.info("Record updated");
        return recordDto;
    }

    @Override
    public void deleteRecord(Long id) {
        log.info("Record deleting");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<Record> cd = cb.createCriteriaDelete(Record.class);
        cd.where(cb.equal(cd.getRoot().get(Record_.ID), id));
        entityManager.createQuery(cd).executeUpdate();
        log.info("Record deleted");
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("User get by email");
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            log.error("Username null");
            throw new NotFoundException("User not found");
        }
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().getAuthority());
    }
}
