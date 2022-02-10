package edu.escuelaing.lab2.data;

import edu.escuelaing.lab2.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
public class User {

    @Id
    private String id ;

    private String name;
    private String lastName;

    @Indexed( unique = true)
    private String email;
    private Date createAt;

    public User() {
    }

    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.createAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
