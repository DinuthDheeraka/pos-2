/**
 * @author : Dinuth Dheeraka
 * Project Name: POS
 * Date        : 5/29/2022
 * Time        : 11:24 PM
 */
package lk.ijse.pos.view.jaspertm;

public class IncomeJTM {
    String month;
    double income;
    double discount;

    public IncomeJTM(String month, double income, double discount) {
        this.month = month;
        this.income = income;
        this.discount = discount;
    }
}
