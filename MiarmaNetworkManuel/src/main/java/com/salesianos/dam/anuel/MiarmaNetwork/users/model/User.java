package com.salesianos.dam.anuel.MiarmaNetwork.users.model;

import com.salesianos.dam.anuel.MiarmaNetwork.post.model.Publicacion;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.UniqueElements;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;



import org.hibernate.annotations.Parameter;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column( updatable = false, nullable = false)
    private UUID id;


    @NaturalId
    @NotBlank
    @Size(max = 15)
    @Column(unique = true, updatable = false)
    private String nick;

    @Email
    @NotBlank
    @Size(max = 40)
    @Column(unique = true, updatable = false)
    private String email;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;


    @NotBlank
    private String avatar;

    private boolean isPublic;

    private LocalDateTime fechaNacimiento;

    @Lob
    private String biografia;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Role roles;

    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Publicacion> publicacionList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "seguimiento",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "following_id"))
    private Set<User> following;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + roles.name()));
    }

    @Override
    public String getUsername() {
        return nick;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }








}
