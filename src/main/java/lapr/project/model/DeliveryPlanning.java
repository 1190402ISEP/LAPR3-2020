package lapr.project.model;


import lapr.project.data.PlanningDB;
import lapr.project.model.MapGraph.Edge;
import lapr.project.model.MapGraph.Graph;
import lapr.project.model.MapGraph.GraphAlgorithms;
import lapr.project.model.utils.Utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The type Delivery planning.
 */
public class DeliveryPlanning {

    private static Float actualPayload;
    private final PlanningDB db;

    private static final Graph<String, String> grafoCoordenadasScooter = new Graph<>(true);
    private static final Graph<String, String> grafoCoordenadasDrone = new Graph<>(true);
    private static final Map<String, MapPoint> points = new HashMap<>();
    private static HashMap<String, Integer> indexKeys = new HashMap<>();

    private static float spentTimeLastDronePath;

    private static float velocidadeScooter = 5.56f; //(m/s)
    private static float velocidadeSubidaDrone = 5.56f; //em m/s
    private static float velocidadeDescidaDrone = 6f; //em m/s
    private static float velocidadeCruzeiroDrone = 4.0f; //em m/s
    private static float coeficienteArrasto = 1.5f;
    private static float coeficienteatritoasfalto = 0.7f;
    private static float coeficienteAtritoTerra = 0.5f;
    private static float massaMediaHumano = 75f; // em kg
    private static float massaScooter = 15f; //em kg
    private static float massaDrone = 3.5f; //em kg


    private static float areaFrontalVeiculos = 1; //em m2
    private static float densidadeAr = 1.225f; //em kg/m3
    private static float aceleracaoGravitica = 9.8f; //em m/s2
    private static float velocidadeVento = 2.77f; //em m/s
    private static float alturaDeslocamentoHorizontalDrone = 150; // em metros


    /**
     * The constant coordinatesClientsPath.
     */
    public static String coordinatesClientsPath = Utils.getPath() + "clients.txt";
    /**
     * The constant coordinatesPharmaciesPath.
     */
    public static String coordinatesPharmaciesPath = Utils.getPath() + "pharmacies.txt";
    /**
     * The constant caminhosPath.
     */
    public static String caminhosPathScooter = Utils.getPath() + "pathsScooter.txt";
    /**
     * The constant caminhosPathDrone.
     */
    public static String caminhosPathDrone = Utils.getPath() + "pathsDrone.txt";


    /**
     * Instantiates a new Delivery planning.
     *
     * @param db the db
     */
    public DeliveryPlanning(PlanningDB db) {
        this.db = db;

    }

    /**
     * Instantiates a new Delivery planning.
     */
    public DeliveryPlanning() {
        db = new PlanningDB();

    }

    /**
     * Start.
     */
    public void start() {
        try {
            createMapPoints();
            createGraphs();
            generateIndexes();

            db.initiatePoints(points);

            readPhisics();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start with xml.
     */
    public void startWithXML() {
        try {
            createMapPoints();
            createGraphsMaxConections();
            readRestricoes();
            generateIndexes();

            db.initiatePoints(points);

            readPhisics();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets coeficiente arrasto.
     *
     * @return the coeficiente arrasto
     */
    public static float getCoeficienteArrasto() {
        return coeficienteArrasto;
    }

    /**
     * Gets coeficiente atrito asfalto.
     *
     * @return the coeficiente atrito asfalto
     */
    public static float getCoeficienteAtritoAsfalto() {
        return coeficienteatritoasfalto;
    }

    /**
     * Gets coeficiente atrito terra.
     *
     * @return the coeficiente atrito terra
     */
    public static float getCoeficienteAtritoTerra() {
        return coeficienteAtritoTerra;
    }

    /**
     * Gets massa media humano.
     *
     * @return the massa media humano
     */
    public static float getMassaMediaHumano() {
        return massaMediaHumano;
    }

    /**
     * Gets massa scooter.
     *
     * @return the massa scooter
     */
    public static float getMassaScooter() {
        return massaScooter;
    }

    /**
     * Gets massa drone.
     *
     * @return the massa drone
     */
    public static float getMassaDrone() {
        return massaDrone;
    }

    /**
     * Gets area frontal veiculos.
     *
     * @return the area frontal veiculos
     */
    public static float getAreaFrontalVeiculos() {
        return areaFrontalVeiculos;
    }

    /**
     * Gets densidade ar.
     *
     * @return the densidade ar
     */
    public static float getDensidadeAr() {
        return densidadeAr;
    }

    /**
     * Gets aceleracao gravitica.
     *
     * @return the aceleracao gravitica
     */
    public static float getAceleracaoGravitica() {
        return aceleracaoGravitica;
    }

    /**
     * Gets velocidade vento.
     *
     * @return the velocidade vento
     */
    public static float getVelocidadeVento() {
        return velocidadeVento;
    }

    /**
     * Gets velocidade subida drone.
     *
     * @return the velocidade subida drone
     */
    public static float getVelocidadeSubidaDrone() {
        return velocidadeSubidaDrone;
    }

    /**
     * Gets velocidade descida drone.
     *
     * @return the velocidade descida drone
     */
    public static float getVelocidadeDescidaDrone() {
        return velocidadeDescidaDrone;
    }

    /**
     * Gets velocidade cruzeiro drone.
     *
     * @return the velocidade cruzeiro drone
     */
    public static float getVelocidadeCruzeiroDrone() {
        return velocidadeCruzeiroDrone;
    }

    /**
     * Gets velocidade scooter.
     *
     * @return the velocidade scooter
     */
    public static float getVelocidadeScooter() {
        return velocidadeScooter;
    }

    /**
     * Gets spent time last drone path.
     *
     * @return the spent time last drone path
     */
    public static float getSpentTimeLastDronePath() {
        return spentTimeLastDronePath;
    }


    /**
     * Gets nome ponto by cord.
     *
     * @param cord the cord
     * @return the nome ponto by cord
     */
    public static String getNomePontoByCord(String cord) {
        return points.get(cord).getNome();
    }

    /**
     * Gets actual payload.
     *
     * @return the actual payload
     */
    public static Float getActualPayload() {
        return DeliveryPlanning.actualPayload;
    }

    /**
     * Sets actual payload.
     *
     * @param actualPayload the actual payload
     */
    public static void setActualPayload(Float actualPayload) {
        DeliveryPlanning.actualPayload = actualPayload;
    }

    /**
     * Gets id closest pharmacy by user email.
     *
     * @param email the email
     * @param db    the db
     * @return the id closest pharmacy by user email
     */
    public static int getIdClosestPharmacyByUserEmail(String email, PlanningDB db) {


        String clientCord = db.getClientCordByEmail(email);

        Map<Integer, String> cordsFarmacias = db.getCordsOfAllPharmacys();

        return getIdClosestByOrigDest(clientCord, cordsFarmacias);
    }

    /**
     * Gets id closest by orig dest.
     *
     * @param orig the orig
     * @param dest the dest
     * @return the id closest by orig dest
     */
    public static int getIdClosestByOrigDest(String orig, Map<Integer, String> dest) {

        int idMenor = -1;
        float menorDist = Float.MAX_VALUE;


        for (Integer id : dest.keySet()) {

            if (orig.equals(dest.get(id))) {
                continue;
            }

            float dist;
            dist = (float) GraphAlgorithms.shortestPath(grafoCoordenadasScooter, orig, dest.get(id), new LinkedList<>());
            if (dist < menorDist) {
                menorDist = dist;
                idMenor = id;

            }

        }


        return idMenor;
    }


    /**
     * Gets total payload.
     *
     * @param pesosPorEncomenda the pesos por encomenda
     * @return the total payload
     */
    public static float getTotalPayload(Map<String, Float> pesosPorEncomenda) {
        float total = 0;
        for (Float f : pesosPorEncomenda.values()) {
            total += f;
        }
        return total;
    }

    /**
     * Gets altura deslocamento horizontal drone.
     *
     * @return the altura deslocamento horizontal drone
     */
    public static float getAlturaDeslocamentoHorizontalDrone() {
        return alturaDeslocamentoHorizontalDrone;
    }

    /**
     * Sets altura deslocamento horizontal drone.
     *
     * @param alturaDeslocamentoHorizontalDrone the altura deslocamento horizontal drone
     */
    public static void setAlturaDeslocamentoHorizontalDrone(float alturaDeslocamentoHorizontalDrone) {
        DeliveryPlanning.alturaDeslocamentoHorizontalDrone = alturaDeslocamentoHorizontalDrone;
    }

    /**
     * Sets velocidade scooter.
     *
     * @param velocidadeScooter the velocidade scooter
     */
    public static void setVelocidadeScooter(float velocidadeScooter) {
        DeliveryPlanning.velocidadeScooter = velocidadeScooter;
    }

    /**
     * Sets velocidade subida drone.
     *
     * @param velocidadeSubidaDrone the velocidade subida drone
     */
    public static void setVelocidadeSubidaDrone(float velocidadeSubidaDrone) {
        DeliveryPlanning.velocidadeSubidaDrone = velocidadeSubidaDrone;
    }

    /**
     * Sets velocidade descida drone.
     *
     * @param velocidadeDescidaDrone the velocidade descida drone
     */
    public static void setVelocidadeDescidaDrone(float velocidadeDescidaDrone) {
        DeliveryPlanning.velocidadeDescidaDrone = velocidadeDescidaDrone;
    }

    /**
     * Sets velocidade cruzeiro drone.
     *
     * @param velocidadeCruzeiroDrone the velocidade cruzeiro drone
     */
    public static void setVelocidadeCruzeiroDrone(float velocidadeCruzeiroDrone) {
        DeliveryPlanning.velocidadeCruzeiroDrone = velocidadeCruzeiroDrone;
    }

    /**
     * Sets coeficiente arrasto.
     *
     * @param coeficienteArrasto the coeficiente arrasto
     */
    public static void setCoeficienteArrasto(float coeficienteArrasto) {
        DeliveryPlanning.coeficienteArrasto = coeficienteArrasto;
    }

    /**
     * Sets coeficienteatritoasfalto.
     *
     * @param coeficienteatritoasfalto the coeficienteatritoasfalto
     */
    public static void setCoeficienteatritoasfalto(float coeficienteatritoasfalto) {
        DeliveryPlanning.coeficienteatritoasfalto = coeficienteatritoasfalto;
    }

    /**
     * Sets coeficiente atrito terra.
     *
     * @param coeficienteAtritoTerra the coeficiente atrito terra
     */
    public static void setCoeficienteAtritoTerra(float coeficienteAtritoTerra) {
        DeliveryPlanning.coeficienteAtritoTerra = coeficienteAtritoTerra;
    }

    /**
     * Sets massa media humano.
     *
     * @param massaMediaHumano the massa media humano
     */
    public static void setMassaMediaHumano(float massaMediaHumano) {
        DeliveryPlanning.massaMediaHumano = massaMediaHumano;
    }

    /**
     * Sets massa scooter.
     *
     * @param massaScooter the massa scooter
     */
    public static void setMassaScooter(float massaScooter) {
        DeliveryPlanning.massaScooter = massaScooter;
    }

    /**
     * Sets massa drone.
     *
     * @param massaDrone the massa drone
     */
    public static void setMassaDrone(float massaDrone) {
        DeliveryPlanning.massaDrone = massaDrone;
    }

    /**
     * Sets area frontal veiculos.
     *
     * @param areaFrontalVeiculos the area frontal veiculos
     */
    public static void setAreaFrontalVeiculos(float areaFrontalVeiculos) {
        DeliveryPlanning.areaFrontalVeiculos = areaFrontalVeiculos;
    }

    /**
     * Sets densidade ar.
     *
     * @param densidadeAr the densidade ar
     */
    public static void setDensidadeAr(float densidadeAr) {
        DeliveryPlanning.densidadeAr = densidadeAr;
    }

    /**
     * Sets aceleracao gravitica.
     *
     * @param aceleracaoGravitica the aceleracao gravitica
     */
    public static void setAceleracaoGravitica(float aceleracaoGravitica) {
        DeliveryPlanning.aceleracaoGravitica = aceleracaoGravitica;
    }

    /**
     * Sets velocidade vento.
     *
     * @param velocidadeVento the velocidade vento
     */
    public static void setVelocidadeVento(float velocidadeVento) {
        DeliveryPlanning.velocidadeVento = velocidadeVento;
    }

    private static void createMapPoints() throws FileNotFoundException {
        List<Scanner> scs = new ArrayList<>();

        if (!coordinatesClientsPath.isEmpty()) scs.add(new Scanner(new File(coordinatesClientsPath)));
        if (!coordinatesPharmaciesPath.isEmpty()) scs.add(new Scanner(new File(coordinatesPharmaciesPath)));

        for (Scanner sc : scs) {
            while (sc.hasNextLine()) {

                String linha = sc.nextLine();
                if (linha.charAt(0) == '#') {
                    continue;
                }
                String[] arr = linha.split("--");
                String[] splited = arr[1].split(";");
                String[] cords = splited[0].split(", ");
                float altitude = Float.parseFloat(splited[1]);
                float latitude = Float.parseFloat(cords[0].trim());
                float longitude = Float.parseFloat(cords[1].trim());

                points.put(splited[0], new MapPoint(latitude, longitude, altitude, arr[0]));
            }
        }

    }

    private static void createGraphs() throws FileNotFoundException {
        List<Scanner> list = new ArrayList<>();

        list.add(new Scanner(new File(caminhosPathScooter)));
        list.add(new Scanner(new File(caminhosPathDrone)));

        VehicleType type = VehicleType.SCOOTER;
        for (Scanner sc : list) {

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.charAt(0) == '#') {
                    continue;
                }

                String[] splited = linha.split(";");


                MapPoint p1 = points.get(splited[0]);
                MapPoint p2 = points.get(splited[1]);
                String conectionType = splited[2];

                float dist = p1.distanceBetween(p2);
                if (type.equals(VehicleType.SCOOTER)) {
                    grafoCoordenadasScooter.insertEdge(splited[0], splited[1], splited[3], dist);

                } else {
                    grafoCoordenadasDrone.insertEdge(splited[0], splited[1], splited[3], dist);
                }

                if (conectionType.equals("2")) {
                    if (type.equals(VehicleType.SCOOTER)) {
                        grafoCoordenadasScooter.insertEdge(splited[1], splited[0], splited[3], dist);

                    } else {
                        grafoCoordenadasDrone.insertEdge(splited[1], splited[0], splited[3], dist);
                    }

                }

            }
            sc.close();
            type = VehicleType.DRONE;
        }

    }

    /**
     * Create graphs max conections.
     */
    public static void createGraphsMaxConections() {
        for (String s : points.keySet()) {
            for (String s2 : points.keySet()) {
                if (!s.equals(s2)) {
                    double dist = points.get(s).distanceBetween(points.get(s2));

                    grafoCoordenadasScooter.insertEdge(s, s2, "asfalto", dist);
                    grafoCoordenadasDrone.insertEdge(s, s2, "asfalto", dist);
                }
            }
        }
    }

    /**
     * Read restricoes.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public static void readRestricoes() throws FileNotFoundException {

        Scanner sc1 = new Scanner(new File(Utils.getPath() + "restricoesScooter.txt"));
        Scanner sc2 = new Scanner(new File(Utils.getPath() + "restricoesDrones.txt"));

        Scanner[] scanners = new Scanner[2];
        scanners[0] = sc1;
        scanners[1] = sc2;

        VehicleType type = VehicleType.SCOOTER;

        for (Scanner sc : scanners) {
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                if (linha.charAt(0) == '#') {
                    continue;
                }

                String[] splited = linha.split(";");

                String orig = splited[0];
                String dest = splited[1];
                String conection = splited[2];

                if (conection.equals("1")) {
                    if (type.equals(VehicleType.SCOOTER)) {
                        grafoCoordenadasScooter.removeEdge(dest, orig);

                    } else {
                        grafoCoordenadasDrone.removeEdge(dest, orig);

                    }
                } else {
                    if (type.equals(VehicleType.SCOOTER)) {
                        grafoCoordenadasScooter.removeEdge(dest, orig);
                        grafoCoordenadasScooter.removeEdge(orig, dest);

                    } else {
                        grafoCoordenadasDrone.removeEdge(dest, orig);
                        grafoCoordenadasDrone.removeEdge(orig, dest);

                    }
                }


            }
            sc.close();
            type = VehicleType.DRONE;
        }


    }


    private void readPhisics() {

        try {
            Scanner sc = new Scanner(new File(Utils.getPath() + "IniciarVariaveisFisicas.txt"));

            float[] valores = new float[15];
            int i = 0;

            while (sc.hasNextLine()) {
                String valorString = sc.nextLine().split("--")[1];
                float valor = Float.parseFloat(valorString);
                valores[i] = valor;
                i++;
            }

            setVelocidadeScooter(valores[0]);
            setVelocidadeSubidaDrone(valores[1]);
            setVelocidadeDescidaDrone(valores[2]);
            setVelocidadeCruzeiroDrone(valores[3]);
            setCoeficienteArrasto(valores[4]);
            setCoeficienteatritoasfalto(valores[5]);
            setCoeficienteAtritoTerra(valores[6]);
            setMassaMediaHumano(valores[7]);
            setMassaScooter(valores[8]);
            setMassaDrone(valores[9]);
            setAreaFrontalVeiculos(valores[10]);
            setDensidadeAr(valores[11]);
            setAceleracaoGravitica(valores[12]);
            setVelocidadeVento(valores[13]);
            setAlturaDeslocamentoHorizontalDrone(valores[14]);

            sc.close();
        } catch (FileNotFoundException e) {
            return;
        }


    }


    /**
     * Most eficient path list.
     *
     * @param casasClientes     the casas clientes
     * @param startingFarmacia  the starting farmacia
     * @param type              the type
     * @param bateriaAtualMW    the bateria atual mw
     * @param payload           the payload
     * @param pesosPorEncomenda the pesos por encomenda
     * @param allFarmacias      the all farmacias
     * @param remainingPath     the remaining path
     * @return the list
     */
    public List<String> mostEficientPath(List<String> casasClientes, String startingFarmacia, VehicleType type, float bateriaAtualMW, float payload, Map<String, Float> pesosPorEncomenda, Map<Integer, String> allFarmacias, LinkedList<String> remainingPath) {

        List<String> completePath = mostEficientPath(type, new LinkedList<>(casasClientes), startingFarmacia, startingFarmacia, payload, new HashMap<>(pesosPorEncomenda));

        if (hasSuficientEnergy(completePath, bateriaAtualMW, type, payload, new HashMap<>(pesosPorEncomenda))) {

            return completePath;
        }

        return mostEficientComParagem(new LinkedList<>(casasClientes), startingFarmacia, type, bateriaAtualMW, allFarmacias, remainingPath, payload, new HashMap<>(pesosPorEncomenda));


    }

    /**
     * Most eficient com paragem list.
     *
     * @param casasClientes     the casas clientes
     * @param startingFarmacia  the starting farmacia
     * @param type              the type
     * @param bateriaAtualMW    the bateria atual mw
     * @param allFarmacias      the all farmacias
     * @param remainingPath     the remaining path
     * @param payload           the payload
     * @param pesosPorEncomenda the pesos por encomenda
     * @return the list
     */
    public List<String> mostEficientComParagem(List<String> casasClientes, String startingFarmacia, VehicleType type, float bateriaAtualMW, Map<Integer, String> allFarmacias, LinkedList<String> remainingPath, float payload, Map<String, Float> pesosPorEncomenda) {
        LinkedList<String> cloneClientes = new LinkedList<>(casasClientes);
        int size = cloneClientes.size();

        for (int i = 0; i < size; i++) {
            for (String farmaciaDestino : allFarmacias.values()) {
                List<String> path = mostEficientPath(type, new LinkedList<>(cloneClientes), startingFarmacia, startingFarmacia, payload, new HashMap<>(pesosPorEncomenda));
                if (path == null) continue;
                if (hasSuficientEnergy(path, bateriaAtualMW, type, payload, new HashMap<>(pesosPorEncomenda))) {
                    remainingPath.addFirst(farmaciaDestino);
                    for (String ver : casasClientes) {
                        if (!path.contains(ver)) {
                            remainingPath.add(ver);
                        }
                    }

                    remainingPath.addLast(startingFarmacia);
                    return path;

                }
            }
            cloneClientes.remove(getCasaMaisDistante(startingFarmacia, new LinkedList<>(cloneClientes), type)); //aqui remove a farmacia mais distante da farmacia de origem
        }
        return null;
    }

    /**
     * Gets most distance house from a certain pharmacy
     *
     * @param farmacia the farmacia
     * @param casas    the casas
     * @param type     the type
     * @return the casa mais distante
     */
    public String getCasaMaisDistante(String farmacia, List<String> casas, VehicleType type) {
        String maisDistante = null;
        float maiorPeso = -1f;

        for (String casa : casas) {
            if (type.equals(VehicleType.SCOOTER)) {
                double peso = GraphAlgorithms.shortestPath(grafoCoordenadasScooter, farmacia, casa, new LinkedList<>());
                if (peso > maiorPeso) {
                    maiorPeso = (float) peso;
                    maisDistante = casa;
                }
            } else {


                double peso = GraphAlgorithms.shortestPath(grafoCoordenadasDrone, farmacia, casa, new LinkedList<>());
                if (peso > maiorPeso) {
                    maiorPeso = (float) peso;
                    maisDistante = casa;
                }
            }

        }

        return maisDistante;
    }

    /**
     * Checks if has suficient energy
     *
     * @param completePath      the complete path
     * @param bateriaAtualMW    the bateria atual mw
     * @param type              the type
     * @param payload           the payload
     * @param pesosPorEncomenda the pesos por encomenda
     * @return the boolean
     */
    public boolean hasSuficientEnergy(List<String> completePath, float bateriaAtualMW, VehicleType type, float payload, Map<String, Float> pesosPorEncomenda) {
        float energyCost;

        if (type.equals(VehicleType.SCOOTER)) {
            energyCost = calculateEnergyCost(completePath, payload, VehicleType.SCOOTER, new HashMap<>(pesosPorEncomenda), new HashMap<>());

        } else {
            energyCost = calculateEnergyCost(completePath, payload, VehicleType.DRONE, new HashMap<>(pesosPorEncomenda), new HashMap<>());
        }
        return bateriaAtualMW >= energyCost;
    }


    /**
     * Most eficient path scooter list.
     *
     * @param type              the type
     * @param clientes          the clientes
     * @param startingFarmacia  the starting farmacia
     * @param ultimaFarmacia    the ultima farmacia
     * @param payload           the payload
     * @param pesosPorEncomenda the pesos por encomenda
     * @return the list
     */
    public List<String> mostEficientPath(VehicleType type, List<String> clientes, String startingFarmacia, String ultimaFarmacia, float payload, Map<String, Float> pesosPorEncomenda) {

        LinkedList<LinkedList<String>> permutacoes = (LinkedList<LinkedList<String>>) allPermutations(clientes);
        float menorGasto = Float.MAX_VALUE;
        List<String> menor = null;

        for (LinkedList<String> per : permutacoes) {
            LinkedList<String> clonePer = new LinkedList<>(per);
            clonePer.addFirst(startingFarmacia);
            clonePer.addLast(ultimaFarmacia);

            List<String> caminho = unirPontosComRestantesDoMapa(clonePer, type);
            float cost = calculateEnergyCost(caminho, payload, type, new HashMap<>(pesosPorEncomenda), new HashMap<>());

            if (cost < menorGasto) {
                menorGasto = cost;
                menor = caminho;
            }

        }

        return menor;


    }

    /**
     * Shortest path scooter list.
     *
     * @param type             the type
     * @param lista            the lista
     * @param startingFarmacia the starting farmacia
     * @param ultimaFarmacia   the ultima farmacia
     * @return the list
     */
    public List<String> shortestPath(VehicleType type, List<String> lista, String startingFarmacia, String ultimaFarmacia) {

        LinkedList<String> cords = new LinkedList<>(lista);

        Double[][] matriz = grafoCordsToMatriz(type);
        GraphAlgorithms.calcularMenorDistanciaWarshallMethod(matriz);

        LinkedList<LinkedList<String>> permutacoes = (LinkedList<LinkedList<String>>) allPermutations(cords);
        LinkedList<String> listaFinal = (LinkedList<String>) calcularMenorCaminhoFinal(startingFarmacia, ultimaFarmacia, permutacoes, matriz);

        if (listaFinal == null) {
            return null;
        }


        return unirPontosComRestantesDoMapa(listaFinal, type);
    }


    /**
     * Calculate energy cost float.
     *
     * @param path              the path
     * @param payload           the payload
     * @param type              the type
     * @param pesosPorEncomenda the pesos por encomenda
     * @param costs             the costs
     * @return the float
     */
    public static float calculateEnergyCost(List<String> path, float payload, VehicleType type, Map<String, Float> pesosPorEncomenda, Map<String[], Float> costs) {
        if (type.equals(VehicleType.SCOOTER)) {
            return energyCostScooter(path, payload, pesosPorEncomenda, costs);
        } else {
            return energyCostDrone(path, payload, pesosPorEncomenda, costs);
        }


    }

    private static float energyCostDrone(List<String> path, float payload, Map<String, Float> pesosPorEncomenda, Map<String[], Float> costs) {

        float totalEnergy = 0;
        float actualPayload = payload;
        float totalTempoGastoHoras = 0;


        LinkedList<String> pathlink = (LinkedList<String>) path;

        int size = pathlink.size() - 1;



        for (int i = 0; i < size; i++) {
            if (pathlink.get(i) != null && pathlink.get(i + 1) != null) {
                MapPoint pOrig = points.get(pathlink.get(i));
                MapPoint pDest = points.get(pathlink.get(i + 1));

                float cost = 0;

                if (pesosPorEncomenda.get(pathlink.get(i)) != null || i == 0) { // so calcula a a subida se for o ponto inical ou estiver num ponto de entrega
                    float distanciaSubida;
                    if (i == 0) {
                        distanciaSubida = alturaDeslocamentoHorizontalDrone; //em metros
                    } else {
                        distanciaSubida = alturaDeslocamentoHorizontalDrone - 10; //em metros
                    }

                    float dragSubida = calculateDragForce(velocidadeSubidaDrone);
                    float pesoSubida = calculatePesoForce(massaDrone + actualPayload);

                    float potenciaSubida = calculatePotencia(dragSubida + pesoSubida, velocidadeSubidaDrone);


                    float spentTimeSubidaHoras = calculateSpentTime(distanciaSubida / 1000, velocidadeSubidaDrone);

                    totalTempoGastoHoras += spentTimeSubidaHoras;

                    float gastoEnergeticoSubida = calculateGastoEnergetico(potenciaSubida, spentTimeSubidaHoras);

                    cost += gastoEnergeticoSubida;
                }

                if (pesosPorEncomenda.get(pathlink.get(i + 1)) != null || i == size - 1) { //so calcula a descida se o dest for um ponto de entrega ou a farmacia de destino
                    float forcaDragDescida = calculateDragForce(velocidadeDescidaDrone);
                    float forcaPesoDescida = calculatePesoForce(massaDrone + actualPayload);

                    float forcaTotal = forcaPesoDescida - forcaDragDescida;

                    float potenciaDescida = calculatePotencia(forcaTotal, velocidadeDescidaDrone);

                    float distanciaDescidaKM = (pOrig.getAltitude() + alturaDeslocamentoHorizontalDrone - pDest.getAltitude() - 10) / 1000;

                    if (i == size - 1) {
                        distanciaDescidaKM += 0.010;
                    }

                    float spentTimeDescidaHoras = calculateSpentTime(distanciaDescidaKM, velocidadeDescidaDrone);

                    totalTempoGastoHoras += spentTimeDescidaHoras;

                    float gastoEnergeticoDescida = calculateGastoEnergetico(potenciaDescida, spentTimeDescidaHoras);

                    cost += gastoEnergeticoDescida;
                }


                //-------------------------------

                float forcaCruzeiro = (float) Math.sqrt(Math.pow(calculateDragForce(velocidadeCruzeiroDrone), 2) + Math.pow(calculatePesoForce(actualPayload + massaDrone), 2));

                float potenciaCruzeiro = calculatePotencia(forcaCruzeiro, velocidadeCruzeiroDrone);

                float spentTimeCruzeiroHoras = calculateSpentTime(pOrig.distanceBetween(pDest), velocidadeCruzeiroDrone);

                totalTempoGastoHoras += spentTimeCruzeiroHoras;

                float gastoEnergeticoCruzeiro = calculateGastoEnergetico(potenciaCruzeiro, spentTimeCruzeiroHoras);

                cost += gastoEnergeticoCruzeiro;


                String[] po = new String[2];
                po[0] = pathlink.get(i);
                po[1] = pathlink.get(i + 1);

                costs.put(po, cost);

                totalEnergy += cost;


                if (pesosPorEncomenda.get(pathlink.get(i + 1)) != null ) {
                    actualPayload -= pesosPorEncomenda.get(pathlink.get(i + 1));
                    pesosPorEncomenda.remove(pathlink.get(i+1));
                }

            }


        }

        setActualPayload(actualPayload);


        setSpentTimeLastDronePath(totalTempoGastoHoras);

        return totalEnergy;
    }

    private static void setSpentTimeLastDronePath(float totalTempoGastoHoras) {
        DeliveryPlanning.spentTimeLastDronePath = totalTempoGastoHoras;
    }

    private static float calculateGastoEnergetico(float potencia, float tempoHoras) {
        return potencia * tempoHoras;
    }

    private static float calculatePotencia(float forca, float velocidade) {
        return forca * velocidade;
    }

    private static float calculateDragForce(float velocidade) {
        return (float) (0.5 * coeficienteArrasto * areaFrontalVeiculos * densidadeAr * Math.pow(velocidade + velocidadeVento, 2));
    }

    private static float calculatePesoForce(float massaTotal) {
        return massaTotal * aceleracaoGravitica;
    }

    private static float energyCostScooter(List<String> path, float payload, Map<String, Float> pesosPorEncomenda, Map<String[], Float> costs) {
        float totalEnergy = 0;
        float actualPayload = payload;


        LinkedList<String> pathlink = (LinkedList<String>) path;

        int size = pathlink.size() - 1;

        // Map<String, Integer> bufferMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            if (pathlink.get(i) != null && pathlink.get(i + 1) != null) {
                MapPoint pOrig = points.get(pathlink.get(i));
                MapPoint pDest = points.get(pathlink.get(i + 1));

                Edge<String, String> edge = grafoCoordenadasScooter.getEdge(pathlink.get(i), pathlink.get(i + 1));

                String tipoCaminho = edge.getElement();


                float distance = pOrig.distanceBetween(pDest);

                float altitudeDif = pDest.getAltitude() - pOrig.getAltitude();

                if (altitudeDif < 0) { //estou a considerar que nao gasta energia na descida
                    continue;
                }

                float time = calculateSpentTime(distance, velocidadeScooter);

                float potencia = calcularPotenciaScooter(altitudeDif, actualPayload, distance, tipoCaminho);

                float energyCost = potencia * time;


                String[] po = new String[2];
                po[0] = pathlink.get(i);
                po[1] = pathlink.get(i + 1);

                costs.put(po, energyCost);

                totalEnergy += energyCost;

                if (pesosPorEncomenda.get(pathlink.get(i + 1)) != null) {
                    actualPayload -= pesosPorEncomenda.get(pathlink.get(i + 1));
                    pesosPorEncomenda.remove(pathlink.get(i + 1));
                    // bufferMap.put(pathlink.get(i + 1), 1);
                }


            }


        }

        setActualPayload(actualPayload);

        return totalEnergy;
    }

    private static float calcularPotenciaScooter(float altitudeDif, float payload, float distance, String tipoCaminho) {

        float coeficienteAtritoUsado = -1f;

        if (tipoCaminho.equals("terra")) {
            coeficienteAtritoUsado = coeficienteAtritoTerra;
        } else if (tipoCaminho.equals("asfalto")) {
            coeficienteAtritoUsado = coeficienteatritoasfalto;
        }

        float fa = (float) (0.5 * coeficienteArrasto * areaFrontalVeiculos * densidadeAr * Math.pow(velocidadeScooter + velocidadeVento, 2));

        float OG = (altitudeDif / (distance * 1000));

        float fr = (float) (coeficienteAtritoUsado * (massaMediaHumano + massaScooter + payload) * aceleracaoGravitica * Math.cos(Math.atan(OG)));

        float fTotal = fa + fr;


        return fTotal * velocidadeScooter;
    }


    /**
     * devolve o caminho final que liga os dois users passando por pontos intermedios
     *
     * @param lista the lista
     * @param type  the type
     * @return the linked list
     */
    public static List<String> unirPontosComRestantesDoMapa(List<String> lista, VehicleType type) {
        LinkedList<String> linkedlista = (LinkedList<String>) lista;

        String[] array = linkedlista.toArray(new String[0]);
        LinkedList<String> caminhoReturn = new LinkedList<>();
        if (type.equals(VehicleType.SCOOTER)) {
            GraphAlgorithms.shortestPath(grafoCoordenadasScooter, array[0], array[1], caminhoReturn);

        } else {
            GraphAlgorithms.shortestPath(grafoCoordenadasDrone, array[0], array[1], caminhoReturn);
        }
        for (int i = 1; i < array.length - 1; i++) {
            caminhoReturn.removeLast();
            LinkedList<String> temp = new LinkedList<>();
            if (type.equals(VehicleType.SCOOTER)) {
                GraphAlgorithms.shortestPath(grafoCoordenadasScooter, array[i], array[i + 1], temp);

            } else {
                GraphAlgorithms.shortestPath(grafoCoordenadasDrone, array[i], array[i + 1], temp);
            }

            caminhoReturn.addAll(temp);

        }

        return caminhoReturn;
    }

    /**
     * devolve um sequencia de pontos por onde o caminho tem que passar ordenados por ordem de menor distancia quando analisadas todas as permutações
     *
     * @param vInit  the v init
     * @param vFin   the v fin
     * @param lista  the permutacoes
     * @param matrix the matrix
     * @return the linked list
     */
    public static List<String> calcularMenorCaminhoFinal(String vInit, String vFin, List<LinkedList<String>> lista, Double[][] matrix) {
        LinkedList<LinkedList<String>> permutacoes = (LinkedList<LinkedList<String>>) lista;

        double menorPeso = Double.MAX_VALUE;
        LinkedList<String> listaFinal = new LinkedList<>();

        for (LinkedList<String> intermedio : permutacoes) {
            LinkedList<String> temp = new LinkedList<>();
            temp.add(vInit);
            temp.addAll(intermedio);
            temp.add(vFin);

            String[] array = temp.toArray(new String[0]);
            double pesoTotal = 0;
            for (int i = 0; i < array.length - 1; i++) {
                if (indexKeys.get(array[i]) == null || indexKeys.get(array[i + 1]) == null) {
                    return null;
                }
                Double peso = matrix[indexKeys.get(array[i])][indexKeys.get(array[i + 1])];
                if (peso == null) return null;
                pesoTotal += peso;
            }
            if (pesoTotal < menorPeso) {
                menorPeso = pesoTotal;
                listaFinal = temp;
            }
        }

        return listaFinal;


    }

    /**
     * devolve todas as permutações de uma sequencia de pontos numa lista
     *
     * @param lista the list
     * @return the linked list
     */
    public static List<LinkedList<String>> allPermutations(List<String> lista) {
        LinkedList<String> list = (LinkedList<String>) lista;


        if (list.size() == 0) {
            LinkedList<LinkedList<String>> result = new LinkedList<>();
            result.add(new LinkedList<>());
            return result;
        }

        LinkedList<LinkedList<String>> returnMe = new LinkedList<>();

        String firstElement = list.remove(0);

        LinkedList<LinkedList<String>> recursiveReturn = (LinkedList<LinkedList<String>>) allPermutations(list);
        for (LinkedList<String> li : recursiveReturn) {

            for (int index = 0; index <= li.size(); index++) {
                LinkedList<String> temp = new LinkedList<>(li);
                temp.add(index, firstElement);
                returnMe.add(temp);
            }

        }
        return returnMe;
    }

    /**
     * Grafo cords to matriz double [ ] [ ].
     *
     * @param type the type
     * @return the double [ ] [ ]
     */
    public static Double[][] grafoCordsToMatriz(VehicleType type) {

        Graph<String, String> grafo;
        if (type.equals(VehicleType.SCOOTER)) {
            grafo = grafoCoordenadasScooter;
        } else {
            grafo = grafoCoordenadasDrone;

        }


        int tamanhoGrafo = grafo.numVertices();
        Double[][] matriz = new Double[tamanhoGrafo][tamanhoGrafo];
        generateIndexes();

        for (String coordenadas : points.keySet()) {
            for (String adjacente : grafo.adjVertices(coordenadas)) {
                matriz[indexKeys.get(coordenadas)][indexKeys.get(adjacente)] = grafo.getEdge(coordenadas, adjacente).getWeight();
            }
        }

        return matriz;
    }

    /**
     * Generate indexes.
     */
    public static void generateIndexes() {
        indexKeys = new HashMap<>();
        int i = 0;
        for (String pais : points.keySet()) {
            indexKeys.put(pais, i);
            i++;
        }
    }



    /**
     * Gets points.
     *
     * @return the points
     */
    public static Map<String, MapPoint> getPoints() {
        return points;
    }

    /**
     * Calculate spent time float.
     *
     * @param distance   the distance
     * @param velocidade the velocidade
     * @return the float
     */
    public static float calculateSpentTime(float distance, float velocidade) { //distancia em km  tenho que retornar tempo em horas

        return (float) (distance / (velocidade * 3.6)); //como a velocidade esta em m/s tenho que passar para km por hora
    }


    /**
     * Path weight float.
     *
     * @param path the path
     * @return the float
     */
    public float pathWeight(List<String> path) {
        float total = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            if (path.get(i) != null && path.get(i + 1) != null) {
                float dist = points.get(path.get(i)).distanceBetween(points.get(path.get(i + 1)));
                total += dist;
            }


        }


        return total;
    }

    /**
     * Gets edges by path.
     *
     * @param path the path
     * @return the edges by path
     */
    public static List<Edge<String, String>> getEdgesByPath(LinkedList<String> path) {
        List<Edge<String, String>> edges = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            Edge<String, String> e = grafoCoordenadasScooter.getEdge(path.get(i), path.get(i + 1));
            edges.add(e);
        }

        return edges;
    }


}
