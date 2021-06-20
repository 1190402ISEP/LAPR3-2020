package lapr.project.model;

import java.util.Objects;

public class ParkPlace {

	private int id;
	private int parkId;
	private boolean isAvailable;

	public ParkPlace() {
		id = -1;
		parkId = -1;
		isAvailable = false;
	}

	public ParkPlace(int parkId, boolean isAvailable) {
		this.id = -1;
		this.parkId = parkId;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ParkPlace)) return false;
		ParkPlace parkPlace = (ParkPlace) o;
		return getId() == parkPlace.getId() && getParkId() == parkPlace.getParkId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getParkId());
	}
}
