package ru.deadcryd.personservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.deadcryd.personservice.dto.DocumentDto;
import ru.deadcryd.personservice.entity.Document;
import ru.deadcryd.personservice.entity.DocumentType;

@Mapper(componentModel = "jakarta")
public interface DocumentMapper {

    @Mapping(target = "documentType", expression = "java(documentType(documentDto))")
    Document documentDtoToDocument(DocumentDto documentDto);

    @Mapping(target = "documentType", source = "document.documentType.typeName")
    DocumentDto documentToDocumentDto(Document document);

    default DocumentType documentType(DocumentDto documentDto) {
        DocumentType documentType = new DocumentType();
        documentType.setTypeName(documentDto.getDocumentType());
        return documentType;
    }
}
