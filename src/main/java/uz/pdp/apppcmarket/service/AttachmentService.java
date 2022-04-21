package uz.pdp.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.apppcmarket.entity.Attachment;
import uz.pdp.apppcmarket.entity.AttachmentContent;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.repository.AttachmentRepository;
import uz.pdp.apppcmarket.repository.AttachmetnContentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmetnContentRepository attachmetnContentRepository;

    public Result addAttachment(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        attachment.setName(file.getName());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment save = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(save);
        attachmetnContentRepository.save(attachmentContent);
        return new Result("Upload file", true);
    }

    public void getFile( Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if(optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalContent = attachmetnContentRepository.findByAttachmentId(id);
            if(optionalContent.isPresent()){
                AttachmentContent attachmentContent = optionalContent.get();
                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());
            }
        }
    }
}
