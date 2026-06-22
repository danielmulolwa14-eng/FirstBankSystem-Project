/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vu.group.firstbanksystem;

public abstract class Account {
    protected String accountNumber;
    protected String clientName;
    protected String branch;
    protected String phoneNumber;
    protected String email;
    protected String dateOfBirth;
    protected double openingDeposit;

    public Account(String clientName, String branch, String phoneNumber, String email, String dateOfBirth, double openingDeposit) {
        this.clientName = clientName;
        this.branch = branch;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.openingDeposit = openingDeposit;
    }

    // Méthode abstraite exigée par le sujet du cours
    public abstract double minimumDeposit();

    // Getters et Setters pour manipuler les données
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getClientName() { return clientName; }
    public String getBranch() { return branch; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getDateOfBirth() { return dateOfBirth; }
    public double getOpeningDeposit() { return openingDeposit; }
    public void setOpeningDeposit(double openingDeposit) {
    this.openingDeposit = openingDeposit;
}
}
