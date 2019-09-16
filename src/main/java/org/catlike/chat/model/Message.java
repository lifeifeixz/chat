package org.catlike.chat.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Message {
    private String name;
    private Set<String> names;

    private String content;

    private String date;

    private String publisher;

    private Map<String, String> address;

    /** 1:正文;2:上线通知;3:下线通知; */
    private int dbType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public Map<String, String> getAddress() {
        return address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }

    public int getDbType() {
        return dbType;
    }

    public void setDbType(int dbType) {
        this.dbType = dbType;
    }

    @Override
    public String toString() {
        return "Message [name=" + name + ", names=" + names + ", content="
                + content + ", date=" + date + ", publisher=" + publisher
                + ", address=" + address + ", dbType=" + dbType + "]";
    }


}
