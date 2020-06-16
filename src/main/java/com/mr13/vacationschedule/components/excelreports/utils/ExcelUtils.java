package com.mr13.vacationschedule.components.excelreports.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static com.mr13.vacationschedule.core.constants.StringConstants.XLSX_MIME_TYPE;

@Component
public class ExcelUtils {

  public HttpEntity<byte[]> downloadFile(byte[] content, String fileName) {
    return downloadFile(content, fileName, XLSX_MIME_TYPE);
  }

  public HttpEntity<byte[]> downloadFile(byte[] content, String fileName, String mimeType) {

    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType("application", mimeType));
    header.set("Content-Disposition", "inline; filename=" + fileName);
    header.setContentLength(content.length);

    return new HttpEntity<>(content, header);
  }
}
