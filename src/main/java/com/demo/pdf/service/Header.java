package com.demo.pdf.service;

import com.demo.pdf.PdfCommonDto;
import com.demo.pdf.PdfCommonUtils;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public class Header implements IEventHandler {
    private final PdfCommonDto dto;
    private final ConverterProperties converterFont;

    public Header(ConverterProperties converterFont, PdfCommonDto dto) {
        this.dto = dto;
        this.converterFont = converterFont;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdf = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), pdf);
        Canvas canvas = new Canvas(pdfCanvas, pageSize);
//        if(page.getDocument().getNumberOfPages() > 0) {
            buildSmallHeader(canvas, pageSize);
//        }
        canvas.close();
    }

    private void buildSmallHeader(Canvas canvas, Rectangle pageSize) {
        String imgHtml = "<img style='width: 50px; height: auto;' " +
                "src='src/main/resources/static/images/logo.png'/>";
        canvas.showTextAligned((Paragraph) PdfCommonUtils.getElementFromHtml(imgHtml, converterFont),
                pageSize.getLeft() + 30, pageSize.getTop() - 40, TextAlignment.LEFT);

        String imgHtml2 = "<img style='width: 50px; height: auto;' " +
                "src='src/main/resources/static/images/logo.png'/>";
        String text = "<span style='font-family: Tahoma; font-size: 14px;'>" +
                "Hồ sơ số: 390004598</span>";
//        canvas.showTextAligned((Paragraph) PdfCommonUtils.getElementFromHtml(text, converterFont),
//                pageSize.getRight() - 20, pageSize.getTop() - 40, TextAlignment.RIGHT);
    }

}
