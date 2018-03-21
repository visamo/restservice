package com.fireblend.restservice.entities;

/**
 * Created by cv on 20/03/18.
 */

public class Photos {
    public int albumId;
    public int id;
    public String title;
    public String url;
    public String thumbnailUrl;



    public Photos(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}
