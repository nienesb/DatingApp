package com.teamdating.datingapp.Models;

import java.util.Date;

/**
 * Created by Janine on 19-4-2017.
 */

public class Manager {

    private int id;
    private Date created;
    private String name;
    private Date deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", created = " + created + ", name = " + name + ", deleted = " + deleted + "]";
    }

}
