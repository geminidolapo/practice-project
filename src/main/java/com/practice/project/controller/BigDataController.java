package com.practice.project.controller;

import lombok.Cleanup;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.List;

@RestController
public class BigDataController {

    @GetMapping("/huge-data")
    public ResponseEntity<StreamingResponseBody> getBigData() {
        StreamingResponseBody stream = outputStream -> {
            @Cleanup BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            try {
                List<String> dataList = fetchDataFromDatabase();

                for (String data : dataList) {
                    writer.write(data);
                    writer.newLine(); // Write new line if needed
                    writer.flush(); // Flush after each write (optional, could flush less frequently)
                }
            } catch (Exception e) {
                // Log and handle any errors that occur during streaming
                e.printStackTrace();
            }
        };

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN) // Adjust content type as per your data format
                .body(stream);
    }

    // Simulate fetching large data from the database
    private List<String> fetchDataFromDatabase() {
        // Replace with your database logic
        return List.of("Line 1", "Line 2", "Line 3", "Line 4", "Line 5");
    }
}
