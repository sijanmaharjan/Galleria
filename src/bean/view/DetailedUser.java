package bean.view;

import bean.entity.option.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "DetailedUser.findByUID",
                query = "SELECT u FROM DetailedUser u WHERE u.id = :UID")
})
public class DetailedUser implements Serializable {
    @Id
    @Column(name = "UID")
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "earned")
    private Double earned;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "total_uploads")
    private Long totalUploads;
    @Column(name = "overall_rating")
    private Float overallRating;
    @Column(name = "likes_collected")
    private Long likesCollected;
    @Column(name = "favourites")
    private Long favourites;
    @Column(name = "redeemed")
    private Double redeemeed;

    public DetailedUser() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getEarned() {
        return earned;
    }

    public void setEarned(Double earned) {
        this.earned = earned;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getTotalUploads() {
        return totalUploads;
    }

    public void setTotalUploads(Long totalUploads) {
        this.totalUploads = totalUploads;
    }

    public Float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Float overallRating) {
        this.overallRating = overallRating;
    }

    public Long getLikesCollected() {
        return likesCollected;
    }

    public void setLikesCollected(Long likesCollected) {
        this.likesCollected = likesCollected;
    }

    public Long getFavourites() {
        return favourites;
    }

    public void setFavourites(Long favourites) {
        this.favourites = favourites;
    }

    public Double getRedeemeed() {
        return redeemeed;
    }

    public void setRedeemeed(Double redeemeed) {
        this.redeemeed = redeemeed;
    }
}
