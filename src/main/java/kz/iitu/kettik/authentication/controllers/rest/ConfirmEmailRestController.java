package kz.iitu.kettik.authentication.controllers.rest;

import kz.iitu.kettik.authentication.services.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConfirmEmailRestController {

    private final VerificationTokenService service;

    @GetMapping("/confirm")
    public void confirm(@RequestParam String token) {
        service.confirm(token);
    }
}
