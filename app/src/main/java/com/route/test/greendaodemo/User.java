package com.route.test.greendaodemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * Created by my301s on 2017/7/17.
 */
@Entity
public class User implements Serializable{
    static final long serialVersionUID = 536871008L;
    @Id
    private Long id;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "chuban")
    private String chuban;
    @Property(nameInDb = "image")
    private String image;
    @Generated(hash = 1580302980)
    public User(Long id, String name, String chuban, String image) {
        this.id = id;
        this.name = name;
        this.chuban = chuban;
        this.image = image;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChuban() {
        return this.chuban;
    }
    public void setChuban(String chuban) {
        this.chuban = chuban;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chuban='" + chuban + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
