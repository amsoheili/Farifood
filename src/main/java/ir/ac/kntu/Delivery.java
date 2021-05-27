package ir.ac.kntu;

import java.util.ArrayList;

public class Delivery {

    private String name;
    private TransportationVehicle vehicle;
    private SalaryType salaryType;
    private double salary;
    private ArrayList<Order> orders;
    private int score = 0;
    private int workPlaceNumber=0;



    Delivery(String name,TransportationVehicle vehicle,SalaryType salaryType,double salary){
        this.name = name;
        this.vehicle = vehicle;
        this.salaryType = salaryType;
        this.salary=salary;
        this.orders = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVehicle(TransportationVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWorkPlaceNumber(int workPlaceNumber){
        this.workPlaceNumber = workPlaceNumber;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public TransportationVehicle getVehicle() {
        return vehicle;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public double getSalary() {
        return salary;
    }

    public int getWorkPlaceNumber() {
        return workPlaceNumber;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString(){
        return "Name : " + name +
                "\nVehicle : " + vehicle +
                "\nSalary type : " + salaryType +
                "\nSalary : " + salary +
                "\nScore : " + score;

    }
}
