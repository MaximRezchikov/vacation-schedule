package com.mr13.vacationschedule.components.excelreports.workbook;

import com.mr13.vacationschedule.components.employee.domain.Employee;
import com.mr13.vacationschedule.components.excelreports.dto.ExcelVacationForm;
import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.service.VacationService;
import com.mr13.vacationschedule.core.errors.ExcelReportException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_TIME_FORMAT;
import static com.mr13.vacationschedule.core.constants.StringConstants.DATE_FORMAT;

@Component
@RequiredArgsConstructor
public class ExcelVacationReportCreator {

  private final VacationService vacationService;
  private XSSFWorkbook workbook;
  private XSSFSheet sheet;
  private Cell cell;

  public byte[] createExcel(ExcelVacationForm excelVacationForm) {

    this.workbook = new XSSFWorkbook();
    this.sheet = workbook.createSheet("Vacation List");

    fillHeadersAndCells(excelVacationForm);
    setAutoFilterToAllColumns();

    return generateXls(workbook);
  }

  private byte[] generateXls(XSSFWorkbook workbook) {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    String currentDateTime = LocalDateTime.now().format(dateTimeFormatter);

    File file = new File("C:/file_" + currentDateTime + ".xlsx");
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file)) {

      workbook.write(outputStream);
      byte[] bytes = outputStream.toByteArray();
      fileOutputStream.write(bytes);
      return bytes;
    } catch (IOException e) {
      throw new ExcelReportException("Something went wrong");
    }
  }

  private void setAutoFilterToAllColumns() {

    for (short i = sheet.getRow(0).getFirstCellNum(),
        end = sheet.getRow(0).getLastCellNum(); i < end; i++) {

      CellRangeAddress ca = new CellRangeAddress(0, sheet.getLastRowNum(),
          sheet.getRow(0).getFirstCellNum(),
          sheet.getRow(0).getLastCellNum());

      sheet.setAutoFilter(ca);
    }
  }

  private XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {

    XSSFFont font = workbook.createFont();
    font.setBold(true);

    return getXssfCellStyle(workbook, font);
  }

  private XSSFCellStyle createStyleForCells(XSSFWorkbook workbook) {

    XSSFFont font = workbook.createFont();
    font.setBold(false);

    return getXssfCellStyle(workbook, font);
  }

  private XSSFCellStyle getXssfCellStyle(XSSFWorkbook workbook, XSSFFont font) {

    XSSFCellStyle style = workbook.createCellStyle();
    style.setFont(font);
    HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
    style.setAlignment(horizontalAlignment);
    VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;
    style.setVerticalAlignment(verticalAlignment);
    short ROW_HEIGHT = 800;
    sheet.setDefaultRowHeight(ROW_HEIGHT);

    return style;
  }

  void fillHeadersAndCells(ExcelVacationForm excelVacationForm) {

    XSSFCellStyle style = createStyleForTitle(workbook);

    int headerNumber = 0;
    int rowIndex = 0;

    sheet.createFreezePane(0, 1);
    Row row = sheet.createRow(0);

    List<String> columnTitles = excelVacationForm.getColumnTitles();

    for (String title : columnTitles) {
      if (title != null) {
        cell = row.createCell(headerNumber, CellType.STRING);
        cell.setCellValue(title);
        cell.setCellStyle(style);
        sheet.setColumnWidth(headerNumber, 6000);

        headerNumber++;
      }
    }

    fillCells(excelVacationForm, rowIndex);
  }

  private void fillCells(ExcelVacationForm excelVacationForm, int rowIndex) {

    Row row;

    List<Long> vacationIds = excelVacationForm.getVacationIds();

    for (Long id : vacationIds) {

      rowIndex++;
      row = sheet.createRow(rowIndex);

      fillEmployeeName(id, 0, row);
      fillStartVacation(id, 1, row);
      fillEndVacation(id, 2, row);
      fillRemainingPeriod(id, 3, row);
    }
  }

  void fillEmployeeName(Long id, int header, Row newRow) {

    cell = newRow.createCell(header, CellType.STRING);
    cell.setCellStyle(createStyleForCells(workbook));

    Vacation vacation = vacationService.getOne(id);
    Employee employee = vacation.getEmployee();
    String username = employee.getUsername();

    cell.setCellValue(username);
  }

  void fillStartVacation(Long id, int header, Row newRow) {

    cell = newRow.createCell(header, CellType.STRING);
    cell.setCellStyle(createStyleForCells(workbook));
    Vacation vacation = vacationService.getOne(id);
    LocalDate startVacation = vacation.getStartVacation();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    String formatStartVacation = startVacation.format(dateTimeFormatter);
    cell.setCellValue(formatStartVacation);
  }

  void fillEndVacation(Long id, int header, Row newRow) {

    cell = newRow.createCell(header, CellType.STRING);
    cell.setCellStyle(createStyleForCells(workbook));
    Vacation vacation = vacationService.getOne(id);
    LocalDate endVacation = vacation.getEndVacation();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    String formatEndVacation = endVacation.format(dateTimeFormatter);
    cell.setCellValue(formatEndVacation);
  }

  void fillRemainingPeriod(Long id, int header, Row newRow) {

    Vacation vacation = vacationService.getOne(id);
    LocalDate currentDate = LocalDate.now();
    LocalDate endVacation = vacation.getEndVacation();
    Period between = Period.between(currentDate, endVacation);
    int days = between.getDays();

    cell = newRow.createCell(header, CellType.NUMERIC);
    cell.setCellStyle(createStyleForCells(workbook));
    cell.setCellValue(days);
  }
}
