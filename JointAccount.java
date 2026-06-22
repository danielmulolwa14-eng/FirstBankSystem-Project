/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vu.group.firstbanksystem;

public class JointAccount extends Account {
    private String secondClientName;

    public JointAccount(String name, String branch, String phone, String email, String dob, double dep, String secondName) {
        super(name, branch, phone, email, dob, dep);
        this.secondClientName = secondName;
    }

    @Override
    public double minimumDeposit() { 
        return 75000.0; 
    }

    public String getSecondClientName() { return secondClientName; }
}


