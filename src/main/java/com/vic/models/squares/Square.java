package com.vic.models.squares;

public interface Square {
    Integer getFirstVisitFuelUsage();

    Integer getVisitedFuelUsage();

    Integer getPassThroughFuelUsage();

    boolean willDamagePaint(boolean isPassThrough);

    boolean isProtectedTree();

    String getSquareType();

    static Square getInstance(SquareType squareType) {
        return switch (squareType) {
            case o -> new PlainSquare();
            case r -> new RockSquare();
            case t -> new TreeSquare();
            case T -> new PreserveSquare();
        };
    }
}
