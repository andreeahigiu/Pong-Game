package dataBase.entityClasses;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "User.findByName",
                query = "SELECT u FROM Users u WHERE u.username LIKE:username")})

public class Users {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID", nullable = false, length = 6)
    private Integer id;

    private String username;
    private String password;

    public Users(){

    }

    public Users(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
