package com.example.reactive.r2dbc;

 import org.bson.types.ObjectId;
 import org.springframework.data.annotation.Id;
 import org.springframework.data.mongodb.core.mapping.DBRef;
 import org.springframework.data.mongodb.core.mapping.Document;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.util.List;


@Document(collection = "data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class reactiveData {

 @Id
 private ObjectId id;

 private String title;

 private String data;

 private String user;

 public reactiveData(String title, String data, String user) {
     this.title = title;
     this.data = data;
     this.user = user;

 }
 public reactiveData(String title, String data) {
  this.title = title;
  this.data = data;
  this.user = "null";

 }
}
