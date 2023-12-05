package cloudproject.cloudproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebcamService {

    @Value("${flask.server.url}") //yml파일만 건들면 됨.
    private String flaskServerUrl;

    private final WebClient webClient;

    public WebcamService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(flaskServerUrl).build();
    }

    public String sendFrameToFlask(String frameData) {
        try {
            // HTTP 요청을 위한 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Flask 서버로 POST 요청
            String result = webClient.post()
                    .uri("/webcam") // 이 부분은 실제 엔드포인트에 맞게 변경해야 합니다.
                    .headers(httpHeaders -> httpHeaders.putAll(headers))
                    .body(BodyInserters.fromValue(frameData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // 실제 프로덕션 코드에서는 블록을 피하는 것이 좋습니다.

            // Flask 서버 응답 반환
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "전송 오류";
        }
    }
}


