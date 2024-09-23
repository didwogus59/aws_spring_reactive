package com.example.reactive.r2dbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("child")
public class reactiveChild {

    @Id
    private ObjectId id;

    private String data;

    private ObjectId parent;

    public reactiveChild(String data, ObjectId parent) {
        this.parent = parent;
        this.data = data;
    }
}
