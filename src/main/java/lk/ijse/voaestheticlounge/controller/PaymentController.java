package lk.ijse.voaestheticlounge.controller;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.voaestheticlounge.dto.*;
import lk.ijse.voaestheticlounge.service.OrderDetailService;
import lk.ijse.voaestheticlounge.service.OrderService;
import lk.ijse.voaestheticlounge.service.PaymentService;
import lk.ijse.voaestheticlounge.service.UserService;
import lk.ijse.voaestheticlounge.util.JwtUtil;
import lk.ijse.voaestheticlounge.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Value("${app.domain}")
    private String appDomain;

    private final SecureRandom random = new SecureRandom();

  /*  public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }*/
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  @GetMapping("/init")
  public ResponseEntity<ResponseDTO> initializePayment(@RequestParam double amount,
                                                       @RequestParam String email, @RequestHeader("Authorization") String token) {


      jwtUtil.getUserRoleCodeFromToken(token.substring(7));

      System.out.println(email);
      UserDTO userDTO = userService.findByEmail(email);

      String orderCode = String.format("%06d", random.nextInt(1000000));
      OrderDTO orderDTO = new OrderDTO();
      orderDTO.setUserId(userDTO.getId());
      orderDTO.setOrderDate(LocalDate.now());
      orderDTO.setTotalPrice(amount);
      orderDTO.setOrderCode(orderCode);
      orderDTO.setStatus("PENDING");
      orderService.save(orderDTO);

      String merchantID     = "1230013";
      String merchantSecret = "Mjg1NjQxNDI3NzI5MTc3NDY4OTcxNjU2NDk2NjMzMzAwNTA2NjkyNw==";
      String orderID        = orderCode;
      double amount1         = amount;
      String currency       = "LKR";
      DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
      String amountFormatted = df.format(amount);
      String hash    = getMd5(merchantID + orderID + amountFormatted + currency + getMd5(merchantSecret));
      System.out.println("Generated Hash: " + hash);


      PayhereDTO payehereDTO = new PayhereDTO();
      payehereDTO.setId(orderID);
      payehereDTO.setMerchantId("1230013");
      payehereDTO.setCurrency("LKR");
      payehereDTO.setReturnUrl(appDomain + "/PaymentSuccess.html");
      payehereDTO.setCancelUrl(appDomain + "/PaymentCancel.html");
      payehereDTO.setNotifyUrl("https://971f-2402-4000-2280-73a1-ac80-4ed6-667b-463a.ngrok-free.app/api/v1/payment/notify");
//        https://localhost:8080/api/v1/paymentent/notify
      payehereDTO.setHash(hash);

      payehereDTO.setAmount(amount1);
      payehereDTO.setFirstName("MR/MRS");
      payehereDTO.setLastName(userDTO.getUsername());
      payehereDTO.setEmail(email);
      payehereDTO.setPhone("0764545760");
      payehereDTO.setAddress("NO 59, goldern");
      payehereDTO.setCity("matara");
      payehereDTO.setCountry("Sri Lanka");
      System.out.println("payment "+payehereDTO);
      System.out.println(hash);
      System.out.println("merchantID: " + merchantID);
      System.out.println("orderID: " + orderID);
      System.out.println("amountFormatted: " + amountFormatted);
      System.out.println("currency: " + currency);
      System.out.println("merchantSecret: " + merchantSecret);
      System.out.println("MD5(merchantSecret): " + getMd5(merchantSecret));

      String rawString = merchantID + orderID + amountFormatted + currency + getMd5(merchantSecret);
      System.out.println("Raw String used for hash: " + rawString);

      String hash1 = getMd5(rawString);
      System.out.println("Final Hash: " + hash1);

      return ResponseEntity.status(HttpStatus.OK)
              .body(new ResponseDTO(VarList.OK, "Payment Initiated",payehereDTO));
  }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(@RequestParam("payment_id") String paymentId) {
        System.out.println("Payment Success: " + paymentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Payment Successful! Payment ID: " + paymentId);
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Payment Cancelled!");
    }

//    @PostMapping("/notify")
//    public ResponseEntity<Void> paymentNotify(@RequestBody String notificationData) {
//        System.out.println("Payment Notification: " + notificationData);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @PostMapping("/notify")
    public ResponseEntity<String> handlePaymentNotification(HttpServletRequest request) {
        try {
            String orderId = request.getParameter("order_id");
            String paymentId = request.getParameter("payment_id");
            String status = request.getParameter("status");
            String amount = request.getParameter("payhere_amount");
            String method = request.getParameter("payment_method");
            String paymentDate = request.getParameter("payhere_payment_date");

            if ("2".equals(status)) {
                // ✅ Payment success
                // 👉 Save order & payment info to DB
                System.out.println("✅ Payment Successful! Order ID: " + orderId);

                // orderService.saveOrderAfterPayment(...); // Optional logic here
            } else {
                // ❌ Payment failed or canceled
                System.out.println("❌ Payment not successful. Status: " + status);
            }

            return ResponseEntity.ok("Notification received");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }


}
