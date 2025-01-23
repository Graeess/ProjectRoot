package org.example.projectroot.company;



import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class ProductionCenter {
    private String name;
    private int maxWorkers;
    private double processingTimePerDetail;
    private int bufferCount;
    private Queue<Detail> buffer;

    public ProductionCenter(String name, int maxWorkers, double processingTimePerDetail) {
        this.name = name;
        this.maxWorkers = maxWorkers;
        this.processingTimePerDetail = processingTimePerDetail;
        this.bufferCount = 0;
        this.buffer = new LinkedList<>();
    }

    public void processDetails() {

        if (maxWorkers > 0 && !buffer.isEmpty()) {
            int detailsProcessed = Math.min(maxWorkers, buffer.size());
            bufferCount -= detailsProcessed;
        }
    }

    public void addDetail(Detail detail) {
        buffer.add(detail);
        bufferCount++;
    }
}
