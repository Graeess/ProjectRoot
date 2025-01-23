package org.example.projectroot.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {


        ExcelReader excelReader = new ExcelReader();
        List<String> inputFiles = new ArrayList<>();
        inputFiles.add("input.xlsx");
        inputFiles.add("input1.xlsx");
        excelReader.readMultipleFiles(inputFiles);
        excelReader.readExcelFile("input.xlsx");
        excelReader.readExcelFile("input1.xlsx");


        List<ProductionCenter> centers = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();
        List<Detail> details = new ArrayList<>();

        centers.add(new ProductionCenter("Производственный центр №1", 2, 5.0));
        centers.add(new ProductionCenter("Производственный центр №2", 3, 3.0));
        workers.add(new Worker("Рабочий 1"));
        workers.add(new Worker("Рабочий 2"));
        details.add(new Detail("Деталь 1"));

        Simulation simulation = new Simulation(centers, workers, details);

        for (Detail detail : details) {
            simulation.addDetail(detail);
        }

        for (int i = 0; i < 10; i++) {
            simulation.simulateStep();
            simulation.writeResultsToCSV("output/output.csv");
        }

        for (int i = 0; i < 10; i++) {
            simulation.simulateStep();
            simulation.writeResultsToCSV("output/output1.csv");
        }
    }
}
