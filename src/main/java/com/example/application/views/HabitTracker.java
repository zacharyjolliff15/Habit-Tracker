package com.example.application.views;


import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.io.*;
import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "Habit Tracker", layout = MainLayout.class) // <1>
@PageTitle("Dashboard | Habit Tracker")


public class HabitTracker extends VerticalLayout
{
static String arr[] = new String[4];
static int option[] = new int[2];
  
static String habit_list(int idx){
arr[0] = "1. go to the bed on time";
arr[1] = "2. eat healthy";
arr[2] = "3. celebrate something I did well";
arr[3] = "4. show someone appreciation";
return arr[idx];
}
  
static void Starter(int idx){
System.out.println("This program will help you track");
System.out.println("daily habits to determine how well you are embracing change");
System.out.println("you are embracing change.");
  
System.out.println("HABIT LIST :");
  
System.out.println(habit_list(0));
  
System.out.println(habit_list(1));
  
System.out.println(habit_list(2));
  
System.out.println(habit_list(3));
  
Scanner sc = new Scanner(System.in);
  
System.out.println("Which habit do you want to track ");
  
int inp = sc.nextInt();
option[idx] = inp-1;
System.out.println("Tracking for Habit "+ '"' + habit_list(inp-1) + '"');
}
  
static double input(){

Scanner sc = new Scanner(System.in);

System.out.println(" How many days do you want to track this habit?");
  
int cnt = 0;
  
int n = sc.nextInt(); sc.nextLine();
  
for(int i =0;i<n;i++){
System.out.println(" Did you practice the habit on day " + (i+1) +"?");
String ans = sc.nextLine();
if(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y"))
cnt = cnt + 1;
}
  
double op = (double) Math.round(cnt*100.0) / n;
op=Math.round(op*Math.pow(10,2))/Math.pow(10,2);
System.out.println(" ~~ Success = " + cnt + "/" + n + " days ~~");
System.out.println(" ~~~~"+ op + "% success! ~~~~");
return op;
}
  
  
  
  
static void result(double a , double b){
System.out.println("ANALYSIS:");
if(a ==100.00 && b == 100.00){
System.out.println("WAY TO GO! You're a change maker!");
}
  
if(a==b){
System.out.println("You were equally successfull with both the habit");
}
  
else if(a>b){
System.out.println("You were better with habit "+'"'+habit_list(option[0])+'"');
}
  
else{
System.out.println("You were better with habit "+'"'+habit_list(option[1])+'"');
}
}
  
   public static void main (String[] args) throws java.lang.Exception
   {
   Starter(0);
   double res1 = input();
   Starter(1);
   double res2 = input();
   result(res1 , res2);
   }
}