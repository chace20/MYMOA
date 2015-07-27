package com.uestc.mymoa.constant;

/**
 * Created by chao on 2015/7/25.
 */
public class Api {
    private static final String BASE = "http://192.168.3.144:3000/api";

    private static final String USERS = "/users";
    private static final String DOC = "/doc";
    private static final String MAIL = "/mail";
    private static final String CONTACT = "/contact";
    private static final String POST = "/post";
    private static final String NEWS = "/news";

    public interface Users {
        String login = BASE + USERS + "/login";
        String addUser = BASE + USERS + "/addUser";
        String query = BASE + USERS + "/query";
        String queryAll = BASE + USERS + "/queryAll";
    }

    public interface News {
        String addNews = BASE + NEWS + "/addNews";
        String addComment = BASE + NEWS + "/addComment";
        String queryCommentList = BASE + NEWS + "/queryCommentList";
        String queryNewsContent = BASE + NEWS + "/queryNewsContent";
        String queryNewsList = BASE + NEWS + "/queryNewsList";
    }

    public interface Post {
        String addPost = BASE + POST + "/addPost";
        String queryPostList = BASE + POST + "/queryPostList";
        String queryPostContent = BASE + POST + "/queryPostContent";
    }

    public interface Doc {
        String addDoc = BASE + DOC + "/addDoc";
        String editDoc = BASE + DOC + "/editDoc";
        String delDoc = BASE + DOC + "/delDoc";
        String queryDocList = BASE + DOC + "/queryDocList";
        String queryDocContent = BASE + DOC + "/queryDocContent";
        String searchDoc = BASE + DOC + "/searchDoc";
    }

    public interface Contact {
        String addContact = BASE + CONTACT + "/addContact";
        String delContact = BASE + CONTACT + "/delContact";
        String queryContactList = BASE + CONTACT + "/queryContactList";
        String queryContactContent = BASE + CONTACT + "/queryContactContent";
        String addContactGroup = BASE + CONTACT + "/addContactGroup";
        String queryGroupList = BASE + CONTACT + "/queryGroupList";
        String queryGroupContactList = BASE + CONTACT + "/queryGroupContactList";
    }

    public interface Mail {
        String sendMail = BASE + MAIL + "/sendMail";
        String queryMailList = BASE + MAIL + "/queryMailList";
        String queryMailContent = BASE + MAIL + "/queryMailContent";
    }
}
