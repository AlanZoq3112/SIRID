package mx.edu.utez.sirid.service.Reports;


import mx.edu.utez.sirid.utils.reports.JasperReportManager;
import mx.edu.utez.sirid.controller.Reports.ReportDTO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class ReportsService {

    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    //listado de profesores en pdf
    @Transactional(rollbackFor = {SQLException.class})
     public ReportDTO generateTeacherListPDF(Map<String,Object> params) throws SQLException, JRException, IOException {
        String name="Listado_de_Docentes";
        String extension=".pdf";
        ByteArrayOutputStream stream= reportManager.export(name, "pdf",params, dataSource.getConnection());
        byte[] bytes= stream.toByteArray();
        ReportDTO dto = new ReportDTO(name+extension, new ByteArrayInputStream(bytes) , bytes.length);
        return dto;
    }

    //listado de profesores en excel
    @Transactional(rollbackFor = {SQLException.class})
    public ReportDTO generateTeacherListExcel(Map<String,Object> params) throws SQLException, JRException, IOException {
        String name="Listado_de_Docentes";
        String extension=".xlsx";
        ByteArrayOutputStream stream= reportManager.export(name, "excel",params, dataSource.getConnection());
        byte[] bytes= stream.toByteArray();
        ReportDTO dto = new ReportDTO(name+extension, new ByteArrayInputStream(bytes) , bytes.length);
        return dto;
    }

    //listado de personal de soporte en pdf
    @Transactional(rollbackFor = {SQLException.class})
    public ReportDTO generateSupportListPDF(Map<String,Object> params) throws SQLException, JRException, IOException {
        String name="Listado_Personal_Soporte";
        String extension=".pdf";
        ByteArrayOutputStream stream= reportManager.export(name, "pdf",params, dataSource.getConnection());
        byte[] bytes= stream.toByteArray();
        ReportDTO dto = new ReportDTO(name+extension, new ByteArrayInputStream(bytes) , bytes.length);
        return dto;
    }

    //lisatdo de personal de soporte en excel
    @Transactional(rollbackFor = {SQLException.class})
    public ReportDTO generateSupportListExcel(Map<String,Object> params) throws SQLException, JRException, IOException {
        String name="Listado_Personal_Soporte";
        String extension=".xlsx";
        ByteArrayOutputStream stream= reportManager.export(name, "excel",params, dataSource.getConnection());
        byte[] bytes= stream.toByteArray();
        ReportDTO dto = new ReportDTO(name+extension, new ByteArrayInputStream(bytes) , bytes.length);
        return dto;
    }

    //graficas en pdf (Corregir)
    @Transactional(rollbackFor = {SQLException.class})
    public ReportDTO statisticsReportPDF(Map<String,Object> params) throws SQLException, JRException, IOException {
        System.out.println(params);
        String name="ReporteDeEstadisticas";
        String extension=".pdf";
        ByteArrayOutputStream stream= reportManager.export(name, "pdf",params, dataSource.getConnection());
        byte[] bytes= stream.toByteArray();
        ReportDTO dto = new ReportDTO(name+extension, new ByteArrayInputStream(bytes) , bytes.length);
        return dto;
    }

    //graficas en excel (Corregir)
    @Transactional(rollbackFor = {SQLException.class})
    public ReportDTO statisticsReportExcel(Map<String,Object> params) throws SQLException, JRException, IOException {
        String name="ReporteDeEstadisticas";
        String extension=".xlsx";
        ByteArrayOutputStream stream= reportManager.export(name, "excel",params, dataSource.getConnection());
        byte[] bytes= stream.toByteArray();
        ReportDTO dto = new ReportDTO(name+extension, new ByteArrayInputStream(bytes) , bytes.length);
        return dto;
    }


}
