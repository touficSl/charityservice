package com.service.charity.controller;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.service.charity.config.Constants;

@RestController
public class CheckoutController {

    @GetMapping("/checkout")
    public Map<String, String> getCheckoutUrl() {
        Map<String, String> response = new HashMap<>();

        String productName = "Test Product";
        String price = "10.00";
        String currency = "USD";
        String orderId = "ORDER-" + System.currentTimeMillis();

        URI uri = UriComponentsBuilder
            .fromUriString("https://secure.2checkout.com/checkout/purchase")
            .queryParam("sid", Constants.SELLER_ID)
            .queryParam("mode", "2CO")
            .queryParam("merchant_order_id", orderId)
            .queryParam("li_0_type", "product")
            .queryParam("li_0_name", productName)
            .queryParam("li_0_price", price)
            .queryParam("currency", currency)
            .queryParam("return_url", "https://localhost/thankyou.html")
            .build().toUri();

        response.put("checkout_url", uri.toString());
        return response;
    }

    @PostMapping("/ipn")
    public String handleIPN(@RequestParam Map<String, String> params) {
        String saleId = params.get("sale_id");
        String invoiceId = params.get("invoice_id");
        String receivedHash = params.get("md5_hash");
        String vendorId = params.get("vendor_id");

        String generatedHash = generateMd5Hash(saleId, vendorId, invoiceId);

        if (generatedHash.equalsIgnoreCase(receivedHash)) {
            // Payment is verified
            System.out.println("✅ IPN Verified - Order Paid");
            // You can update your DB, notify user, etc.
            return "IPN received and verified";
        } else {
            System.out.println("❌ Invalid IPN - Hash mismatch");
            return "Invalid signature";
        }
    }

    private String generateMd5Hash(String saleId, String vendorId, String invoiceId) {
        String stringToHash = saleId + vendorId + invoiceId + Constants.SECRET_WORD;
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            SecretKeySpec secretKey = new SecretKeySpec(Constants.SECRET_WORD.getBytes(), "HmacMD5");
            mac.init(secretKey);
            byte[] hashBytes = mac.doFinal(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}
