package com.project.feedbackservice.repository;

import com.project.feedbackservice.entity.ValidationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationStatusRepository extends JpaRepository<ValidationStatus, Long>
{
    ValidationStatus findByUserIdAndAndOrderId(String userId, String orderId);
}
