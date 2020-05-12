package roomreservation;

import roomreservation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {

        @Autowired
        ManagementRepository managementRepository;

        @StreamListener(KafkaProcessor.INPUT)
        public void onEventByString(@Payload Reserved reserved){
            if( reserved.getEventType().equals("Reserved")){
                Management p = new Management();
                p.setId(reserved.getReservationId());
                p.setCustomerName(reserved.getCustomerName());
                p.setCardInfo(reserved.getCardInfo());
                p.setCustomerPhoneNumber(reserved.getCustomerPhoneNumber());
                p.setRequestDate(reserved.getRequestDate());
                p.setReservationDate(reserved.getReservationDate());
                p.setRoomInfo(reserved.getRoomInfo());
                managementRepository.save(p);
            }
            else if(reserved.getEventType().equals("ReservationCanceled")){
                Management p = new Management();
                p.setId(reserved.getReservationId());
                p.setCustomerName(reserved.getCustomerName());
                p.setCardInfo(reserved.getCardInfo());
                p.setCustomerPhoneNumber(reserved.getCustomerPhoneNumber());
                p.setRequestDate(reserved.getRequestDate());
                p.setReservationDate(reserved.getReservationDate());
                p.setRoomInfo(reserved.getRoomInfo());
                managementRepository.delete(p);
            }

      //     System.out.println(reserved.getEventType());
        }

    }
