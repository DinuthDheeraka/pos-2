/**
 * @author : Dinuth Dheeraka
 * Project Name: POS
 * Date        : 5/31/2022
 * Time        : 6:56 PM
 */
package lk.ijse.pos.view.tdm;

public class AnalyzeItemTM {
    private String itemCode;
    private String description;
    private String packSize;
    private double totalSales;

    public AnalyzeItemTM(String itemCode, String description, String packSize, double totalSales) {
        this.itemCode = itemCode;
        this.description = description;
        this.packSize = packSize;
        this.totalSales = totalSales;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}
