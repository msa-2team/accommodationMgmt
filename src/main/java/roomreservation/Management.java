package roomreservation;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import roomreservation.config.kafka.KafkaProcessor;

@Entity
@Table(name="Management_table")
public class Management {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String customerName;
    private Long reservationId;
    private String cardInfo;
    private String roomInfo;
    private String reservationDate;
    private String requestDate;
    private String customerPhoneNumber;

    @PostRemove
    public void onPostRemove(){
        RoomRejected r = new RoomRejected();
        r.setReservationId(this.getReservationId());
        r.setCustomerName(this.getCustomerName());
        r.setCardInfo(this.getCardInfo());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(r);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }
        System.out.println("onPostRemove");
        System.out.println(json);

        KafkaProcessor processor = Application.applicationContext.getBean(KafkaProcessor.class);
        MessageChannel outputChannel = processor.outboundTopic();

        outputChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    @PostPersist
    public void onPostPersist(){

/*        RoomConfirmed r = new RoomConfirmed();
        r.setReservationId(this.getReservationId());
        r.setCustomerName(this.getCustomerName());
        r.setCardInfo(this.getCardInfo());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(r);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }
        System.out.println("onPostPersist");
        System.out.println(json);

        KafkaProcessor processor = Application.applicationContext.getBean(KafkaProcessor.class);
        MessageChannel outputChannel = processor.outboundTopic();

        outputChannel.send(MessageBuilder
                .withPayload(json)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());*/


        PaymentService paymentService = Application.applicationContext.getBean(PaymentService.class);

        Payment payment = new Payment();
        payment.setCustomerName(this.getCustomerName());
        payment.setCardInfo(this.getCardInfo());
        paymentService.requestPayment( payment);

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

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

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }






}
