package com.example.springboot;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
public class Controller {
        @GetMapping("/")
        public String hello() {
            return "Welcome to Spring API !";
        }

        @PostMapping("/converter")
        public ResponseEntity<byte[]> getImage(@RequestBody byte[] image) throws IOException {
            Convert object = new Converter(image);
            byte[] out = object.Convert();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(out);
        }

}
