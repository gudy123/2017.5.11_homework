package com.example.a2017511_homework;

/**
 * Created by lenovo on 2017/5/11.
 */

public class Item  {
    private String id;
    private String name;
    private String summary;
    private String photo;

    public Item(String id,String name, String summary , String photo){
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSummary() {
        return summary;
    }

}

