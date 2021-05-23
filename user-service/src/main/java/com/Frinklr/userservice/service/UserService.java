package com.Frinklr.userservice.service;

import com.Frinklr.userservice.VO.Department;
import com.Frinklr.userservice.VO.ResponseTemplateVO;
import com.Frinklr.userservice.entity.User;
import com.Frinklr.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
@Slf4j
@Configuration
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId() ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
