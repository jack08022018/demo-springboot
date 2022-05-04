package com.demo.service.pdf;

public class PdfCommonDto {
    private int totalPage;

    private PdfCommonDto(PdfCommonDtoBuilder builder) {
        this.totalPage = builder.totalTage;
    }

    public static PdfCommonDtoBuilder builder() {
        return new PdfCommonDtoBuilder();
    }

    public static class PdfCommonDtoBuilder {
        private int totalTage;

        public PdfCommonDtoBuilder setotalPage(int totalTage) {
            this.totalTage = totalTage;
            return this;
        }

        public PdfCommonDto build() {
            return new PdfCommonDto(this);
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
