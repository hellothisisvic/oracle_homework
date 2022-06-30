package com.vic.constants;

public class OperatorInteraction {
    public static final String WELCOME = "Welcome to the Aconex site clearing simulator. This is a map of the site:";
    public static final String LOCATION = "The bulldozer is currently located at the %s edge of the site, immediately to the %s of the site, and facing %s.";
    public static final String MOVE_INDICATE = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static final String SIMULATION_EXIT_BY_QUIT = "The simulation has ended at your request.";
    public static final String SIMULATION_EXIT_BY_OUT_OF_BOUNDARY = "The simulation has ended at you are out of the site boundary at x=%d y=%d.";
    public static final String SIMULATION_EXIT_BY_DESTRUCTED_PROTECTED_TREE = "The simulation has ended at you destructed a protected tree at x=%d y=%d.";
    public static final String SIMULATION_EXIT_BY_WRONG_PARAMETER = "The simulation has ended at you input an unacceptable parameter.";
    public static final String MOVE_HISTORY = "These are the commands you issued:";
    public static final String COST_LIST = "The costs for this land clearing operation were:";
    public static final String THANK_YOU = "Thank you for using the Aconex site clearing simulator.";
    public static final String WRONG_INPUT = "Unacceptable Input. Please try again.";
    public static final String MOVE_INDICATE_LEFT = "l";
    public static final String MOVE_INDICATE_RIGHT = "r";
    public static final String MOVE_INDICATE_ADVANCED = "a";
    public static final String MOVE_INDICATE_QUIT = "q";
    public static final String MOVE_INDICATE_LEFT_DESC = "turn left";
    public static final String MOVE_INDICATE_RIGHT_DESC = "turn right";
    public static final String MOVE_INDICATE_ADVANCED_DESC = "advance";
    public static final String MOVE_INDICATE_QUIT_DESC = "quit";
    public static final String MOVE_PATH_SEPARATOR = ",";
    public static final String REPORT_COMMUNICATION_ITEM_DESCRIPTION = "communication overhead";
    public static final String REPORT_FUEL_ITEM_DESCRIPTION = "fuel usage";
    public static final String REPORT_UNCLEARED_SQUARE_ITEM_DESCRIPTION = "uncleared squares";
    public static final String REPORT_PROTECTED_TREE_ITEM_DESCRIPTION = "destruction of protected tree";
    public static final String REPORT_PAINT_DAMAGE_ITEM_DESCRIPTION = "paint damage to bulldozer";
    public static final String REPORT_HEADLINE_ITEM_COLUMN_NAME = "Item";
    public static final String REPORT_HEADLINE_QUANTITY_COLUMN_NAME = "Quantity";
    public static final String REPORT_HEADLINE_COST_COLUMN_NAME = "Cost";
    public static final String REPORT_TOTAL = "Total";
    public static final String REPORT_TOTAL_SEPARATE_MARK = "----";
    public static final String EMPTY_STRING = "";
    public static final String SPACE_STRING = " ";
    public static final String NEW_LINE_STRING = "\n";

    private OperatorInteraction() {}
}
