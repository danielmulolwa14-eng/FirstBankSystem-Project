/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vu.group.firstbanksystem;

public class FixedDepositAccount extends Account {
    public FixedDepositAccount(String name, String branch, String phone, String email, String dob, double dep) {
        super(name, branch, phone, email, dob, dep);
    }
    
    @Override
    public double minimumDeposit() { 
        return 200000.0; // Le montant spécifique demandé par le sujet
    }
}
