package com.example.YachtAndPrivateJetRental.Controller;

import com.example.YachtAndPrivateJetRental.Request.JetRequest;
import com.example.YachtAndPrivateJetRental.Service.JetService;
import com.example.YachtAndPrivateJetRental.Util.RestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;

@Controller
@RequestMapping("/jet")
public class JetController {
    @Autowired
    private JetService service;

    public static final String SIZE = "10";
    public static final String PAGE = "1";
    public static final String MESSAGE = "Successful";
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> categories(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = PAGE) int page,
            @RequestParam(defaultValue = SIZE) int size
    ){
        return RestResponse.ok(service.findAll(),MESSAGE);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(
            @Valid @RequestBody JetRequest request, @RequestPart("image") List<MultipartFile> multipartFiles
    ){
        return RestResponse.ok(service.save(request, multipartFiles));
    }
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@RequestParam int id){
        return RestResponse.ok(service.delete(id));
    }
    @PostMapping(value = {"/id"},produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody JetRequest request){
        return RestResponse.ok(service.update(request, id));
    }
}
