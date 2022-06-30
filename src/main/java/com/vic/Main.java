package com.vic;

import com.vic.errors.IllegalStartDirection;
import com.vic.models.SiteMap;
import com.vic.models.operator.Operator;
import com.vic.services.LoadDataService;
import com.vic.services.SimulateRunner;

import java.io.IOException;

import static com.vic.constants.ErrorMessage.FILE_CANT_READ;
import static com.vic.constants.ErrorMessage.INVALID_FILE_PATH;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(INVALID_FILE_PATH);
            return;
        }
        try {
            SiteMap siteMap = LoadDataService.loadData(args[0]);
            Operator operator = new Operator(siteMap, 0, -1);
            SimulateRunner simulateRunner = new SimulateRunner(operator);
            simulateRunner.runSimulate(siteMap);
        } catch (IOException e) {
            System.out.println(FILE_CANT_READ);
        } catch (IllegalStartDirection e) {
            System.out.println(e.getMessage());
        }
    }
}
