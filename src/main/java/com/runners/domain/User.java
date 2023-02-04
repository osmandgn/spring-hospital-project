package com.runners.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String firstName;

    @Column(nullable = false,unique = true)
    @NotBlank
    private String userName;

    @Column(nullable = false)
    private String password;

    @JoinTable(name="t_user_role", joinColumns = @JoinColumn(name = "t_user"),
            inverseJoinColumns =@JoinColumn(name = "t_role"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Patient patient;



}