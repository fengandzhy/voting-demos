package org.frank.vote.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique=true)
    private String name;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(targetEntity=User.class)
    @JoinTable(name="t_user_role",
            joinColumns = @JoinColumn(referencedColumnName="Id",name="role_id"), 
            inverseJoinColumns = @JoinColumn(referencedColumnName="Id",name="user_id"))
    private List<User> users = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
