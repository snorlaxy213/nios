package com.springboot.commons;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.exception.GlobalException;

import java.io.*;

public class PrintUtil {

    public byte[] generatePdfByte() {
        try {
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("hello world"));
            document.close();
            byte[] bytes = baos.toByteArray().clone();

            try {
                baos.close();

                String folderStr = "C:\\temp\\report\\";
                File folder = new File(folderStr);
                if (!folder.exists() && !folder.isDirectory()) {
                    folder.mkdirs();
                }
                OutputStream out = new FileOutputStream(folderStr+"\\"+(1+Math.random()*999)+".pdf");
                InputStream is = new ByteArrayInputStream(bytes);
                byte[] buff = new byte[1024];
                int len = 0;
                while((len=is.read(buff))!=-1){
                    out.write(buff, 0, len);
                }
                is.close();
                out.close();
            } catch (IOException ex) {
                throw new GlobalException();
            }


        } catch (DocumentException e) {
            throw new GlobalException();
        }
        return null;
    }
}
