package com.example.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
import java.util.List;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = { DobValidator.class }
)
public @interface DobConstraint {
    String message() default "Invalid date of birth"; // Thông báo lỗi mặc định

    int min();

    Class<?>[] groups() default {}; // Nhóm validation (bắt buộc theo chuẩn JPA)

    Class<? extends Payload>[] payload() default {}; // Dữ liệu bổ sung (bắt buộc)
}
