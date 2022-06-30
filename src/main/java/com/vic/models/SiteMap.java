package com.vic.models;

import com.vic.models.squares.Square;
import com.vic.models.squares.SquareType;

import java.util.List;

import static com.vic.constants.ErrorMessage.ILLEGAL_SITEMAP;
import static com.vic.constants.OperatorInteraction.NEW_LINE_STRING;

public class SiteMap {
    private final Square[][] mapArray;
    private int unReachableSquareCount = 0;

    public SiteMap(List<String> lines) {
        if (lines != null && lines.size() > 0) {
            this.mapArray = new Square[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                String eachLine = lines.get(i);
                for (int j = 0; j < eachLine.length(); j++) {
                    this.mapArray[i][j] = Square.getInstance(SquareType.valueOf(String.valueOf(eachLine.charAt(j))));
                    if (this.mapArray[i][j].isProtectedTree()) {
                        this.unReachableSquareCount++;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException(ILLEGAL_SITEMAP);
        }
    }

    public String getSiteMapString() {
        StringBuilder sb = new StringBuilder();
        for (Square[] squareLine : mapArray) {
            for (Square eachSquare : squareLine) {
                sb.append(eachSquare.getSquareType());
            }
            sb.append(NEW_LINE_STRING);
        }
        return sb.toString();
    }

    public int getSiteMapRowSize() {
        return this.mapArray.length;
    }

    public int getSiteMapColumnSize() {
        return this.mapArray[0].length;
    }

    public Square getSquare(int x, int y) {
        if (x >= 0 && x < this.mapArray.length && y >= 0 && y < this.mapArray[0].length) {
            return this.mapArray[x][y];
        }
        return null;
    }

    public int getUnreachableSquareCount() {
        return this.unReachableSquareCount;
    }
}
