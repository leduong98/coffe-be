package com.example.coffebe.domain.entities;

import com.example.coffebe.domain.utils.Helper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Field(name = "created_at")
    @CreatedDate
    protected LocalDateTime createdAt = Helper.getTodayDateTimeHCMZone();

    @Field(name = "updated_at")
    @LastModifiedDate
    protected LocalDateTime updatedAt = Helper.getTodayDateTimeHCMZone();

}
