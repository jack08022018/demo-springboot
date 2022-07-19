package com.demo.pdf;

import com.demo.pdf.service.Background;
import com.demo.pdf.service.Footer;
import com.demo.pdf.service.Header;
import com.demo.pdf.service.PdfService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.AreaBreakType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PDFGenerator {
    public static ByteArrayInputStream getPdfReport() throws Exception {
        ConverterProperties converterFont = PdfCommonUtils.getConverterFont();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfCommonDto headerDto = PdfCommonDto.builder()
                .build();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        PdfService.addMetaData(pdfDoc);
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, new Background());
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, new Header(converterFont, headerDto));
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, new Footer(converterFont, headerDto));
        Document document = new Document(pdfDoc, new PageSize(PageSize.A4.getWidth(), PageSize.A4.getHeight()), false);
        try {
            document.setMargins(40, 30, 40, 30);
            document.add(new Paragraph("\n\n\n"));
            for (int i = 0; i < 5; i++) {
                PdfService.buildPart_A(document, converterFont);
                PdfService.buildCaution(document, converterFont);
                PdfService.buildPart_B(document, converterFont);
//                PdfService.buildPart_C(document, converterFont);
//                PdfService.buildPart_D(document, converterFont);
//                PdfService.buildPart_E(document, converterFont);
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }
            Footer.addPagingToFooter(document, converterFont);
        } catch (Exception e) {
            throw e;
        } finally {
            document.close();
            outputStream.close();
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
