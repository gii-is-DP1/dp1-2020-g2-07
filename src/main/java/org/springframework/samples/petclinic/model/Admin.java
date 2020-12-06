package org.springframework.samples.petclinic.model;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
