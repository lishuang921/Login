package com.example.login.bean;

import java.util.ArrayList;
import java.util.List;

public class ArticlesData {
    private int curPage;

    private ArrayList<Articles> datas;


    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public ArrayList<Articles> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Articles> datas) {
        this.datas = datas;
    }

    public static class Articles{
        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private String publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<Tag> tags;
        public class Tag{
            private String name;
            private String url;

        }

        public Articles(String apkLink, String author, int chapterId, String chapterName, boolean collect, int courseId, String desc, String envelopePic, boolean fresh, int id, String link, String niceDate, String origin, String prefix, String projectLink, String publishTime, int superChapterId, String superChapterName, String title, int type, int userId, int visible, int zan, List<Tag> tags) {
            this.apkLink = apkLink;
            this.author = author;
            this.chapterId = chapterId;
            this.chapterName = chapterName;
            this.collect = collect;
            this.courseId = courseId;
            this.desc = desc;
            this.envelopePic = envelopePic;
            this.fresh = fresh;
            this.id = id;
            this.link = link;
            this.niceDate = niceDate;
            this.origin = origin;
            this.prefix = prefix;
            this.projectLink = projectLink;
            this.publishTime = publishTime;
            this.superChapterId = superChapterId;
            this.superChapterName = superChapterName;
            this.title = title;
            this.type = type;
            this.userId = userId;
            this.visible = visible;
            this.zan = zan;
            this.tags = tags;
        }

        public String getApkLink() {
            return apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public String getDesc() {
            return desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public int getId() {
            return id;
        }

        public String getLink() {
            return link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public int getUserId() {
            return userId;
        }

        public int getVisible() {
            return visible;
        }

        public int getZan() {
            return zan;
        }

        public List<Tag> getTags() {
            return tags;
        }
    }


    /*  "apkLink": "",
             "author": "鸿洋",
             "chapterId": 408,
             "chapterName": "鸿洋",
             "collect": false,
             "courseId": 13,
             "desc": "",
             "envelopePic": "",
             "fresh": false,
             "id": 8526,
             "link": "https://mp.weixin.qq.com/s/UYOrokX3qjZiUnX9FlPo3g",
             "niceDate": "2019-05-22",
             "origin": "",
             "prefix": "",
             "projectLink": "",
             "publishTime": 1558454400000,
             "superChapterId": 408,
             "superChapterName": "公众号",
             "tags": [
    {
        "name": "公众号",
            "url": "/wxarticle/list/408/1"
    }
                ],
                        "title": "RecylcerView 面试十九问",
                        "type": 0,
                        "userId": -1,
                        "visible": 1,
                        "zan": 0*/





}


