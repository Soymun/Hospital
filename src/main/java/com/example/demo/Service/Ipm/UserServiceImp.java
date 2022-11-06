package com.example.demo.Service.Ipm;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.Plot;
import com.example.demo.Entity.Plot_;
import com.example.demo.Entity.User;
import com.example.demo.Entity.User_;
import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Mappers.UserMapper;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info("User saving");
        User user = userMapper.userDtoToUser(userDto);
        User userSaved = userRepository.save(user);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null){
            log.error("Username null");
            throw new NotFoundException("User not found");
        }
        User user = userRepository.findUserByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().getAuthority());
    }
}
