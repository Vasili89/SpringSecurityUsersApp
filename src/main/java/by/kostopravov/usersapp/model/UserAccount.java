package by.kostopravov.usersapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "users", schema = "usersapp", uniqueConstraints={@UniqueConstraint(columnNames = {"user_name"})})
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "User name is empty")
    @Size(min = 3, max = 16, message = "User name should be between 3 and 16 characters")
    @Pattern(regexp = "^[A-Za-z]+$")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @NotEmpty(message = "First name is empty")
    @Size(min = 1, max = 16, message = "First name should be between 1 and 16 characters")
    @Pattern(regexp = "^[A-Za-z]+$")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is empty")
    @Size(min = 1, max = 16, message = "Last name should be between 1 and 16 characters")
    @Pattern(regexp = "^[A-Za-z]+$")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Transient
    @JsonIgnore
    @Size(min = 3, max = 16, message = "Password should be between 3 and 16 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$")
    private String passwordForValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Transient
    public String getPasswordForValid() {
        return passwordForValid;
    }

    public void setPasswordForValid(String passwordForValid) {
        this.passwordForValid = passwordForValid;
    }

    public UserAccount() {
    }

    public UserAccount(String userName, String firstName, String lastName,
                       Role role, Status status, String passwordForValid) {
        this.userName = userName;
        this.password = new BCryptPasswordEncoder(12).encode(passwordForValid);
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
        this.createdAt = LocalDate.now();
        this.passwordForValid = passwordForValid;
    }

    public UserAccount(Long id, String userName, String firstName, String lastName,
                       Role role, Status status, LocalDate createdAt, String passwordForValid) {
        this.id = id;
        this.userName = userName;
        this.password = new BCryptPasswordEncoder(12).encode(passwordForValid);
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.passwordForValid = null;
    }

}
