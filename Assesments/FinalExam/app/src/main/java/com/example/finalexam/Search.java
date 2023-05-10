package com.example.finalexam;

import java.util.ArrayList;
import java.util.Date;

public class Search {
    public int total;
    public int total_pages;
    public ArrayList<Result> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    private class ProfileImage {
        public String small;
        public String medium;

        public ProfileImage(String small, String medium) {
            this.small = small;
            this.medium = medium;
        }

        public ProfileImage() {
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        @Override
        public String toString() {
            return "ProfileImage{" +
                    "small='" + small + '\'' +
                    ", medium='" + medium + '\'' +
                    '}';
        }
    }

    private class Result {
        public String id;
        public Date created_at;
        public Date promoted_at;
        public String description;
        public Urls urls;
        public User user;

        public Result(String id, Date created_at, Date promoted_at, String description, Urls urls, User user) {
            this.id = id;
            this.created_at = created_at;
            this.promoted_at = promoted_at;
            this.description = description;
            this.urls = urls;
            this.user = user;
        }

        public Result() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Date created_at) {
            this.created_at = created_at;
        }

        public Date getPromoted_at() {
            return promoted_at;
        }

        public void setPromoted_at(Date promoted_at) {
            this.promoted_at = promoted_at;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", created_at=" + created_at +
                    ", promoted_at=" + promoted_at +
                    ", description='" + description + '\'' +
                    ", urls=" + urls +
                    ", user=" + user +
                    '}';
        }
    }


    private class Urls {
        public String raw;
        public String full;
        public String regular;

        public Urls() {
        }

        public Urls(String raw, String full, String regular) {
            this.raw = raw;
            this.full = full;
            this.regular = regular;
        }

        public String getRaw() {
            return raw;
        }

        public void setRaw(String raw) {
            this.raw = raw;
        }

        public String getFull() {
            return full;
        }

        public void setFull(String full) {
            this.full = full;
        }

        public String getRegular() {
            return regular;
        }

        public void setRegular(String regular) {
            this.regular = regular;
        }

        @Override
        public String toString() {
            return "Urls{" +
                    "raw='" + raw + '\'' +
                    ", full='" + full + '\'' +
                    ", regular='" + regular + '\'' +
                    '}';
        }
    }

    private class User {
        public String id;
        public String name;
        public String first_name;
        public String last_name;
        public Object twitter_username;
        public Object portfolio_url;
        public Object bio;
        public String location;
        public ProfileImage profile_image;

        public User() {
        }

        public User(String id, String name, String first_name, String last_name, Object twitter_username, Object portfolio_url, Object bio, String location, ProfileImage profile_image) {
            this.id = id;
            this.name = name;
            this.first_name = first_name;
            this.last_name = last_name;
            this.twitter_username = twitter_username;
            this.portfolio_url = portfolio_url;
            this.bio = bio;
            this.location = location;
            this.profile_image = profile_image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public Object getTwitter_username() {
            return twitter_username;
        }

        public void setTwitter_username(Object twitter_username) {
            this.twitter_username = twitter_username;
        }

        public Object getPortfolio_url() {
            return portfolio_url;
        }

        public void setPortfolio_url(Object portfolio_url) {
            this.portfolio_url = portfolio_url;
        }

        public Object getBio() {
            return bio;
        }

        public void setBio(Object bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public ProfileImage getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(ProfileImage profile_image) {
            this.profile_image = profile_image;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", first_name='" + first_name + '\'' +
                    ", last_name='" + last_name + '\'' +
                    ", twitter_username=" + twitter_username +
                    ", portfolio_url=" + portfolio_url +
                    ", bio=" + bio +
                    ", location='" + location + '\'' +
                    ", profile_image=" + profile_image +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Search{" +
                "total=" + total +
                ", total_pages=" + total_pages +
                ", results=" + results +
                '}';
    }
}