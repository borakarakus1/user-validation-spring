package com.bora.validatetest.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {
    @SequenceGenerator(name = "users_sequence",
                       sequenceName = "user_sequence",
                       allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "users_sequence")
    private int id;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Email(message = "Please Enter a Valid email Address.")
    @Column(name = "email",unique = true)
    private String email;
    @NotNull
    @Length(min = 7,message = "Password should be at least 7 characters long ")
    @Column(name = "password")
    private String password;
    @Column(name = "mobile",unique = true)
    @Length(min = 10,message = "Password should be at least 10 characters long")
    private String mobile;
    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "locked")
    private Boolean locked = false;
    @Column(name = "enabled")
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
