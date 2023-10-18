package ru.kata.spring.bootstrap.demo.model;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.bootstrap.demo.converter.AuthorityDatabaseConverter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    @Pattern(regexp = "[A-z]+",message = "only words")
    private String name;
    @Column(name = "age")
    @Min(value = 0,message = "age must be over 0")
    private int age;
    @NotEmpty
    @Email(message = "write correct email")
    @Column(name = "email")
    private String email;

    @Convert(converter = AuthorityDatabaseConverter.class)
    @Column(name = "authorities")
    private List<Role> authorities;

    @Column(name = "password")
    private String password;

    public User(List<Role> authorities, String username, String password, int age, String email){
        this.authorities = authorities;
        this.name = username;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public User() {

    }


    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Collection<? extends Role> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
}