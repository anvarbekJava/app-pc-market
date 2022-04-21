package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/upload")
public class AttachmetnController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping()
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.addAttachment(request);
        return ResponseEntity.status(result.getStatus()?200:409).body(result);
    }

    @GetMapping(value = "/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFile(id, response);
    }
}
