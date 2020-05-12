package roomreservation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name="payment", url="${api.url.payment}")
public interface PaymentService {

   // @RequestMapping(method = RequestMethod.POST, value = "/payments/{reservationId}", consumes = "application/json")
  //  void increaseStock(@PathVariable("productId") Long productId, int quantity);

}
