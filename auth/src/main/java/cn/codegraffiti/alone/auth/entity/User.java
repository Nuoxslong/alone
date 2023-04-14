package cn.codegraffiti.alone.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private Long id;

    private String username;

    private String password;

}
