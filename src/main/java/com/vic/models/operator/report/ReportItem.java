package com.vic.models.operator.report;

import com.vic.models.dto.ReportItemDTO;

public class ReportItem {
    private final String description;
    private int quantity;
    private final int unitCost;

    public ReportItem(String description, int unitCost) {
        this.quantity = 0;
        this.unitCost = unitCost;
        this.description = description;
    }

    public ReportItem(int quantity, String description, int unitCost) {
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.description = description;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public ReportItemDTO convertToDTO() {
        return new ReportItemDTO(this.description, this.quantity, this.quantity * this.unitCost);
    }
}
