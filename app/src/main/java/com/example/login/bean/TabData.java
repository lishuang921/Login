package com.example.login.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TabData {
   /*  "children": [],
             "courseId": 13,
             "id": 408,
             "name": "鸿洋",
             "order": 190000,
             "parentChapterId": 407,
             "userControlSetTop": false,
             "visible": 1*/
   @Id
   private Long lid;

   private int courseId;
   private int id;
   private String name;
   private int order;
   private int parentChapterId;
   private boolean userControlSetTop;
   private int visible;
   @Generated(hash = 891756113)
   public TabData(Long lid, int courseId, int id, String name, int order,
           int parentChapterId, boolean userControlSetTop, int visible) {
       this.lid = lid;
       this.courseId = courseId;
       this.id = id;
       this.name = name;
       this.order = order;
       this.parentChapterId = parentChapterId;
       this.userControlSetTop = userControlSetTop;
       this.visible = visible;
   }
   @Generated(hash = 912424272)
   public TabData() {
   }
   public Long getLid() {
       return this.lid;
   }
   public void setLid(Long lid) {
       this.lid = lid;
   }
   public int getCourseId() {
       return this.courseId;
   }
   public void setCourseId(int courseId) {
       this.courseId = courseId;
   }
   public int getId() {
       return this.id;
   }
   public void setId(int id) {
       this.id = id;
   }
   public String getName() {
       return this.name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public int getOrder() {
       return this.order;
   }
   public void setOrder(int order) {
       this.order = order;
   }
   public int getParentChapterId() {
       return this.parentChapterId;
   }
   public void setParentChapterId(int parentChapterId) {
       this.parentChapterId = parentChapterId;
   }
   public boolean getUserControlSetTop() {
       return this.userControlSetTop;
   }
   public void setUserControlSetTop(boolean userControlSetTop) {
       this.userControlSetTop = userControlSetTop;
   }
   public int getVisible() {
       return this.visible;
   }
   public void setVisible(int visible) {
       this.visible = visible;
   }


}
