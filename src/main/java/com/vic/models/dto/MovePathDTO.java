package com.vic.models.dto;

public class MovePathDTO {
    private final String description;
    private final Integer distance;

    public MovePathDTO(String description, Integer distance) {
        this.description = description;
        this.distance = distance;
    }

    public String getReportFormatString() {
        if (this.distance != null) {
            return this.description + " " + this.distance;
        }
        return this.description;
    }
}
