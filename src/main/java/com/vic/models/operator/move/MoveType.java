package com.vic.models.operator.move;

import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_ADVANCED;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_ADVANCED_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_LEFT;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_LEFT_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_QUIT;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_QUIT_DESC;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_RIGHT;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_RIGHT_DESC;

public enum MoveType {
    LEFT(MOVE_INDICATE_LEFT_DESC, MOVE_INDICATE_LEFT),
    RIGHT(MOVE_INDICATE_RIGHT_DESC, MOVE_INDICATE_RIGHT),
    ADVANCE(MOVE_INDICATE_ADVANCED_DESC, MOVE_INDICATE_ADVANCED),
    QUIT(MOVE_INDICATE_QUIT_DESC, MOVE_INDICATE_QUIT);

    private final String description;
    private final String shortName;

    MoveType(String description, String shortName) {
        this.description = description;
        this.shortName = shortName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getShortName() {
        return this.shortName;
    }
}
