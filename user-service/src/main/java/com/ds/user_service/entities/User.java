package com.ds.user_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false, length = 100, unique = true)
    private String userId;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "USER_EMAIL", nullable = false)
    private String email;
    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();
}
