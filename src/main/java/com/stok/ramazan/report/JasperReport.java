package com.stok.ramazan.report;

import com.stok.ramazan.helper.EnumUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Created by Ramazan on 17.8.2016.
 */
public class JasperReport {
    private static final Logger LOGGER = LoggerFactory.getLogger(JasperReport.class);

    public String createReport(final EnumUtil.JasperReportType reportType, String reportName, List lstCollection,
                               HashMap<String, String> mapColumnNames, HashMap<String, Class> map, PageType pageType,
                               PageOrientation pageOrientation) {

        ColumnBuilder[] columnBuilder = createColumnBuilder(map, mapColumnNames);
        JRDataSource dataSource = new JRBeanCollectionDataSource(lstCollection);
        final JasperReportBuilder jasperReportBuilder = createJasperBuilder(dataSource, columnBuilder, pageType,
                pageOrientation, reportName, reportType);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = jasperReportBuilder.toJasperPrint();
        } catch (DRException e) {
            e.printStackTrace();
        }
        final JasperPrint finalJasperPrint = jasperPrint;
        return encodeReport(finalJasperPrint, reportType);
    }

    private String encodeReport(final JasperPrint finalJasperPrint, final EnumUtil.JasperReportType reportType) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() {
            public void write(OutputStream output) throws IOException, WebApplicationException {
                try {
                    if (reportType == EnumUtil.JasperReportType.EXCEL) {
                        JRXlsxExporter docxExporter = new JRXlsxExporter();
                        docxExporter.setExporterInput(new SimpleExporterInput(finalJasperPrint));
                        docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                        SimpleXlsxReportConfiguration configXlsx = new SimpleXlsxReportConfiguration();
                        configXlsx.setDetectCellType(true);
                        configXlsx.setRemoveEmptySpaceBetweenColumns(true);
                        configXlsx.setRemoveEmptySpaceBetweenRows(true);
                        configXlsx.setCollapseRowSpan(true);
                        configXlsx.setWhitePageBackground(false);
                        docxExporter.setConfiguration(configXlsx);
                        docxExporter.exportReport();
                    } else if (reportType == EnumUtil.JasperReportType.PDF) {
                        JRPdfExporter pdfExporter = new JRPdfExporter();
                        pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, finalJasperPrint);
                        pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                        pdfExporter.exportReport();
                    } else if (reportType == EnumUtil.JasperReportType.WORD) {
                        JRDocxExporter docxExporter = new JRDocxExporter();
                        docxExporter.setExporterInput(new SimpleExporterInput(finalJasperPrint));
                        docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));

                        SimpleDocxReportConfiguration configDocx = new SimpleDocxReportConfiguration();
                        configDocx.setOverrideHints(false);
                        configDocx.setFlexibleRowHeight(true);
                        configDocx.setIgnoreHyperlink(false);

                        docxExporter.setConfiguration(configDocx);
                        docxExporter.exportReport();

                    } else if (reportType == EnumUtil.JasperReportType.HTML) {
                        HtmlExporter exporter = new HtmlExporter();
                        exporter.setExporterInput(new SimpleExporterInput(finalJasperPrint));
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                        exporter.exportReport();
                    }

                } catch (Exception e) {
                    LOGGER.info(e.getMessage());
                }
            }
        };
        return convertBase64OutputStream(stream);
    }

    public String convertBase64OutputStream(ByteArrayOutputStream outputStream) {
        byte[] exporterDataByte = outputStream.toByteArray();
        // Aşagıda java 8 de bulunmayan switch casede string işlemleri için 1.7
        // ye göre yazılmıştır
        return org.apache.commons.codec.binary.Base64.encodeBase64String(exporterDataByte);
    }

    public String createReportIReportwithSubReport(String masterReportFileName, String subReportFileName,
                                                   List lstGenericData, EnumUtil.JasperReportType reportType, String reportName, String parametreName,
                                                   String subDir) throws JRException {
        net.sf.jasperreports.engine.JasperReport jasperMasterReport = null;
        net.sf.jasperreports.engine.JasperReport jasperSubReport = null;
        try {
            jasperMasterReport = JasperCompileManager.compileReport(masterReportFileName);
            jasperSubReport = JasperCompileManager.compileReport(subReportFileName);
        } catch (JRException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(parametreName, jasperSubReport);
        parameters.put("SUBREPORT_DIR", subDir);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lstGenericData);

        final JasperPrint finalJasperPrint = JasperFillManager.fillReport(jasperMasterReport, parameters,
                beanColDataSource);
        return encodeReport(finalJasperPrint, reportType);
    }

    public String createReportIReportswithSubReportList(String masterReportFileName, String masterReportFileName1,
                                                        String masterReportFileName2, Map<String, String> mapSubReportFileName, List lstGenericData, List lstTeblig,
                                                        EnumUtil.JasperReportType reportType, String reportName, Map<String, String> mapParametreName,
                                                        String subReportDir) throws JRException {
        net.sf.jasperreports.engine.JasperReport jasperMasterReport = null;
        net.sf.jasperreports.engine.JasperReport jasperMasterReport1 = null;
        net.sf.jasperreports.engine.JasperReport jasperMasterReport2 = null;
        List<net.sf.jasperreports.engine.JasperReport> lstSubJasper = new ArrayList<>();
        List<String> lstParametreName = new ArrayList<>();
        try {

            jasperMasterReport = JasperCompileManager.compileReport(masterReportFileName);

            jasperMasterReport1 = JasperCompileManager.compileReport(masterReportFileName1);

            jasperMasterReport2 = JasperCompileManager.compileReport(masterReportFileName2);

            for (Map.Entry<String, String> subReportFileName : mapSubReportFileName.entrySet()) {
                lstSubJasper.add(JasperCompileManager.compileReport(subReportFileName.getValue()));
                lstParametreName.add(mapParametreName.get(subReportFileName.getKey()));
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<String, Object>();
        for (int i = 0; i < lstSubJasper.size(); i++) {
            String parametreName = lstParametreName.get(i);
            net.sf.jasperreports.engine.JasperReport jasperSubReport = lstSubJasper.get(i);
            parameters.put(parametreName, jasperSubReport);
        }
        parameters.put("SUBREPORT_DIR", subReportDir);
        Map<String, Object> parameters1 = new HashMap<String, Object>();
        for (int i = 0; i < lstSubJasper.size(); i++) {
            String parametreName = lstParametreName.get(i);
            net.sf.jasperreports.engine.JasperReport jasperSubReport = lstSubJasper.get(i);
            parameters1.put(parametreName, jasperSubReport);
        }
        parameters1.put("SUBREPORT_DIR", subReportDir);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lstGenericData);
        JRBeanCollectionDataSource beanColDataSource1 = new JRBeanCollectionDataSource(lstGenericData);
        JRBeanCollectionDataSource beanColDataSource2 = new JRBeanCollectionDataSource(lstTeblig);

        final JasperPrint finalJasperPrint = JasperFillManager.fillReport(jasperMasterReport, parameters,
                beanColDataSource);
        final JasperPrint finalJasperPrint1 = JasperFillManager.fillReport(jasperMasterReport1, parameters1,
                beanColDataSource1);
        final JasperPrint finalJasperPrint2 = JasperFillManager.fillReport(jasperMasterReport2,
                new HashMap<String, Object>(), beanColDataSource2);

        List pages = finalJasperPrint.getPages();
        for (int i = 0; i < pages.size(); i++) {
            JRPrintPage object = (JRPrintPage) pages.get(i);
            finalJasperPrint1.addPage(object);
        }

        List pages1 = finalJasperPrint2.getPages();
        for (int j = 0; j < pages1.size(); j++) {
            JRPrintPage object = (JRPrintPage) pages1.get(j);
            finalJasperPrint1.addPage(object);
        }
        return encodeReport(finalJasperPrint, reportType);
    }

    public String createReportIReportwithSubReportList(String masterReportFileName,
                                                       Map<String, String> mapSubReportFileName, List lstGenericData, EnumUtil.JasperReportType reportType,
                                                       String reportName, Map<String, String> mapParametreName, String subReportDir) throws JRException {

        net.sf.jasperreports.engine.JasperReport jasperMasterReport = null;
        List<net.sf.jasperreports.engine.JasperReport> lstSubJasper = new ArrayList<>();
        List<String> lstParametreName = new ArrayList<>();
        try {

            jasperMasterReport = JasperCompileManager.compileReport(masterReportFileName);

            for (Map.Entry<String, String> subReportFileName : mapSubReportFileName.entrySet()) {
                lstSubJasper.add(JasperCompileManager.compileReport(subReportFileName.getValue()));
                lstParametreName.add(mapParametreName.get(subReportFileName.getKey()));
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

        Map<String, Object> parameters = new HashMap<String, Object>();
        for (int i = 0; i < lstSubJasper.size(); i++) {
            String parametreName = lstParametreName.get(i);
            net.sf.jasperreports.engine.JasperReport jasperSubReport = lstSubJasper.get(i);
            parameters.put(parametreName, jasperSubReport);
        }
        parameters.put("SUBREPORT_DIR", subReportDir);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lstGenericData);

        final JasperPrint finalJasperPrint = JasperFillManager.fillReport(jasperMasterReport, parameters,
                beanColDataSource);
        return encodeReport(finalJasperPrint, reportType);
    }

    public String createReportIReport(String masterReportFileName, List lstGenericData,
                                      EnumUtil.JasperReportType reportType, String reportName) throws JRException {
        net.sf.jasperreports.engine.JasperReport jasperMasterReport = null;
        net.sf.jasperreports.engine.JasperReport jasperSubReport = null;
        try {
            jasperMasterReport = JasperCompileManager.compileReport(masterReportFileName);
        } catch (JRException e) {
            e.printStackTrace();
        }

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(lstGenericData);

        final JasperPrint finalJasperPrint = JasperFillManager.fillReport(jasperMasterReport,
                new HashMap<String, Object>(), beanColDataSource);
        return encodeReport(finalJasperPrint, reportType);
    }

    public String createReportWithDataSource(final EnumUtil.JasperReportType reportType, String reportName,
                                             JRDataSource dataSource, HashMap<String, String> mapColumnNames, HashMap<String, Class> map,
                                             PageType pageType, PageOrientation pageOrientation) {

        ColumnBuilder[] columnBuilder = createColumnBuilder(map, mapColumnNames);

        final JasperReportBuilder jasperReportBuilder = createJasperBuilder(dataSource, columnBuilder, pageType,
                pageOrientation, reportName, reportType);
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = jasperReportBuilder.toJasperPrint();
        } catch (DRException e) {
            e.printStackTrace();
        }
        final JasperPrint finalJasperPrint = jasperPrint;
        return encodeReport(finalJasperPrint, reportType);
    }

    private JasperReportBuilder createJasperBuilder(JRDataSource dataSource, ColumnBuilder[] columnBuilders,
                                                    PageType pageType, PageOrientation pageOrientation, String reportName,
                                                    EnumUtil.JasperReportType reportType) {
        StyleBuilder textStyle = stl.style(Templates.columnStyle).setBorder(stl.pen1Point());
        try {
            JasperReportBuilder jasperBuilder = null;
            if (reportType == EnumUtil.JasperReportType.EXCEL) {
                jasperBuilder = report().setTemplate(Templates.reportTemplate).setColumnStyle(textStyle)
                        .columns(columnBuilders)
                        // .title(Templates.createTitleComponent(reportName))
                        // .pageFooter(Templates.footerComponent)
                        .setDetailSplitType(SplitType.PREVENT).setColumnTitleStyle(Templates.columnTitleStyle)
                        .setPageMargin(margin(30)).setDataSource(dataSource)
                        .setColumnHeaderPrintWhenExpression(exp.printInFirstPage())
                        .setPageFormat(pageType, pageOrientation);
            } else if (reportType == EnumUtil.JasperReportType.PDF) {
                jasperBuilder = report().setTemplate(Templates.reportTemplate).setColumnStyle(textStyle)
                        .columns(columnBuilders).title(Templates.createTitleComponent(reportName))
                        // .pageFooter(Templates.footerComponent)
                        // .setColumnHeaderPrintWhenExpression(exp.printInFirstPage())
                        .setDetailSplitType(SplitType.PREVENT).setColumnTitleStyle(Templates.columnTitleStyle)
                        .setPageMargin(margin(30)).setDataSource(dataSource).setPageFormat(pageType, pageOrientation);
            }
            return jasperBuilder;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    private ColumnBuilder[] createColumnBuilder(HashMap<String, Class> map, HashMap<String, String> mapColumnNames) {
        List<ColumnBuilder> lstColumnBuilder = new LinkedList<ColumnBuilder>();
        for (Map.Entry<String, Class> entry : map.entrySet()) {
            switch (entry.getValue().getSimpleName().toLowerCase().trim()) {
                case "date":
                    lstColumnBuilder.add(col.column(mapColumnNames.get(entry.getKey()), entry.getKey(), type.dateType()));
                    break;
                case "string":
                    lstColumnBuilder.add(col.column(mapColumnNames.get(entry.getKey()), entry.getKey(), type.stringType()));
                    break;
                case "long":
                    lstColumnBuilder.add(col.column(mapColumnNames.get(entry.getKey()), entry.getKey(), type.longType()));
                    break;
                case "double":
                    lstColumnBuilder.add(col.column(mapColumnNames.get(entry.getKey()), entry.getKey(), type.doubleType()));
                    break;
            }
        }
        ColumnBuilder[] columnBuilderArray = new ColumnBuilder[lstColumnBuilder.size()];
        for (int i = 0; i < lstColumnBuilder.size(); i++) {
            columnBuilderArray[i] = lstColumnBuilder.get(i);
        }
        return columnBuilderArray;
    }
}