package co.edu.icesi.dev.saamfi.controller.implementation.baseEntities;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.dev.saamfi.controller.security.JwtTokenProvider;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*")
public class PublicSaamfiKeyController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("/publicKey")
    public ResponseEntity<?> getPublicKey() {
        byte[] key = tokenProvider.getPublicKey();
        String kp = Arrays.toString(key);
        return ResponseEntity.ok(kp);
    }
}
