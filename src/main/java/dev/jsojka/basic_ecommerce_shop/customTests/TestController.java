package dev.jsojka.basic_ecommerce_shop.customTests;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Hello admin!");
    }

    @GetMapping("/buyer")
    @PreAuthorize("hasAuthority('ROLE_BUYER')")
    public ResponseEntity<String> buyerEndpoint() {
        return ResponseEntity.ok("Hello buyer!");
    }

    @GetMapping("/seller")
    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    public ResponseEntity<String> sellerEndpoint() {
        return ResponseEntity.ok("Hello seller!");
    }
}
