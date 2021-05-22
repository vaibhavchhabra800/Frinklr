package com.Frinklr.userservice.VO;

import com.Frinklr.userservice.entity.User;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private User user;
    private Department department;
}
