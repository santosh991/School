/**
 *  public static final String DEST = "/home/peter/Documents/radom/ATEXTPDFs/peter/column_text_phrase.pdf";
 
 */
package com.yahoo.petermwenda83.server.servlet.result.text;

/**
 * @author peter
 *
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
//import sandbox.WrapToTest;
 
//@WrapToTest
public class FitTextInRectangle {
 
	public static final String DEST = "/home/peter/Documents/radom/ATEXTPDFs/peter/column_text_phrase.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FitTextInRectangle().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
 
        // the direct content
        PdfContentByte cb = writer.getDirectContent();
        // the rectangle and the text we want to fit in the rectangle
        Rectangle rect = new Rectangle(400, 150, 100, 200); // L H
        String text = "test";
        // try to get max font size that fit in rectangle
        BaseFont bf = BaseFont.createFont();
        int textHeightInGlyphSpace = bf.getAscent(text) - bf.getDescent(text);
        float fontSize = 500 * rect.getHeight() / textHeightInGlyphSpace;
        Phrase phrase = new Phrase("test", new Font(bf, fontSize));
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, phrase,
                // center horizontally
                (rect.getLeft() + rect.getRight()) / 2,
                // shift baseline based on descent
                rect.getBottom() - bf.getDescentPoint(text, fontSize),
                0);
 
        // draw the rect
        cb.saveState();
        cb.setColorStroke(BaseColor.BLUE);
        cb.rectangle(rect.getLeft(), rect.getBottom(), rect.getWidth(), rect.getHeight());
        cb.stroke();
        cb.restoreState();
 
        document.close();
    }
}
