/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vu.group.firstbanksystem;

public class SavingsAccount extends Account {
    
    public SavingsAccount(String clientName, String branch, String phoneNumber, String email, String dateOfBirth, double openingDeposit) {
        super(clientName, branch, phoneNumber, email, dateOfBirth, openingDeposit);
    }

    @Override
    public double minimumDeposit() {
        return 100000.0; // 100 000 UGX minimum pour un compte d'épargne
    }
}
