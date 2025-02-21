package com.fdm.pmsuibackend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author April Chou
 * @Classname PostionRequest
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/5 12:59
 */
@Component
@Data
public class PositionResponse {
    private List<PositionDto> positionData;

}
