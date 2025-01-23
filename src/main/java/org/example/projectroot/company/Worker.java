package org.example.projectroot.company;



import lombok.Data;

@Data
public class Worker {
    private String name;

    public Worker(String name) {
        this.name = name;
    }
}
