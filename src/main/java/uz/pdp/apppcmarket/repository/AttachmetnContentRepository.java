package uz.pdp.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppcmarket.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmetnContentRepository extends JpaRepository<AttachmentContent, Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
