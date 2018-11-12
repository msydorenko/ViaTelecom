package ua.gmail.sydorenko.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.entity.Tariff;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PdfCreator {
    private static final Logger LOG = Logger.getLogger(PdfCreator.class);

    public static void downloadTariffs(List<Tariff> tariffList) {
        LOG.debug("Start create pdf file");
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("D:\\ViaTelecom.pdf"));
            LOG.trace("Create target directory");
            document.open();
            BaseFont calibri = BaseFont.createFont("C:\\Windows\\Fonts\\calibri.ttf", "cp1251", BaseFont.EMBEDDED);

            String name = "ViaTelecom";
            Paragraph title1 = new Paragraph(name, new Font(calibri, 18));
            title1.setAlignment(Element.ALIGN_CENTER);
            document.add(title1);

            String item = "Current information about tariffs";
            Paragraph title2 = new Paragraph(item, new Font(calibri, 14));
            title2.setAlignment(Element.ALIGN_CENTER);
            document.add(title2);

            PdfPTable table = new PdfPTable(3);
            table.setSpacingBefore(20);
            table.setSpacingAfter(20);

            addTableHeader(table, calibri);
            LOG.trace("Build header");
            addRows(table, calibri, tariffList);
            LOG.trace("Build table rows");

            document.add(table);
            LOG.trace("Add table to document");

            Paragraph footer = new Paragraph("© 1991-2018 ООО «ViaTelecom»", new Font(calibri, 10));
            document.add(footer);
            LOG.trace("Add footer to document");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        document.close();
        LOG.debug("Finish create pdf file");
    }

    private static void addTableHeader(PdfPTable table, BaseFont font) {
        Stream.of("Name", "Price", "Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, new Font(font, 14)));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table, BaseFont calibri, List<Tariff> tariffList) {
        tariffList.forEach(tariff -> {
            table.addCell(new Phrase(tariff.getName(), new Font(calibri, 14)));
            table.addCell(new Phrase(String.valueOf(tariff.getPrice()), new Font(calibri, 12)));
            table.addCell(new Phrase(tariff.getDescription(), new Font(calibri, 12)));
        });
    }

}
