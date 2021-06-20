package lapr.project.controller;

import lapr.project.model.Delivery;
import lapr.project.model.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Stats controller.
 */
public class StatsController {

    private List<Delivery> deliverys;
    private final Statistics stats;

    /**
     * Instantiates a new Stats controller.
     *
     * @param stats the stats
     */
    public StatsController(Statistics stats) {
        deliverys = new ArrayList<>();
        this.stats = stats;
    }

    /**
     * Show stats list.
     *
     * @return the list
     */
    public List<Delivery> showStats() {

        deliverys = stats.getAllDeliverys();

        stats.printStatistics(deliverys);

        return deliverys;
    }
}
