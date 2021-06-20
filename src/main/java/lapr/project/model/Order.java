package lapr.project.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Order.
 */
public class Order {
    private int id;
    private LocalDateTime orderDate;
    private int stateId;

    /**
     * Instantiates a new Order.
     *
     * @param id        the id
     * @param orderDate the order date
     * @param stateId   the state id
     */
    public Order(int id, LocalDateTime orderDate, int stateId) {
        this.id = id;
        this.orderDate = orderDate;
        this.stateId = stateId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets order date.
     *
     * @return the order date
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Sets order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets state id.
     *
     * @return the state id
     */
    public int getStateId() {
        return stateId;
    }

    /**
     * Sets state id.
     *
     * @param stateId the state id
     */
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public String toString() {
        return "Order:" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", stateId=" + stateId
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}