package com.example.application.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "Habit Tracker", layout = MainLayout.class) // <1>
@PageTitle("Dashboard | Habit Tracker")


public class HabitTracker extends VerticalLayout {

   private Grid<Habit> habitGrid = new Grid<>();
   private TextField habitName = new TextField("Name");
   private TextArea habitDescription = new TextArea("Description");
   private Button addButton = new Button("Add");
   private Label completionCount = new Label();
   private Label successPercentage = new Label();

   private List<Habit> habits = new ArrayList<>();

   public HabitTracker() {
       // Set up the habit grid
       habitGrid.addColumn(Habit::getName).setHeader("Name");
       habitGrid.addColumn(Habit::getDescription).setHeader("Description");
       habitGrid.addColumn(Habit::getCompletionCount).setHeader("Completion Count");
       habitGrid.addColumn(Habit::getSuccessPercentage).setHeader("Success Percentage");

       // Set up the form
       habitName.setRequired(true);
       habitDescription.setRequired(true);

       FormLayout formLayout = new FormLayout();
       formLayout.add(habitName, habitDescription, addButton);

       // Set up the statistics section
       HorizontalLayout statsLayout = new HorizontalLayout();
       statsLayout.add(completionCount, successPercentage);

       // Add everything to the main layout
       add(habitGrid, formLayout, statsLayout);

       // Set up the event listeners
       addButton.addClickListener(event -> addHabit());
       habitGrid.asSingleSelect().addValueChangeListener(event -> updateStatistics());
   }

   private void addHabit() {
       if (habitName.isEmpty() || habitDescription.isEmpty()) {
           Notification.show("Please enter a name and description for the habit");
           return;
       }

       Habit habit = new Habit(habitName.getValue(), habitDescription.getValue());
       habits.add(habit);
       habitGrid.setItems(habits);

       clearForm();
       Notification.show("Habit added successfully");
   }

   private void updateStatistics() {
       Habit selectedHabit = habitGrid.asSingleSelect().getValue();

       if (selectedHabit != null) {
           completionCount.setText("Completion count: " + selectedHabit.getCompletionCount());
           successPercentage.setText("Success percentage: " + selectedHabit.getSuccessPercentage() + "%");
       } else {
           completionCount.setText("");
           successPercentage.setText("");
       }
   }

   private void clearForm() {
       habitName.clear();
       habitDescription.clear();
   }
}