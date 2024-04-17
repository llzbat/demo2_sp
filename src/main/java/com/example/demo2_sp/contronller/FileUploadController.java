package com.example.demo2_sp.contronller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/face")
public class FileUploadController {

    //转发
    @PostMapping("/register_forward")
    public ResponseEntity<String> Register_Image(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("UID") String UID) {
        System.out.println(UID);
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("上传失败，因为文件是空的.", HttpStatus.BAD_REQUEST);
        }
        // 创建一个包含文件的 MultiValueMap
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 将 MultipartFile 转换为字节数组
            byte[] fileBytes = file.getBytes();

            // 创建一个包含文件字节数组的 ByteArrayResource
            ByteArrayResource fileResource = new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename(); // 使用原始文件名
                }
            };

            body.add("file", fileResource);
            // 添加字符串数据部分
            body.add("UID", UID);
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // 构建请求体
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // 替换为实际的目标API地址
            String otherApiUrl = "http://192.168.0.112:8000/login/register/";

            ResponseEntity<String> response = restTemplate.exchange(
                    otherApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            System.out.println(response.toString());
            if (response.getStatusCode() == HttpStatus.OK) {

                return new ResponseEntity<>("YES", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("NO", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("上传失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recognize_forward")
    public ResponseEntity<String> Recognize_Image(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("上传失败，因为文件是空的.", HttpStatus.BAD_REQUEST);
        }
        try {
            RestTemplate restTemplate = new RestTemplate();
            // 将 MultipartFile 转换为字节数组
            byte[] fileBytes = file.getBytes();
            // 创建一个包含文件字节数组的 ByteArrayResource
            ByteArrayResource fileResource = new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename(); // 使用原始文件名
                }
            };

            // 创建一个包含文件的 MultiValueMap
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileResource);

            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // 构建请求体
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // 替换为实际的目标API地址
            String otherApiUrl = "http://192.168.72.19:8000/test/";

            ResponseEntity<String> response = restTemplate.exchange(
                    otherApiUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            System.out.println(response.toString());
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>("上传成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("上传失败，目标API返回错误：" + response.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("上传失败: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



