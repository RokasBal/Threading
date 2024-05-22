package com.chat.threading;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private Button importButton;
    @FXML
    private Button filterButton;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<Data> dataTable;

    List<Data> dataList = new ArrayList<>();
    Threads threads;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Data, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Data, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));

        TableColumn<Data, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        TableColumn<Data, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Data, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Data, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Data, String> domainColumn = new TableColumn<>("Domain");
        domainColumn.setCellValueFactory(new PropertyValueFactory<>("domain"));

        TableColumn<Data, String> birthDateColumn = new TableColumn<>("Birth Date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        dataTable.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, emailColumn, genderColumn, countryColumn, domainColumn, birthDateColumn);
        dataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idSortColumnProperty(idColumn);
        sortColumnProperty(firstNameColumn, Comparator.comparing(Data::getFirst_name));
        sortColumnProperty(lastNameColumn, Comparator.comparing(Data::getLast_name));
        sortColumnProperty(emailColumn, Comparator.comparing(Data::getEmail));
        sortColumnProperty(genderColumn, Comparator.comparing(Data::getGender));
        sortColumnProperty(countryColumn, Comparator.comparing(Data::getCountry));
        sortColumnProperty(domainColumn, Comparator.comparing(Data::getDomain));
        sortColumnProperty(birthDateColumn, Comparator.comparing(Data::getBirth_date));

        importButton.setOnAction(this::loadData);
        filterButton.setOnAction(this::filter);
    }

    public void setData(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void updateTable() {
        dataTable.getItems().clear();
        dataTable.getItems().addAll(dataList);
    }

    protected void loadData(ActionEvent actionEvent) {
        threads = new Threads(this);
        threads.readData();
    }

    protected void filter(ActionEvent actionEvent) {
        dataList = threads.getDataList();
        dataTable.getItems().clear();
        for (Data data : dataList) {
            if (data.getBirth_date().compareTo(startDatePicker.getValue().toString()) >= 0 && data.getBirth_date().compareTo(endDatePicker.getValue().toString()) <= 0) {
                dataTable.getItems().add(data);
            }
        }
    }

    private void sortColumnProperty(TableColumn<Data, String> column, Comparator<Data> comparing) {
        column.sortTypeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == TableColumn.SortType.ASCENDING) {
                List<Data> listSorted = dataTable.getItems().stream().sorted(comparing).toList();
                dataTable.getItems().clear();
                dataTable.getItems().addAll(listSorted);
            } else if (newValue == TableColumn.SortType.DESCENDING) {
                List<Data> listSorted = dataTable.getItems().stream().sorted(comparing).collect(Collectors.toList());
                Collections.reverse(listSorted);
                dataTable.getItems().clear();
                dataTable.getItems().addAll(listSorted);
            } else {
                List<Data> listSorted = dataTable.getItems().stream().sorted(comparing).toList();
                dataTable.getItems().clear();
                dataTable.getItems().addAll(listSorted);
            }
        });
    }

    private void idSortColumnProperty(TableColumn<Data, Integer> column) {
        column.sortTypeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == TableColumn.SortType.ASCENDING) {
                List<Data> listSorted = dataTable.getItems().stream().sorted(Comparator.comparingInt(Data::getId)).toList();
                dataTable.getItems().clear();
                dataTable.getItems().addAll(listSorted);
            } else if (newValue == TableColumn.SortType.DESCENDING) {
                List<Data> listSorted = dataTable.getItems().stream().sorted(Comparator.comparingInt(Data::getId)).collect(Collectors.toList());
                Collections.reverse(listSorted);
                dataTable.getItems().clear();
                dataTable.getItems().addAll(listSorted);
            } else {
                List<Data> listSorted = dataTable.getItems().stream().sorted(Comparator.comparingInt(Data::getId)).toList();
                dataTable.getItems().clear();
                dataTable.getItems().addAll(listSorted);
            }
        });
    }
}