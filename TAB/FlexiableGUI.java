package com.example.flexiblegui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FlexibleGUIApp extends Application {

    private VBox mainLayout;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flexible GUI");

        // Create the main layout
        mainLayout = new VBox(10);
        mainLayout.setStyle("-fx-padding: 10;");

        // Create a toolbar
        ToolBar toolBar = createToolBar();

        // Create a scrollable content area
        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);

        // Create the root layout
        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button addTextFieldBtn = new Button("Add TextField");
        addTextFieldBtn.setOnAction(e -> addTextField());

        Button addLabelBtn = new Button("Add Label");
        addLabelBtn.setOnAction(e -> addLabel());

        Button addButtonBtn = new Button("Add Button");
        addButtonBtn.setOnAction(e -> addButton());

        toolBar.getItems().addAll(addTextFieldBtn, addLabelBtn, addButtonBtn);

        return toolBar;
    }

    private void addTextField() {
        TextField textField = new TextField();
        addComponentWithRemoveButton(textField);
    }

    private void addLabel() {
        Label label = new Label("New Label");
        addComponentWithRemoveButton(label);
    }

    private void addButton() {
        Button button = new Button("New Button");
        addComponentWithRemoveButton(button);
    }

    private void addComponentWithRemoveButton(javafx.scene.Node component) {
        HBox hbox = new HBox(10);
        Button removeBtn = new Button("Remove");
        removeBtn.setOnAction(e -> mainLayout.getChildren().remove(hbox));

        hbox.getChildren().addAll(component, removeBtn);
        mainLayout.getChildren().add(hbox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}