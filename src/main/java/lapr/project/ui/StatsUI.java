package lapr.project.ui;


import lapr.project.controller.StatsController;
import lapr.project.data.StatisticsDB;
import lapr.project.model.Statistics;

/**
 * The type Stats ui.
 */
public class StatsUI {

    /**
     * Run.
     */
    public static void run() {

        StatsController controller = new StatsController(new Statistics(new StatisticsDB()));

        controller.showStats();
    }
}
