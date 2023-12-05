package cloudproject.cloudproject;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.PropertySource;
//
//@SpringBootApplication
//@PropertySource("classpath:application.yml")
//public class CloudProjectApplication {
//
//	public static void main(String[] args) {
//
//		SpringApplication.run(CloudProjectApplication.class, args);
//	}
//}
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class CloudProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudProjectApplication.class, args);
	}


	@CrossOrigin(origins = "http://localhost:3000")
	@RestController
	public class ImageController {

		@CrossOrigin
		@PostMapping("/api/receiveImage")
		public ImageResponse receiveImage(@RequestBody ImageRequest request) {
			// 여기에서 이미지 처리 또는 다른 작업 수행
			// 여기에서는 간단히 랜덤 숫자를 생성하여 반환
			int randomNumber = (int) (Math.random() * 100);

			// 모의 처리 지연 (예: 비동기 작업을 시뮬레이션하기 위해)
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return new ImageResponse("Received image, random number: " + randomNumber);
		}
	}

	public static class ImageRequest {
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
	}

	public static class ImageResponse {
		private String text;

		public ImageResponse(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}}

