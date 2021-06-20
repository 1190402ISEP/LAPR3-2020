package lapr.project.ui;

import lapr.project.data.EmailHandler;
import lapr.project.model.DeliveryPlanning;
import lapr.project.model.MapGraph.Edge;
import lapr.project.model.VehicleType;
import lapr.project.model.utils.Utils;


import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type Print.
 */
public class Print {

    /**
     * Generate path string.
     *
     * @param path the path
     * @return the string
     */
    public static String generatePath(LinkedList<String> path) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < path.size() - 1; i++) {
            s.append(DeliveryPlanning.getNomePontoByCord(path.get(i))).append(" -> ");
        }
        s.append(DeliveryPlanning.getNomePontoByCord(path.get(path.size() - 1)));

        return s.toString();
    }

    /**
     * Print file delivery boolean.
     *
     * @param mostEficiente   the most eficiente
     * @param path            the path
     * @param distance        the distance
     * @param time            the time
     * @param energyCost      the energy cost
     * @param deliveryID      the delivery id
     * @param outputFileName  the output file name
     * @param actualBatteryWH the actual battery wh
     * @param payload         the payload
     * @param vehicleID       the vehicle id
     * @param courierNIF      the courier nif
     * @param pharmacyCords   the pharmacy cords
     * @return the boolean
     */
    public static boolean printFileDelivery(LinkedList<String> mostEficiente, LinkedList<String> path, float distance, float time, float energyCost, Integer deliveryID, String outputFileName, float actualBatteryWH, float payload, int vehicleID, int courierNIF, String pharmacyCords, Map<String[], Float> costs) {

        Logger.log("Delivery ID: " + deliveryID, outputFileName);
        if (courierNIF == 0) {
            Logger.log("Drone ID: " + vehicleID, outputFileName);
        } else {
            Logger.log("Scooter ID: " + vehicleID, outputFileName);
            Logger.log("Courier NIF: " + courierNIF, outputFileName);
        }
        Logger.log("Starting pharmacy name: " + DeliveryPlanning.getNomePontoByCord(pharmacyCords), outputFileName);
        Logger.log("Vehicle battery at the start of the trip: " + actualBatteryWH + " Wh", outputFileName);
        Logger.log("Weight of orders at startup: " + payload + " kg", outputFileName);

        Logger.log("Shortest path: " + generatePath(path), outputFileName);
        List<Edge<String, String>> edges = DeliveryPlanning.getEdgesByPath(path);
        if (courierNIF != 0) {
            for (Edge<String, String> e : edges) {
                Logger.log(DeliveryPlanning.getNomePontoByCord(e.getVOrig()) + " -> " + DeliveryPlanning.getNomePontoByCord(e.getVDest()) + " : " + e.getElement(), outputFileName);
            }
        }

        Logger.log("", outputFileName);
        Logger.log("Most Eficient Path (opted path): " + generatePath(mostEficiente), outputFileName);


        Logger.log("Distance: " + (distance * 1000) + " meters", outputFileName);
        Logger.log("Duration: " + Utils.timeMinutesFormat(time * 60), outputFileName);
        Logger.log("", outputFileName);
        for (String[] s : costs.keySet()) {
            Logger.log(DeliveryPlanning.getNomePontoByCord(s[0]) + " -> " + DeliveryPlanning.getNomePontoByCord(s[1]) + " : " + costs.get(s) + " Wh", outputFileName);
        }
        Logger.log("", outputFileName);
        Logger.log("Total Energy Cost: " + energyCost + " Wh", outputFileName);

        return true;
    }

    /**
     * Print phisics.
     *
     * @param outputFileName the output file name
     * @param type           the type
     */
    public static void printPhisics(String outputFileName, VehicleType type) {

        Logger.log("", outputFileName);

        Logger.log("##Physical values we consider##", outputFileName);
        Logger.log("", outputFileName);


        if (type.equals(VehicleType.SCOOTER)) {
            Logger.log("Scooter weight: " + DeliveryPlanning.getMassaScooter() + " kg", outputFileName);
            Logger.log("Courier weight: " + DeliveryPlanning.getMassaMediaHumano() + " kg", outputFileName);
            Logger.log("Travel speed: " + String.format("%.2f", DeliveryPlanning.getVelocidadeScooter() * 3.6) + " km/h", outputFileName);

        } else {
            Logger.log("Drone weight: " + DeliveryPlanning.getMassaDrone() + " kg", outputFileName);
            Logger.log("Cruising speed: " + String.format("%.2f", DeliveryPlanning.getVelocidadeCruzeiroDrone() * 3.6) + " km/h", outputFileName);
            Logger.log("Ascent speed: " + String.format("%.2f", DeliveryPlanning.getVelocidadeSubidaDrone() * 3.6) + " km/h", outputFileName);
            Logger.log("Descent speed: " + String.format("%.2f", DeliveryPlanning.getVelocidadeDescidaDrone() * 3.6) + " km/h", outputFileName);
            Logger.log("Horizontal displacement height of the drone: " + DeliveryPlanning.getAlturaDeslocamentoHorizontalDrone() + " meters", outputFileName);

        }


        Logger.log("Front area of the vehicle: " + (DeliveryPlanning.getAreaFrontalVeiculos()) + " m2", outputFileName);
        Logger.log("Air density: " + (DeliveryPlanning.getDensidadeAr()) + " kg/m3", outputFileName);
        Logger.log("Gravitational acceleration: " + (DeliveryPlanning.getAceleracaoGravitica()) + " m/s2", outputFileName);
        Logger.log("Wind speed (we only consider it against the movement of the vehicle): " + String.format("%.2f", DeliveryPlanning.getVelocidadeVento() * 3.6) + " km/h", outputFileName);

        if (type.equals(VehicleType.SCOOTER)) {
            Logger.log("Coefficient of friction asphalt : " + (DeliveryPlanning.getCoeficienteAtritoAsfalto()), outputFileName);
            Logger.log("Coefficient of friction dirt: " + (DeliveryPlanning.getCoeficienteAtritoTerra()), outputFileName);

        }
        Logger.log("Drag coefficient: " + (DeliveryPlanning.getCoeficienteArrasto()), outputFileName);

    }

    /**
     * Print remaining delivery path.
     *
     * @param outputFileName          the output file name
     * @param actualFarmacia          the actual farmacia
     * @param fullRemainingPath       the full remaining path
     * @param gastoEnergeticoRestante the gasto energetico restante
     * @param distanciaRestante       the distancia restante
     * @param tempoRestante           the tempo restante
     * @param type                    the type
     */
    public static void printRemainingDeliveryPath(String outputFileName, String actualFarmacia, LinkedList<String> fullRemainingPath, float gastoEnergeticoRestante, float distanciaRestante, float tempoRestante, VehicleType type, Map<String[], Float> costs) {

        String vehicleDesignation;
        if (type.equals(VehicleType.SCOOTER)) {
            vehicleDesignation = "Scooter";
        } else {
            vehicleDesignation = "Drone";
        }
        Logger.log("", outputFileName);
        Logger.log("The " + vehicleDesignation + " was unable to complete the entire journey, so it has to stop to charge the battery.", outputFileName);
        Logger.log("", outputFileName);

        Logger.log("Stop pharmacy for charging: " + DeliveryPlanning.getNomePontoByCord(actualFarmacia), outputFileName);
        Logger.log("Remaining path to the starting pharmacy: " + generatePath(fullRemainingPath), outputFileName);
        Logger.log("", outputFileName);
        for (String[] s : costs.keySet()) {
            Logger.log(DeliveryPlanning.getNomePontoByCord(s[0]) + " -> " + DeliveryPlanning.getNomePontoByCord(s[1]) + " : " + costs.get(s) + " Wh", outputFileName);
        }
        Logger.log("", outputFileName);
        Logger.log("Remaining total energy cost: " + gastoEnergeticoRestante + " Wh", outputFileName);
        Logger.log("Remaining distance (meters): " + distanciaRestante * 1000, outputFileName);
        Logger.log("Remaining travel time: " + Utils.timeMinutesFormat(tempoRestante * 60), outputFileName);


    }

    /**
     * Format print statistics boolean.
     *
     * @param valores the valores
     * @return the boolean
     */
    public static boolean formatPrintStatistics(float[] valores) {
        String filename = Utils.getDateUnderscore() + "-Delivery-Statistics";
        Logger.log("----------------Deliveries Statistics----------------\n", filename);
        Logger.log(String.format("  | %-40s | %-7s | %-7s |", "Results", "Scooter", "Drone"), filename);
        Logger.log("  |------------------------------------------|---------|---------|");
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Average Trip Time (min)", valores[0], valores[1]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Average Trip Distance (meters)", valores[2], valores[3]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Average Trip Energy Cost (Wh)", valores[4], valores[5]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Total Trips Time (min)", valores[6], valores[7]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Total Trips Distance (meters)", valores[8], valores[9]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Total Trips Energy Cost (Wh)", valores[10], valores[11]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Wh/km", valores[12], valores[13]), filename);
        Logger.log(String.format("  | %-40s | %-7.2f | %-7.2f |", "Km/h", valores[14], valores[15]), filename);
        Logger.log("  |------------------------------------------|---------|---------|", filename);
        return true;
    }

    public static void sendEmailInvoiceClient(int id, int nif, int usedCredits, Date orderDate, String nomeFarmacia, float totalWeight, float totalPrice, float deliveryFee, float productsTotalPrice, float totalWithDiscount, String email) throws IOException {

        StringBuilder s = new StringBuilder();
        s.append("Order successfully placed\nBelow is the information:\n\n");
        s.append("Order ID: " + id + "\n");
        s.append("NIF: " + nif + "\n");
        s.append("Used Credits: " + usedCredits + "\n");
        s.append("Order Date: " + orderDate + "\n");
        s.append("Pharmacy: " + nomeFarmacia + "\n");
        s.append("Total Weight: " + totalWeight + " kg\n");
        s.append("Total Price (w/IVA): " + totalPrice + " €\n");
        s.append("Delivery Fee (w/IVA): " + deliveryFee + " €\n");
        s.append("Products Total Price (w/IVA): " + productsTotalPrice + " €\n");
        s.append("\n");
        s.append("Final price with discount: " + totalWithDiscount + " €\n");

        EmailHandler e = new EmailHandler();

        e.sendEmail(email, "Order #" + id + " successfully placed", s.toString());

    }

    public static boolean sendOrderBackNotice(int OrderID, int nif, int pharmacyid1, int pharmacyid2, float TotalWeight, Map<String, Integer> map) throws IOException {
        StringBuilder s = new StringBuilder();
        s.append("Order back successefully placed\nBelow is the information\n\n");
        s.append("Order ID: " + OrderID + "\n");
        s.append("Courier NIF:" + nif + "\n");
        s.append("Pharmacy with lack of stock: " + pharmacyid1 + "\n");
        s.append("Ordered back Pharmacy:" + pharmacyid2 + "\n");
        s.append("Total weight: " + TotalWeight + "\n");
        for (String barcode : map.keySet()) {
            Integer stock = map.get(barcode);
            s.append("Product's barcode: " + barcode + "\n");
            s.append("Number of stock ordered: " + stock + "\n");
        }


        EmailHandler e = new EmailHandler();

        e.sendEmail("moussamanafa@outlook.pt", "OrderBack notification", s.toString());

        return true;
    }
}
