package edu.uncc.hw06;

public class Forum {
    String title;
    String description;
    String createdBy;
    String createdAt;
    String id;

    public Forum() {
    }

    public Forum(String title, String description, String createdBy, String createdAt, String id) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.id = id;
    }

    public Forum(String title, String desc, String uid) {
        this.title = title;
        this.description = desc;
        this.createdBy = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
