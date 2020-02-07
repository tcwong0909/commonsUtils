package com.tcwong.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserVO {
    @Id
    private String userId;
    private String userName;
    private Book books;
}
