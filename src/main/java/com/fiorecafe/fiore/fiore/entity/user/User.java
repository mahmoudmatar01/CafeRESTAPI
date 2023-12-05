package com.fiorecafe.fiore.fiore.entity.user;
import com.fiorecafe.fiore.fiore.enums.Gender;
import com.fiorecafe.fiore.fiore.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 7, message = "Password must be at least 7 characters long")
    @Pattern.List({
            @Pattern(regexp = ".*[a-zA-Z].*", message = "Password must contain at least one letter"),
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit"),
            @Pattern(regexp = ".*[!@#$%^&*()-+=].*", message = "Password must contain at least one special character")
    })
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne()
    @JoinColumn(name = "user_image")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserImage userImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Map<String, Object> getClaims() {
        return Map.of(
                "id", getUserId(),
                "name", getName(),
                "email", getEmail(),
                "gender", getGender()
        );
    }
}