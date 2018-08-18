package com.net.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "user_dtl")
public class User {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "createts")
    private Timestamp createts;

    /*@Lob
    @Column(name = "xml")
    private byte[] xmlBytes;*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setCreatets(Timestamp createts) {
        this.createts = createts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Timestamp getCreatets() {
        return createts;
    }

}
