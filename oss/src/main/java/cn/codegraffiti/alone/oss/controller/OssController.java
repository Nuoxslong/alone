package cn.codegraffiti.alone.oss.controller;

import cn.codegraffiti.alone.core.R;
import cn.codegraffiti.alone.oss.service.OssService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/oss")
public class OssController {

    final OssService ossService;

    @PostMapping(value = "/upload")
    public R<String> upload(@RequestParam MultipartFile file) {
        return ossService.tempStorage(file);
    }

    @GetMapping(value = "/{identifier}")
    public R<Void> get(HttpServletResponse response, @PathVariable String identifier) throws IOException {
        InputStream inputStream = this.ossService.get(identifier);
        IOUtils.copy(inputStream, response.getOutputStream());
        inputStream.close();
        return R.ok();
    }

}
