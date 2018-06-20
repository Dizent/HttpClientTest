package Util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.file.Paths;

import static java.nio.file.Files.createFile;

/**
 * @author: Dizent<zhentao.d @ winbaoxian.com>
 * @date :2018/1/18-14:21
 * @description :
 * @Modify By :
 */
public class PDFBox {

    public void pdf2doc(String name1) throws IOException {
        PDDocument doc=PDDocument.load(new File(name1));
        int pagenumber=doc.getNumberOfPages();

        name1 = name1.substring(0, name1.lastIndexOf("."));
//      String dirName = "D:\\pdf\\";// 创建目录D:\\pdf\\a.doc
        String dirName = name1;// 创建目录D:\\pdf\\a.doc
        //createDir(dirName);// 调用方法创建目录
        String fileName = dirName + ".doc";// 创建文件
        createFile(Paths.get(fileName));
        FileOutputStream fos=new FileOutputStream(fileName);
        Writer writer=new OutputStreamWriter(fos,"UTF-8");
        PDFTextStripper stripper=new PDFTextStripper();
//      doc.addSignature(arg0, arg1, arg2);
        stripper.setSortByPosition(true);//排序
        //stripper.setWordSeparator("");//pdfbox对中文默认是用空格分隔每一个字，通过这个语句消除空格（视频是这么说的）
        stripper.setStartPage(1);//设置转换的开始页
        stripper.setEndPage(pagenumber);//设置转换的结束页
        stripper.writeText(doc,writer);
        writer.close();
        doc.close();
        System.out.println("pdf转换word成功！");
    }
}
