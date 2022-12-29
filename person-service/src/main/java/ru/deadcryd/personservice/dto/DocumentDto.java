package ru.deadcryd.personservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class DocumentDto {

    private String documentType;

    private List<DocumentFieldDataDto> fieldData;
}
