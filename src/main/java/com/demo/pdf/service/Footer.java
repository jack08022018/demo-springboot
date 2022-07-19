package com.demo.pdf.service;

import com.demo.pdf.PdfCommonDto;
import com.demo.pdf.PdfCommonUtils;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

public class Footer implements IEventHandler {
    private final PdfCommonDto dto;
    private final ConverterProperties converterFont;

    public Footer(ConverterProperties converterFont, PdfCommonDto dto) {
        this.dto = dto;
        this.converterFont = converterFont;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        Canvas canvas = new Canvas(new PdfCanvas(page), pageSize);

        canvas.showTextAligned("390004598 - 05/11/2020 14:03:41 GMT 07:00",
                pageSize.getLeft() + 30,
                pageSize.getBottom() + 13,
                TextAlignment.LEFT);

        String number = "<span style='" +
                "border: 1px solid black;" +
                "font: bold 20px verdana;" +
                "padding: 0 3 0 3;" +
                "'>10201141</span>";
        canvas.showTextAligned((Paragraph) PdfCommonUtils.getElementFromHtml(number, converterFont),
                pageSize.getRight() - 66,
                pageSize.getBottom() + 13,
                TextAlignment.RIGHT);
        canvas.close();
    }

    public static void addPagingToFooter(Document doc, ConverterProperties converterFont) {
        Rectangle pageSize = doc.getPdfDocument().getDefaultPageSize();
        int totalPage = doc.getPdfDocument().getNumberOfPages();
        for (int i = 1; i <= totalPage; i++) {
            Paragraph number = new Paragraph(i + "/" + totalPage);
            number.setBorder(new SolidBorder(0));
            number.setBorderRadius(new BorderRadius(4));
//            number.setWidth(10);
            number.setMaxWidth(32);
            number.setPadding(2);
//            number.setPaddingLeft(5);
//            number.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            number.setKeepTogether(true);
            number.setBackgroundColor(new DeviceRgb(255, 0, 0));
            number.setFontColor(DeviceRgb.WHITE);
            doc.showTextAligned(number,
                    pageSize.getRight() - 46,
                    pageSize.getBottom() + 30,
                    i,
                    TextAlignment.CENTER,
                    VerticalAlignment.TOP,
                    0);
        }
    }
    public static void addPagingToFooter1(Document document, ConverterProperties converterFont) {
        Rectangle pageSize = document.getPdfDocument().getDefaultPageSize();
        int totalPage = document.getPdfDocument().getNumberOfPages();
        for (int i = 1; i <= totalPage; i++) {
            String html = "<div style='"
                    + "display: inline-block;"
                    + "font: 14px verdana;"
                    + "border-radius: 4px;"
                    + "padding: 2px;"
                    + "background: red; color: white;"
                    + "'>" + i + "/" + totalPage + "</div>";
            document.showTextAligned(((Paragraph) PdfCommonUtils.getElementFromHtml(html, converterFont)),
                    pageSize.getRight() - 46, pageSize.getBottom() + 25, i, TextAlignment.LEFT, VerticalAlignment.TOP, 0);
        }
    }
}
