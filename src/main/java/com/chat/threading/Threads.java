package com.chat.threading;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Threads {
    private String file1 = "MOCK_DATA1.csv";
    private String file2 = "MOCK_DATA2.csv";
    private String file3 = "MOCK_DATA3.csv";

    public boolean isThread1Running = true;
    public boolean isThread2Running = true;
    public boolean isThread3Running = true;
    public boolean finishedCheck = false;

    private Controller controller;

    private List<Data> dataList = new ArrayList<>();

    public Threads(Controller controller) {
        this.controller = controller;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void readData() {
        Thread thread1 = new Thread(() -> {
            System.out.println("Started reading data on thread 1");

            try (Reader in = new FileReader("src/main/java/DataFiles/" + file1)) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                for(CSVRecord record : records) {
                    isThread1Running = true;
                    Data data = new Data();
                    data.setId(Integer.parseInt(record.get(0).trim()));
                    data.setFirst_name(record.get(1).trim());
                    data.setLast_name(record.get(2).trim());
                    data.setEmail(record.get(3).trim());
                    data.setGender(record.get(4).trim());
                    data.setCountry(record.get(5).trim());
                    data.setDomain(record.get(6).trim());
                    data.setBirth_date(record.get(7).trim());
                    dataList.add(data);
                    controller.setData(dataList);

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("In Thread 1: " + data.getId() + ", " + data.getFirst_name() + ", " + data.getLast_name() + ", " + data.getEmail() + ", " + data.getGender() + ", " + data.getCountry() + ", " + data.getDomain() + ", " + data.getBirth_date());
                }

                isThread1Running = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread 1 has finished reading data");

        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Started reading data on thread 2");

            try (Reader in = new FileReader("src/main/java/DataFiles/" + file2)) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                for(CSVRecord record : records) {
                    isThread2Running = true;
                    Data data = new Data();
                    data.setId(Integer.parseInt(record.get(0).trim()));
                    data.setFirst_name(record.get(1).trim());
                    data.setLast_name(record.get(2).trim());
                    data.setEmail(record.get(3).trim());
                    data.setGender(record.get(4).trim());
                    data.setCountry(record.get(5).trim());
                    data.setDomain(record.get(6).trim());
                    data.setBirth_date(record.get(7).trim());
                    dataList.add(data);
                    controller.setData(dataList);

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("In Thread 2: " + data.getId() + ", " + data.getFirst_name() + ", " + data.getLast_name() + ", " + data.getEmail() + ", " + data.getGender() + ", " + data.getCountry() + ", " + data.getDomain() + ", " + data.getBirth_date());
                }

                isThread2Running = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread 2 has finished reading data");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("Started reading data on thread 3");

            try (Reader in = new FileReader("src/main/java/DataFiles/" + file3)) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                for(CSVRecord record : records) {
                    isThread3Running = true;
                    Data data = new Data();
                    data.setId(Integer.parseInt(record.get(0).trim()));
                    data.setFirst_name(record.get(1).trim());
                    data.setLast_name(record.get(2).trim());
                    data.setEmail(record.get(3).trim());
                    data.setGender(record.get(4).trim());
                    data.setCountry(record.get(5).trim());
                    data.setDomain(record.get(6).trim());
                    data.setBirth_date(record.get(7).trim());
                    dataList.add(data);
                    controller.setData(dataList);

                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("In Thread 3: " + data.getId() + ", " + data.getFirst_name() + ", " + data.getLast_name() + ", " + data.getEmail() + ", " + data.getGender() + ", " + data.getCountry() + ", " + data.getDomain() + ", " + data.getBirth_date());
                }

                isThread3Running = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread 3 has finished reading data");
        });

        Thread updatingThread = new Thread(() -> {
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Updating data in tableView.");
                if (isThread1Running || isThread2Running || isThread3Running) {
                    controller.updateTable();
                } else if (!finishedCheck) {
                    controller.updateTable();
                    finishedCheck = true;
                }
            } while (!finishedCheck);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        updatingThread.start();
    }
}
