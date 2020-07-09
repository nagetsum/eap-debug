package org.example.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class UploadController {
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get("/tmp/" + fileName));
            redirectAttributes.addFlashAttribute("filename", fileName);
            redirectAttributes.addFlashAttribute("size", file.getSize());
            return "redirect:/success";
        }

        return "redirect:/failed";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/failed")
    public String failed() {
        return "failed";
    }
}
