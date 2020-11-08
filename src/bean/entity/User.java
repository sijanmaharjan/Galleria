package bean.entity;

import bean.entity.option.Gender;
import util.DateMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "User")
@NamedQueries({
        @NamedQuery(name = "User.getAll",
                query = "SELECT u FROM User u ORDER BY u.joinDate DESC "),
        @NamedQuery(name = "User.findByUsername",
                query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findById",
                query = "SELECT u FROM User u WHERE u.id = :UID"),
        @NamedQuery(name = "User.block",
                query = "UPDATE User u SET u.blocked=true WHERE u.id = :UID"),
        @NamedQuery(name = "User.allow",
                query = "UPDATE User u SET u.blocked=false WHERE u.id = :UID"),
        @NamedQuery(name = "User.delete",
                query = "DELETE FROM User u WHERE u.id = :UID")
})
public class User implements Serializable {
    @Id
    @Column(name = "UID", length = 40)
    private String id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_date", nullable = false)
    private Date joinDate;
    @Column(name = "profile_picture", length = 32, nullable = false)
    private String profilePicture;
    @Column(name = "first_name", length = 32, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 32, nullable = false)
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "email", length = 64, nullable = false, unique = true)
    private String email;
    @Column(name = "username", length = 32, nullable = false, unique = true)
    private String username;
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    @Column(name = "earned", nullable = false)
    private Double earned = 0.0;
    @Column(name = "blocked", nullable = false)
    private Boolean blocked = false;

    public User() {
    }

    public User(
            String firstName,
            String lastName,
            String birthDate,
            Gender gender,
            String email,
            String username,
            String password
    ) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = DateMapper.toDate(birthDate);
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.joinDate = new Date();
    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getEarned() {
        return earned;
    }

    public void setEarned(Double earned) {
        this.earned = earned;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

}
