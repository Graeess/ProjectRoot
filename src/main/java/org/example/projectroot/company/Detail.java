package org.example.projectroot.company;


import lombok.Data;

@Data
public class Detail {
    private String id;
    private String status;

    public Detail(String id) {
        this.id = id;
        this.status = "новая";
    }


    }

