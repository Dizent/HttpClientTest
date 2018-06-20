package Util;

import org.junit.Test;

import java.io.IOException;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/18-14:25
 * @description :
 * @Modify By :
 */
public class PdfTest {

    @Test
    public void pdfToWord(){
        PDFBox b = new PDFBox();
        String fileName = "D:\\a.pdf";
        try {
            b.pdf2doc(fileName);
        } catch (IOException e) {
            System.out.println("------");
            e.printStackTrace();
        }
    }
}
