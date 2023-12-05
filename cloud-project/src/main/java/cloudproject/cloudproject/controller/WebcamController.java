package cloudproject.cloudproject.controller;

import cloudproject.cloudproject.service.WebcamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebcamController {

    private final WebcamService webcamService;

    public WebcamController(cloudproject.cloudproject.service.WebcamService webcamService) {
        this.webcamService = webcamService;
    }

    @GetMapping("/webcam2")
    public String showWebcamPage() {
        return "webcam";
    }

    @PostMapping ("/webcam")
    public String processWebcamFrame(@RequestBody String frameData, Model model) {
        // 프레임 데이터를 WebcamService를 통해 Flask 서버로 전송하고 응답을 받음
        String result = webcamService.sendFrameToFlask(frameData);

        // 결과를 모델에 추가하여 클라이언트에 반환
        model.addAttribute("webcamResult", result);

        // 여기서 "webcamResult"는 웹캠 처리 결과를 템플릿에서 사용할 이름입니다.
        return "webcamResult";
    }
}
