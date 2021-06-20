package lapr.project.model;

import java.util.Objects;

/**
 * The type Charging station.
 */
public class ChargingStation {

    private int stationId;

    public ChargingStation() {
        this.stationId = -1;
    }

    public ChargingStation ( int stationId ) {
        this.stationId = stationId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChargingStation)) return false;
        ChargingStation that = (ChargingStation) o;
        return getStationId() == that.getStationId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStationId());
    }
}
