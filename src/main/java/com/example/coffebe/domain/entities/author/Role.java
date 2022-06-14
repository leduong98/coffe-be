package com.example.coffebe.domain.entities.author;

import com.example.coffebe.domain.entities.BaseEntity;
import com.example.coffebe.domain.entities.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("Role")
public class Role extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "role";

    @Id
    private Long id;

    @Field(name = "role_name")
    private RoleType roleType;

}
