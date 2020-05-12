package roomreservation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name="payment", url="http://localhost:8083")
public interface PaymentService {

    @RequestMapping(method = RequestMethod.POST, value = "/payments", consumes = "application/json")
    void requestPayment(@RequestBody Payment payment);

    @RequestMapping(method = RequestMethod.POST, value = "/payments", consumes = "application/json")
    void requestPayments(@RequestParam Long reservationId, String cardInfo, String customerName);

}
