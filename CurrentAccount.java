/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vu.group.firstbanksystem;

public class CurrentAccount extends Account {
    
    public CurrentAccount(String clientName, String branch, String phoneNumber, String email, String dateOfBirth, double openingDeposit) {
        super(clientName, branch, phoneNumber, email, dateOfBirth, openingDeposit);
    }

    @Override
    public double minimumDeposit() {
        return 200000.0; // 200 000 UGX minimum pour un compte courant
    }
}