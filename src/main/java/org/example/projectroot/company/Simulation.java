package org.example.projectroot.company;


import lombok.Data;

import java.io.File;

import java.io.IOException;
import java.util.List;

@Data
public class Simulation {

    private List<ProductionCenter> centers;
    private List<Worker> workers;
    private List<Detail> details;
    private double time;

    public Simulation(List<ProductionCenter> centers, List<Worker> workers, List<Detail> details) {
        this.centers = centers;
        this.workers = workers;
        this.details = details;
        this.time = 0.0;
    }

    public void simulateStep() {
        // Распределяем работников после каждого шага симуляции
        distributeWorkers();


        for (ProductionCenter center : centers) {
            center.processDetails();
        }


        time += 1.0;
    }

    public void distributeWorkers() {

        int remainingWorkers = workers.size();
        for (ProductionCenter center : centers) {
            if (center.getBufferCount() > 0 && remainingWorkers > 0) {
                int workersToAssign = Math.min(center.getMaxWorkers(), remainingWorkers);
                center.setMaxWorkers(workersToAssign);
                remainingWorkers -= workersToAssign;
            }
        }
    }

    public void addDetail(Detail detail) {

        if (!centers.isEmpty()) {
            centers.get(0).addDetail(detail);
        }
    }

    public void writeResultsToCSV(String filename) throws IOException {
        String directoryPath = "target/";


        File directory = new File(directoryPath);
        if (!directory.exists()) {

            throw new IOException("Директория не существует: " + directoryPath);
        }


        File file = new File(directoryPath + filename);


        CsvWriter csvWriter = new CsvWriter();


        StringBuilder data = new StringBuilder("Time, ProductionCenter, WorkersCount, BufferCount\n");
        for (ProductionCenter center : centers) {
            data.append(time)
                    .append(", ").append(center.getName())
                    .append(", ").append(center.getMaxWorkers())
                    .append(", ").append(center.getBufferCount())
                    .append("\n");
        }

        csvWriter.writeCsv(file.getAbsolutePath(), data.toString());
    }
}