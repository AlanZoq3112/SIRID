package mx.edu.utez.sirid.controller.Reports;

import mx.edu.utez.sirid.service.Reports.ReportsService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api-sirid/reports/")
@CrossOrigin(origins = {"*"})
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    //Obtener Lista de docentes en PDF
    @GetMapping("/TeacherList/pdf")
    public ResponseEntity<Resource> generateTeacherListPDF(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
       ReportDTO reportDTO = reportsService.generateTeacherListPDF(params);
        InputStreamResource streamResource= new InputStreamResource(reportDTO.getStream());
        MediaType mediaType;
        mediaType= MediaType.APPLICATION_PDF;

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportDTO.getFileName() + "\"")
                .contentLength(reportDTO.getLength()).contentType(mediaType).body(streamResource);
    }

    //Obtener litsa de docentes en archivo Excell
    @GetMapping("/TeacherList/excel")
    public ResponseEntity<Resource> generateTeacherListExcel(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
        ReportDTO reportDTO = reportsService.generateTeacherListExcel(params);
        InputStreamResource streamResource= new InputStreamResource(reportDTO.getStream());
        MediaType mediaType;
        mediaType= MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportDTO.getFileName() + "\"")
                .contentLength(reportDTO.getLength()).contentType(mediaType).body(streamResource);
    }

    //Obtener lista de personal de soporte en PDF
    @GetMapping("/supportList/pdf")
    public ResponseEntity<Resource> generateSupportListPDF(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
        ReportDTO reportDTO = reportsService.generateSupportListPDF(params);
        InputStreamResource streamResource= new InputStreamResource(reportDTO.getStream());
        MediaType mediaType;
        mediaType= MediaType.APPLICATION_PDF;

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportDTO.getFileName() + "\"")
                .contentLength(reportDTO.getLength()).contentType(mediaType).body(streamResource);
    }

    //Obtener lista de personal de soporte en excel
    @GetMapping("/supportList/excel")
    public ResponseEntity<Resource> generateSupportListExcel(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
        ReportDTO reportDTO = reportsService.generateSupportListExcel(params);
        InputStreamResource streamResource= new InputStreamResource(reportDTO.getStream());
        MediaType mediaType;
        mediaType= MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportDTO.getFileName() + "\"")
                .contentLength(reportDTO.getLength()).contentType(mediaType).body(streamResource);
    }

    //reporte de estadisticas de acuerdo a u ranfo de fechas  en pdf
    @GetMapping("/statistics/pdf")
    public ResponseEntity<Resource> statisticsReportListPDF(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
        ReportDTO reportDTO = reportsService.statisticsReportPDF(params);
        InputStreamResource streamResource= new InputStreamResource(reportDTO.getStream());
        MediaType mediaType;
        mediaType= MediaType.APPLICATION_PDF;

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportDTO.getFileName() + "\"")
                .contentLength(reportDTO.getLength()).contentType(mediaType).body(streamResource);
    }

    //reporte de estadisticas de acuerdo a u ranfo de fechas  en excell
    @GetMapping("/statistics/excel")
    public ResponseEntity<Resource> statisticsReportListexcel(@RequestParam Map<String,Object> params) throws JRException, SQLException, IOException {
        ReportDTO reportDTO = reportsService.statisticsReportExcel(params);
        InputStreamResource streamResource= new InputStreamResource(reportDTO.getStream());
        MediaType mediaType;
        mediaType= MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + reportDTO.getFileName() + "\"")
                .contentLength(reportDTO.getLength()).contentType(mediaType).body(streamResource);
    }
}
