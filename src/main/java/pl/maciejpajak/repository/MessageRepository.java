package pl.maciejpajak.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.maciejpajak.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    public Page<Message> findByRecipientId(long id, Pageable pageable);
    public Page<Message> findBySenderId(long id, Pageable pageable);
    public Long countByRecipientIdAndRead(long recipientId, boolean read);
    
}
