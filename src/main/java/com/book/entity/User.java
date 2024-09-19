package com.book.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Implement UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // If you don't have any roles or authorities, return an empty list
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
    	System.out.println("------------------Hit here");
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Or add your logic for expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Or add your logic for account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Or add your logic for credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true;  // Or add your logic for enabling/disabling accounts
    }

    // Getters and Setters for the fields
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }


    // Other fields' getters and setters
}
