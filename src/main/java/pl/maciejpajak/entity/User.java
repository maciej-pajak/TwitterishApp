package pl.maciejpajak.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @Email
    @NotBlank
    private String email;
    
    @NotNull        // TODO check
    private boolean enabled;
    
    @OneToMany(mappedBy = "user")
    private Collection<Tweet> tweets;
    
    @OneToMany(mappedBy = "user")
    private Collection<Comment> comments;
    
    public User() {}

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }
    
    public String getPassword() {
        return null;
    }

    public User setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public Collection<Tweet> getTweets() {
        return tweets;
    }

    public User setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
        return this;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public User setComments(Collection<Comment> comments) {
        this.comments = comments;
        return this;
    }
    
}
