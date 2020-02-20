package obslugaRaportow;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.sf.jasperreports.engine.JasperExportManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.Logger;

public class JasperReportOperations {

    private Logger logger = Logger.getLogger(JasperReportOperations.class);
    private String pathForTemplates = "C:\\Users\\Maciek\\Desktop\\Dialysis\\Dialysis - Java Project\\Szablony\\";

    public void makePDF(List<Map<String, ?>> maps, String fileName, boolean print,String templateName) {
        try {

            FileInputStream fis = new FileInputStream(pathForTemplates+templateName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(maps);

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

            if (print == true) {
                JasperPrintManager.printReport(jasperPrint, false);
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    
}
