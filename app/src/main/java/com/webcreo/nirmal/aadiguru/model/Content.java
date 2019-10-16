package com.webcreo.nirmal.aadiguru.model;

import com.webcreo.nirmal.aadiguru.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Content implements Serializable {

    private String id;
    private String url;
    private String name;
    private String description;
    private String value;
    private String imageUrl;
    private int image;

    // Google Console APIs developer key
    // Replace this key with your's
    public static final String DEVELOPER_KEY = "AIzaSyBUmwNSs7SgNza-7NymNfDscqUBkVwUlrU";

    // YouTube video id
    public static final String YOUTUBE_VIDEO_CODE = "CIAWD1oeINM";

    public List<Content> getSliders() {
        List<Content> dummyContents = new ArrayList<>();
        dummyContents.add(new Content(R.drawable.slider1));
        dummyContents.add(new Content(R.drawable.slider2));
        dummyContents.add(new Content(R.drawable.slider3));
        dummyContents.add(new Content(R.drawable.slider4));
        dummyContents.add(new Content(R.drawable.slider5));
        dummyContents.add(new Content(R.drawable.slider6));
        return dummyContents;
    }

    public List<Content> getVideos()
    {
        List<Content> dummyContents = new ArrayList<>();

        dummyContents.add(new Content("CIAWD1oeINM"));
        dummyContents.add(new Content("1Xm5qjqwLx4"));
        //dummyContents.add(new Content("dYnNQ5rLRUU"));
        //dummyContents.add(new Content("dYnNQ5rLRUU"));
        dummyContents.add(new Content("6qishTyIh8o"));

        dummyContents.add(new Content("ccjk-WgzwtQ"));
        //dummyContents.add(new Content("l7wTBvj4V0I"));
        dummyContents.add(new Content("bGUZV5okphw"));
        dummyContents.add(new Content("iaRAWUi-t1U"));
        dummyContents.add(new Content("7_t2-5OWjsg"));
        dummyContents.add(new Content("Do9bjodw7GE"));
        dummyContents.add(new Content("k4OvQofbbe0"));
        dummyContents.add(new Content("UBnkFbGgeCs"));

        dummyContents.add(new Content("DhCENTVsV6o"));
        dummyContents.add(new Content("5PfAZu3vjg4"));
        dummyContents.add(new Content("9ypCqhBan9M"));
        dummyContents.add(new Content("nYF7NjrW3F0"));
        dummyContents.add(new Content("u32hPuWNGlA"));
        dummyContents.add(new Content("4deEik9LOjc"));
        dummyContents.add(new Content("PHjhZcB0Dkg"));
        dummyContents.add(new Content("CIAWD1oeINM"));
        return dummyContents;
    }

    public List<Content> getImages()
    {
        List<Content> dummyContents = new ArrayList<>();
        dummyContents.add(new Content(R.drawable.img_1));
        dummyContents.add(new Content(R.drawable.img_2));
        dummyContents.add(new Content(R.drawable.img_3));
        dummyContents.add(new Content(R.drawable.img_4));
        dummyContents.add(new Content(R.drawable.img_5));
        dummyContents.add(new Content(R.drawable.img_6));
        dummyContents.add(new Content(R.drawable.img_7));
        dummyContents.add(new Content(R.drawable.img_8));
        dummyContents.add(new Content(R.drawable.img_9));
        return dummyContents;
    }

    public List<Content> getNewsPapers()
    {
        List<Content> dummyContents = new ArrayList<>();
        dummyContents.add(new Content(R.drawable.img_news1));
        dummyContents.add(new Content(R.drawable.img_news2));
        return dummyContents;
    }

    public List<Content> getEvents()
    {
        List<Content> dummyContents = new ArrayList<>();
        dummyContents.add(new Content(R.drawable.img_event1));
        dummyContents.add(new Content(R.drawable.img_event2));
        dummyContents.add(new Content(R.drawable.img_event3));
        return dummyContents;
    }

    public Content(int image)
    {
        this.image = image;
    }

    public Content(String name, int image)
    {
        this.name = name;
        this.image = image;
    }

    public Content(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Content()
    {
    }

    public Content(String name)
    {
        this.name = name;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Content> getDummyOptions() {
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}