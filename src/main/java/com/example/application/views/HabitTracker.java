package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "Habit Tracker", layout = MainLayout.class) // <1>
@PageTitle("Dashboard | Habit Tracker")


public class HabitTracker extends VerticalLayout
{
   public HabitTracker(){
      add(new H3("This program will help you track daily habits to determine how well you are embracing change you are embracing change."));
      add(new H2("Habit List: "));
}
}