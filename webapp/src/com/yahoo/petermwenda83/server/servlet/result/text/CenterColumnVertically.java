package com.yahoo.petermwenda83.server.servlet.result.text;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
//import sandbox.WrapToTest;
 
//@WrapToTest
public class CenterColumnVertically {
 
	public static final String DEST = "/home/peter/Documents/radom/ATEXTPDFs/peter/column_text_phrase.pdf";
 
    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CenterColumnVertically().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        // step 3
        document.open();
        // step 4
        // show the area for the column as a rectangle with red borders
        float llx = 40;  float lly = 80; //determine height
        float urx = 300;/*length*/ float ury = 100;
        Rectangle rect = new Rectangle(llx, lly, urx, ury);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(0.2f);
        rect.setBorderColor(BaseColor.BLACK);
        PdfContentByte cb = writer.getDirectContent();
        cb.rectangle(rect);
        // this is the paragraph we want to center vertically:
        Paragraph p = new Paragraph("This text is centered vertically.");
        p.setLeading(12f);
        
        Paragraph p2 = new Paragraph("This text is centered vertically.");
        p2.setLeading(12f);
        // We add the column in simulation mode:
        float y = drawColumnText(cb, rect, p, true);
        float y2 = drawColumnText(cb, rect, p2, true);
        // We calculate a new rectangle and add the column for real
        rect = new Rectangle(llx, lly, urx, ury - ((y - lly) / 2));
        drawColumnText(cb, rect, p, false);
        // step 5
        document.close();
    }
 
    /**
     * Draws a Paragraph inside a given column and returns the Y value at the end of the text.
     * @param  canvas    the canvas to which we'll add the Paragraph
     * @param  rect      the dimensions of the column
     * @param  p         the Paragraph we want to add
     * @param  simulate  do we add the paragraph for real?
     * @return the Y coordinate of the end of the text
     * @throws com.itextpdf.text.DocumentException
     */
    public float drawColumnText(PdfContentByte canvas, Rectangle rect, Paragraph p, boolean simulate) throws DocumentException {
        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(rect);
        ct.addElement(p);
        ct.go(simulate);
        return ct.getYLine();
    }
}