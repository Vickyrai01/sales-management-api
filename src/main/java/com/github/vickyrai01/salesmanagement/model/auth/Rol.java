package com.github.vickyrai01.salesmanagement.model.auth;

import com.github.vickyrai01.salesmanagement.model.enums.RolName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolName rolName;


    @ManyToMany(mappedBy = "roles")
    private List<User> userList;
}
