package lapr.project.model;

import java.util.Objects;

/**
 * The type Map point.
 */
public class MapPoint {
    private String nome;
    private float latitude;
    private float longitude;
    private float altitude;

    /**
     * Instantiates a new Map point.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @param altitude  the altitude
     * @param nome      the nome
     */
    public MapPoint( float latitude, float longitude, float altitude , String nome) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets altitude.
     *
     * @return the altitude
     */
    public float getAltitude() {
        return altitude;
    }

    /**
     * Sets altitude.
     *
     * @param altitude the altitude
     */
    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    /**
     * Gets as string.
     *
     * @return the as string
     */
    public String getAsString () {
        return latitude + ", " + longitude;
    }

    /**
     * Distance between float.
     *
     * @param p2 the p 2
     * @return the float
     */
    public float distanceBetween(MapPoint p2) {
        double latitude2 = p2.getLatitude();
        double longitude2 = p2.getLongitude();

        double lat1 = latitude * (Math.PI/180);
        double lat2 = latitude2 * (Math.PI/180);
        double deltaLat = (latitude2-latitude) * (Math.PI/180);
        double deltaLong = (longitude2-longitude) * (Math.PI/180);


        double R = 6.371e3;


        double a = Math.sin(deltaLat /2) * Math.sin(deltaLat /2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLong /2) * Math.sin(deltaLong /2);

        double c = 2* Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return (float) (R * c);
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        MapPoint mapPoint = (MapPoint) o;
        return Float.compare(mapPoint.latitude, latitude) == 0 && Float.compare(mapPoint.longitude, longitude) == 0 && Float.compare(mapPoint.altitude, altitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, altitude);
    }

    @Override
    public String toString() {
        return "MapPoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                '}';
    }
}
