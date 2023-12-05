package cloudproject.cloudproject.controller;

import cloudproject.cloudproject.service.FrameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FrameControllerNew {

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @PostMapping("/api/frame")
//    public ResponseEntity<String> handleFrame(@RequestBody String frameData) {
//        // 프레임 데이터를 Flask 서버로 전달
//        String flaskServerUrl = "http://your-flask-server.com/api/process";
//        ResponseEntity<String> response = restTemplate.postForEntity(flaskServerUrl, frameData, String.class);
//
//        // Flask 서버로부터 받은 결과를 클라이언트에게 전달
//        return ResponseEntity.ok(response.getBody());
//    }
@PostMapping("/api/frame2")
public ResponseEntity<Map<String, String>> receiveImage(@RequestBody Map<String, String> imageData) {
    try {
        // Base64로 인코딩된 이미지 데이터 디코드
        String base64Image = imageData.get("image");
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

        // 바이트를 문자열로 변환
        String frameData = new String(decodedBytes);

        // 클라이언트에게 응답할 JSON 생성
        Map<String, String> jsonResponse = new HashMap<>();
        jsonResponse.put("result", "Frame processed successfully");
        jsonResponse.put("frameData", frameData);

        return ResponseEntity.ok(jsonResponse);
    } catch (Exception e) {
        // 에러가 발생한 경우
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error processing image: " + e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
}