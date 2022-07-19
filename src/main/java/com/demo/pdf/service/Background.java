package com.demo.pdf.service;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class Background implements IEventHandler {
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page);
        Rectangle rect = page.getPageSize();
        canvas.saveState()
                .setFillColor(new DeviceRgb(255, 251, 199))
                .rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight())
                .fillStroke()
                .restoreState();
    }
}
