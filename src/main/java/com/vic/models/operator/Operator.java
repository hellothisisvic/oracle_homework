package com.vic.models.operator;

import com.vic.errors.DestructProtectedTree;
import com.vic.errors.IllegalStartDirection;
import com.vic.errors.OutOfSiteBoundary;
import com.vic.models.SiteMap;
import com.vic.models.dto.MovePathDTO;
import com.vic.models.dto.ReportItemDTO;
import com.vic.models.operator.move.Move;
import com.vic.models.operator.move.MoveType;
import com.vic.models.operator.position.Position;
import com.vic.models.operator.report.Report;
import com.vic.models.squares.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.vic.constants.OperatorInteraction.MOVE_INDICATE;
import static com.vic.constants.OperatorInteraction.MOVE_INDICATE_ADVANCED;
import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_DESTRUCTED_PROTECTED_TREE;
import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_OUT_OF_BOUNDARY;
import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_QUIT;
import static com.vic.constants.OperatorInteraction.SIMULATION_EXIT_BY_WRONG_PARAMETER;
import static com.vic.constants.OperatorInteraction.SPACE_STRING;
import static com.vic.constants.OperatorInteraction.WRONG_INPUT;

public class Operator {
    private final List<Move> moves;
    private final boolean[][] operatorSiteMap;
    private final Report report;
    private final SiteMap siteMap;
    private final Position position;

    public Operator(SiteMap siteMap, int startRow, int startColumn) throws IllegalStartDirection {
        this.moves = new ArrayList<>();
        this.operatorSiteMap = new boolean[siteMap.getSiteMapRowSize()][siteMap.getSiteMapColumnSize()];
        this.report = new Report(siteMap.getSiteMapRowSize() * siteMap.getSiteMapColumnSize() - siteMap.getUnreachableSquareCount());
        this.siteMap = siteMap;
        this.position = new Position(startRow, startColumn, siteMap.getSiteMapRowSize(), siteMap.getSiteMapColumnSize());
    }

    public List<MovePathDTO> getMovePathDTOList() {
        return this.moves.stream()
                .map(Move::convertToMovePathDTO)
                .collect(Collectors.toList());
    }

    public List<ReportItemDTO> getMoveReportItemDTOList() {
        return this.report.getReportItemDTO();
    }

    public String getStartDirection() {
        return this.position.getStartDirection();
    }

    public String getStartFacingDirection() {
        return this.position.getStartFacingDirection();
    }

    public String getStartRowRelativePosition(){
        return this.position.getStartRowRelativePosition();
    }

    public String generateMovePath() {
        boolean isQuit = false;
        String exitReason = SIMULATION_EXIT_BY_QUIT;
        Scanner scan = new Scanner(System.in);

        while (!isQuit) {
            printIndicateToConsole();
            if (scan.hasNextLine()) {
                String userInput = scan.nextLine();
                Move move = convertInputToMove(userInput);
                if (move != null) {
                    moves.add(move);
                    if (move.getType() == MoveType.QUIT) {
                        isQuit = true;
                    } else {
                        try {
                            processMove(move);
                        } catch (OutOfSiteBoundary e) {
                            isQuit = true;
                            exitReason = String.format(SIMULATION_EXIT_BY_OUT_OF_BOUNDARY, e.getRow(), e.getColumn());
                        } catch (DestructProtectedTree e) {
                            isQuit = true;
                            exitReason = String.format(SIMULATION_EXIT_BY_DESTRUCTED_PROTECTED_TREE, e.getRow(), e.getColumn());
                        }

                    }
                } else {
                    printInputErrorToConsole();
                    isQuit = true;
                    exitReason = SIMULATION_EXIT_BY_WRONG_PARAMETER;
                }
            }
        }
        scan.close();
        return exitReason;
    }

    private Move convertInputToMove(String inputString) {
        if (inputString.equals(MoveType.LEFT.getShortName())) {
            return new Move(MoveType.LEFT);
        } else if (inputString.equals(MoveType.RIGHT.getShortName())) {
            return new Move(MoveType.RIGHT);
        } else if (inputString.equals(MoveType.QUIT.getShortName())) {
            return new Move(MoveType.QUIT);
        } else {
            return processAdvanceMove(inputString);
        }
    }

    private Move processAdvanceMove(String moveString) {
        String advanceRegex = "^" + MOVE_INDICATE_ADVANCED + " \\d+$";
        if (Pattern.matches(advanceRegex, moveString)) {
            String[] advanceMoveInputArray = moveString.split(SPACE_STRING);
            return new Move(
                    MoveType.ADVANCE,
                    Integer.parseInt(advanceMoveInputArray[advanceMoveInputArray.length - 1]));
        }
        return null;
    }

    private void processMove(Move move) throws OutOfSiteBoundary, DestructProtectedTree {
        this.report.doCommunicate();
        switch (move.getType()) {
            case RIGHT -> this.position.turnRight();
            case LEFT -> this.position.turnLeft();
            case ADVANCE -> moveAdvance(move.getDistance());
        }
    }

    private void moveAdvance(int distance) throws OutOfSiteBoundary, DestructProtectedTree {
        for (int i = 0; i < distance; i++) {
            this.position.moveForward();
            int currentRow = this.position.getRowPosition();
            int currentColumn = this.position.getColumnPosition();
            if (i == distance - 1) {
                processSquareVisit(currentRow, currentColumn);
            } else {
                processSquarePassThrough(currentRow, currentColumn);
            }
        }
    }

    private void processSquareVisit(int x, int y) throws DestructProtectedTree {
        Square square = siteMap.getSquare(x, y);
        if (this.operatorSiteMap[x][y]) {
            this.report.consumeFuel(square.getVisitedFuelUsage());
        } else {
            if (square.isProtectedTree()) {
                this.report.damageProtectedTree();
                throw new DestructProtectedTree("Destruct protected tree at " + x + ":" + y, x, y);
            }
            this.report.consumeFuel(square.getFirstVisitFuelUsage());
            this.report.removeUnclear();
            this.operatorSiteMap[x][y] = true;
        }
    }

    private void processSquarePassThrough(int x, int y) throws DestructProtectedTree {
        Square square = siteMap.getSquare(x, y);
        if (this.operatorSiteMap[x][y]) {
            this.report.consumeFuel(square.getVisitedFuelUsage());
        } else {
            if (square.isProtectedTree()) {
                this.report.damageProtectedTree();
                throw new DestructProtectedTree("Destruct protected tree at " + x + ":" + y, x, y);
            }
            this.report.consumeFuel(square.getPassThroughFuelUsage());
            if (square.willDamagePaint(true)) {
                this.report.damagePaint();
            }
            this.report.removeUnclear();
            this.operatorSiteMap[x][y] = true;
        }
    }

    private void printInputErrorToConsole() {
        System.out.println(WRONG_INPUT);
    }

    private void printIndicateToConsole() {
        System.out.print(MOVE_INDICATE);
    }
}
