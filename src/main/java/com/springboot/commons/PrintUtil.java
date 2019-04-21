package com.springboot.commons;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.exception.GlobalException;

import java.io.*;

public class PrintUtil {

    public byte[] generatePdfByte() {
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
            pdfWriter.setViewerPreferences(PdfWriter.PageModeUseThumbs);
            document.setPageSize(PageSize.A4);
            document.open();

            float[] widths = {113, 113, 113};//设置三列表格的宽度
            PdfPTable table = new PdfPTable(widths);
            table.setTotalWidth(340);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            Object[][] datas = {{"区域产品销售额"},{"区域", "总销售额(万元)", "总利润(万元)简单的表格"}, {"江苏省" , 9045,  2256}, {"广东省", 3000, 690}};
            for (int i = 0; i < datas.length; i++) {
                for (int j = 0; j < datas[i].length; j++) {
                    PdfPCell pdfCell = new PdfPCell(); //表格的单元格
                    pdfCell.setMinimumHeight(30);//设置表格行高
                    pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);//设置水平居中
                    pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);//设置垂直居中
//                    pdfCell.setBackgroundColor(new BaseColor(0xdd7e6b)); //设置背景颜色

                    //设置表格边框大小及其颜色
                    pdfCell.setBorder(0);
                    pdfCell.setBorderWidthTop(0.1f);
                    pdfCell.setBorderWidthBottom(0.1f);
                    pdfCell.setBorderWidthLeft(0.1f);
                    pdfCell.setBorderWidthRight(0.1f);
//                    pdfCell.setBorderColorBottom(new BaseColor(0x674ea7));
//                    pdfCell.setBorderColorLeft(new BaseColor(0x674ea7));
//                    pdfCell.setBorderColorRight(new BaseColor(0x674ea7));
//                    pdfCell.setBorderColorTop(new BaseColor(0x674ea7));

                    //合并单元格
                    if(i == 0 && j == 0) {
                        pdfCell.setRowspan(1);
                        pdfCell.setColspan(3);
                    }

                    Paragraph paragraph = new Paragraph(datas[i][j].toString(), getPdfChineseFont());
                    pdfCell.setPhrase(paragraph);
                    table.addCell(pdfCell);
                }
            }

            document.add(new Paragraph("hello world"));
            document.add(table);
            document.close();
            byte[] bytes = baos.toByteArray().clone();

            try {
                baos.close();

                String folderStr = "C:\\temp\\report\\";
                File folder = new File(folderStr);
                if (!folder.exists() && !folder.isDirectory()) {
                    folder.mkdirs();
                }
                OutputStream out = new FileOutputStream(folderStr + "\\" + (1 + Math.random() * 999) + ".pdf");
                InputStream is = new ByteArrayInputStream(bytes);
                byte[] buff = new byte[1024];
                int len = 0;
                while ((len = is.read(buff)) != -1) {
                    out.write(buff, 0, len);
                }
                is.close();
                out.close();
            } catch (IOException ex) {
                throw new GlobalException();
            }
        } catch (DocumentException e) {
            throw new GlobalException();
        } catch (Exception e) {
            throw new GlobalException();
        }
        return null;
    }

    public static Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        return fontChinese;
    }
}
