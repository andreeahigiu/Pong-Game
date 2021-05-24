package dataBase.entityClasses;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class Users:
 * Contine detaliile unui utilizator: id, username, password si scor
 *
 * @author Andreea Higiu
 * @version 1.0
 */


@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "User.findByName",
                query = "SELECT u FROM Users u WHERE u.username LIKE:username")})

public class Users implements Comparable<Users> {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID", nullable = false, length = 6)
    private Integer id;

    private String username;
    private String password;
    private int score;

    public Users() {

    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                '}';
    }


    @Override
    public int compareTo(Users o) {
        Integer score2 = o.getScore();
        Integer score1 = this.score;

        return score2.compareTo(score1);
    }




}
