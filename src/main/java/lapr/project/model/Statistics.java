package lapr.project.model;

import lapr.project.data.StatisticsDB;
import lapr.project.ui.Print;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Statistics.
 */
public class Statistics {


    private final StatisticsDB db;

    /**
     * Instantiates a new Statistics.
     *
     * @param db the db
     */
    public Statistics(StatisticsDB db) {
        this.db = db;
    }

    private enum Atributos {
        /**
         * Tempo atributos.
         */
        TEMPO,
        /**
         * Distancia atributos.
         */
        DISTANCIA,
        /**
         * Energia atributos.
         */
        ENERGIA
    }


    /**
     * Gets all deliverys.
     *
     * @return the all deliverys
     */
    public List<Delivery> getAllDeliverys() {

        return db.getAllDeliverys();


    }

    /**
     * Print statistics float [ ].
     *
     * @param deliverys the deliverys
     * @return the float [ ]
     */
    public float[] printStatistics(List<Delivery> deliverys) {

        List<Delivery> scooter = new ArrayList<>();
        List<Delivery> drone = new ArrayList<>();

        splitDeliverys(deliverys, scooter, drone);

        float[] valores = new float[16];


        float mediaTempoScooter = getMedia(scooter, Atributos.TEMPO);
        valores[0] = mediaTempoScooter;
        float mediaTempoDrone = getMedia(drone, Atributos.TEMPO);
        valores[1] = mediaTempoDrone;

        float mediaDistaniaScooter = getMedia(scooter, Atributos.DISTANCIA);
        valores[2] = mediaDistaniaScooter;
        float mediaDistaniaDrone = getMedia(drone, Atributos.DISTANCIA);
        valores[3] = mediaDistaniaDrone;

        float mediaEnergiaGastaScooter = getMedia(scooter, Atributos.ENERGIA);
        valores[4] = mediaEnergiaGastaScooter;
        float mediaEnergiaGastaDrone = getMedia(drone, Atributos.ENERGIA);
        valores[5] = mediaEnergiaGastaDrone;

        float totalTempoScooter = getTotal(scooter, Atributos.TEMPO);
        valores[6] = totalTempoScooter;
        float totalTempoDrone = getTotal(drone, Atributos.TEMPO);
        valores[7] = totalTempoDrone;

        float totalDistaniaScooter = getTotal(scooter, Atributos.DISTANCIA);
        valores[8] = totalDistaniaScooter;
        float totalDistaniaDrone = getTotal(drone, Atributos.DISTANCIA);
        valores[9] = totalDistaniaDrone;

        float totalEnergiaGastaScooter = getTotal(scooter, Atributos.ENERGIA);
        valores[10] = totalEnergiaGastaScooter;
        float totalEnergiaGastaDrone = getTotal(drone, Atributos.ENERGIA);
        valores[11] = totalEnergiaGastaDrone;

        float whkmScooter = totalEnergiaGastaScooter / (totalDistaniaScooter / 1000);
        valores[12] = whkmScooter;
        float whkmDrone = totalEnergiaGastaDrone / (totalDistaniaDrone / 1000);
        valores[13] = whkmDrone;

        float kmhScooter = (totalDistaniaScooter / 1000) / (totalTempoScooter / 60);
        valores[14] = kmhScooter;
        float kmhDrone = (totalDistaniaDrone / 1000) / (totalTempoDrone / 60);
        valores[15] = kmhDrone;

        Print.formatPrintStatistics(valores);

        return valores;
    }

    private float getMedia(List<Delivery> lista, Atributos at) {
        float total = 0;
        int count = 0;
        for (Delivery d : lista) {
            switch (at) {
                case TEMPO:
                    total += d.getTimeMinutes();
                    break;
                case DISTANCIA:
                    total += d.getDistance();
                    break;
                case ENERGIA:
                    total += d.getEnergyCost();
                    break;

            }
            count++;
        }
        return total / count;
    }

    private float getTotal(List<Delivery> lista, Atributos at) {
        float total = 0;
        for (Delivery d : lista) {
            switch (at) {
                case TEMPO:
                    total += d.getTimeMinutes();
                    break;
                case DISTANCIA:
                    total += d.getDistance();
                    break;
                case ENERGIA:
                    total += d.getEnergyCost();
                    break;

            }

        }
        return total;
    }


    private void splitDeliverys(List<Delivery> deliverys, List<Delivery> scooter, List<Delivery> drone) {
        for (Delivery d : deliverys) {
            if (d.getNif() != 0) {
                scooter.add(d);
            } else {
                drone.add(d);
            }
        }
    }


}
