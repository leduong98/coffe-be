package com.example.coffeebe.app.dtos.request;

import com.example.coffeebe.domain.entities.author.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@JsonTypeName("user")
public class RegisterRequest implements DTO<User> {

    @NotNull(message = "fullname not null")
    @JsonProperty("full_name")
    private String fullname;

    @NotNull(message = "phone not null")
    private String phone;

    @Email(message = "invalid email")
    private String email;

    @NotEmpty
    @Size(min = 2, max = 100, message = "password from 2 to 100 character")
    private String password;

    @NotEmpty(message = "address not empty")
    @NotNull(message = "address not null")
    private String address;

    @NotNull(message = "birthday not null")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date birthday;

}
