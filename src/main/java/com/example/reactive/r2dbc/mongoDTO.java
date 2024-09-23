package com.example.reactive.r2dbc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class mongoDTO {

    private String id;
    private String title;

    public mongoDTO (reactiveData data) {
        this.id = data.getId().toString();
        this.title = data.getTitle();
    }
}
