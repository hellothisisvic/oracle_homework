package com.vic.models.operator.move;

import com.vic.models.dto.MovePathDTO;

public class Move {
    private final MoveType type;
    private final Integer distance;

    public Move(MoveType type, int distance) {
        this.type = type;
        this.distance = distance;
    }

    public Move(MoveType type) {
        this.type = type;
        this.distance = null;
    }

    public MoveType getType() {
        return this.type;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public MovePathDTO convertToMovePathDTO() {
        return new MovePathDTO(this.type.getDescription(), this.distance);
    }
}
