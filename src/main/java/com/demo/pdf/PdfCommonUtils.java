package com.demo.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;

import java.util.List;

public class PdfCommonUtils {

    public static <T> T getElementFromHtml(String html, ConverterProperties converter) {
        List<IElement> list = HtmlConverter.convertToElements(html, converter);
        return (T)list.get(0);
    }

    public static ConverterProperties getConverterFont() {
        FontProvider fontProvider = new DefaultFontProvider(false, false, false);
        fontProvider.addDirectory("/System/Library/Fonts");
        ConverterProperties properties = new ConverterProperties();
        properties.setFontProvider(fontProvider);
        return properties;
    }
}
