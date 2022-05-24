package com.ibrahim.voledemo.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashMap;
import java.util.List;

@Document(indexName = "twitter_message")
public class User {

   @Id
   private String id;

   @Field(name="x-username",type = FieldType.Text)
   private String userName;

   @Field(name = "message",type = FieldType.Text)
   private String message;

   @Field(name = "hashtag",type = FieldType.Object)
   private List<HashMap> nested;

   public User(String id, String userName, String message) {
      this.id = id;
      this.userName = userName;
      this.message = message;

   }

   public User(String id, String userName, String message, List<HashMap> nested) {
      this.id = id;
      this.userName = userName;
      this.message = message;
      this.nested = nested;
   }

   public List<HashMap> getNested() {
      return nested;
   }

   public void setNested(List<HashMap> nested) {
      this.nested = nested;
   }

   public User() {
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }


}
