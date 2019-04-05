package com.keycloak.accountservice.controller;

import com.keycloak.accountservice.config.EncodeDecode;
import com.keycloak.accountservice.model.AppConfig;
import com.keycloak.accountservice.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/settings")
public class SettingController {

    @Autowired
    private AppConfigService appConfigService;

    @GetMapping("/{key}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> findById(@PathVariable("key") String key) {
        return new ResponseEntity<>(appConfigService.findByKey(key), HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK )
    @GetMapping(value = "/terms", produces="text/html")
    public ResponseEntity<?> terms() {
        try {
            return new ResponseEntity<>(EncodeDecode.decodeString(appConfigService.findByKey("term_condition").getAppValue()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("{'message':'Exception'}", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK )
    @GetMapping(value = "/policy", produces="text/html")
    public ResponseEntity<?> policy() {
        try {
            return new ResponseEntity<>(EncodeDecode.decodeString(appConfigService.findByKey("policy").getAppValue()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("{'message':'Exception'}", HttpStatus.EXPECTATION_FAILED);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(appConfigService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> insert(@Valid @RequestBody AppConfig item) {
        return new ResponseEntity<>(appConfigService.insert(item), HttpStatus.OK);
    }

    @PostMapping("/encoded")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<?> insert(AppConfig appConfig, @Param("key") String key, @Param("description") String description, @RequestBody String body) {

        try {
            appConfig.setAppKey(key);
            appConfig.setDescription(description);
            appConfig.setAppValue(EncodeDecode.encodeString(body));
            return new ResponseEntity<>(appConfigService.insert(appConfig), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>("{'message':'Exception'}", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
