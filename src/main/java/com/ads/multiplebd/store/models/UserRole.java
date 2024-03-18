package com.ads.multiplebd.store.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRole {
    @EmbeddedId
    private UserRoleKey id;

    @Column(name = "created_at")
    private Date createdAt = Date.valueOf(LocalDate.now());


}

