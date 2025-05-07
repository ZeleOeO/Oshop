package com.zele.ishop.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zele.ishop.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderUserViewDto {
    private Double totalAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm::ss")
    private LocalDateTime orderDate;
    private Status status;
    private String userName;
}
