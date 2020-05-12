package roomreservation;

public class RoomConfirmed extends AbstractEvent {

    private Long id;
    private String customerName;
    private Long reservationId;
    private String cardInfo;

    public RoomConfirmed(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() { return customerName;   }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }


}
