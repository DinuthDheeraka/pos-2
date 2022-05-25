package lk.ijse.pos.view.tdm;

import java.time.LocalDate;

public class ItemTM {
    private String itemCode;
    private String description;
    private String packSize;
    private double unitPrice;
    private double maxDiscount;
    private int qoh;
    private LocalDate addedDate;

    public ItemTM() {
    }

    public ItemTM(String itemCode, String description, String packSize,
                  double unitPrice, double maxDiscount, int qoh, LocalDate addedDate) {
        this.itemCode = itemCode;
        this.description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.maxDiscount = maxDiscount;
        this.qoh = qoh;
        this.addedDate = addedDate;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public int getQoh() {
        return qoh;
    }

    public void setQoh(int qoh) {
        this.qoh = qoh;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }
}
