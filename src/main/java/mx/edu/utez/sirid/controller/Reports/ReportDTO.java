package mx.edu.utez.sirid.controller.Reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDTO {
    private String fileName;
    private ByteArrayInputStream stream;
    private int length;


}
