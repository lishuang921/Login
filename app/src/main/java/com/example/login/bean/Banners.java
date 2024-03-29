package com.example.login.bean;

public class Banners {

            /*"desc": "",
                    "id": 2,
                    "imagePath": "https://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png",
                    "isVisible": 1,
                    "order": 2,
                    "title": "JSON工具",
                    "type": 1,
                    "url": "http://www.wanandroid.com/tools/bejson"*/
            private String desc;
            private int id;
            private String imagePath;
            private int isVisible;
            private int order;
            private String title;
            private int type;
            private String url;

    public Banners(String desc, int id, String imagePath, int isVisible, int order, String title, int type, String url) {
        this.desc = desc;
        this.id = id;
        this.imagePath = imagePath;
        this.isVisible = isVisible;
        this.order = order;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
