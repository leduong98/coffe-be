package com.example.coffebe.domain.entities.author;

import com.example.coffebe.domain.entities.BaseEntity;
import com.example.coffebe.domain.entities.author.Role;
import com.example.coffebe.domain.entities.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("User")
public class User extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "user";

    @Id
    private Long id;

    @Field(name = "name")
    private String userName;

    @Field(name = "password")
    private String password;

    @Field(name = "full_name")
    private String fullName;

    @Field(name = "email")
    private String email;

    @Field(name = "phone")
    private String phoneNumber;

    @Field(name ="role_id")
    private Role role;

    @Field(name = "status")
    private UserStatus userStatus;

}
