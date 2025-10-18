package com.ojas;

import java.util.Date;

public class Task {

    private int id;
    private String description;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Task() {

    }

    public Task(int id, String description, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("""
                             {
                              "id": "%s",
                              "description": "%s",
                              "status": "%s",
                              "createdAt": "%s",
                              "updatedAt": "%s"
                             }""",
                id, description, status, createdAt, updatedAt);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
