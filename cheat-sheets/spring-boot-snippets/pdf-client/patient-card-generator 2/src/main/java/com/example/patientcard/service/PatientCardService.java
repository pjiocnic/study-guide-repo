
package com.example.patientcard.service;

import com.example.patientcard.model.Patient;
import com.example.patientcard.repository.PatientRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PatientCardService {

    private final PatientRepository repository;

    @Value("${card.output.dir}")
    private String outputDir;

    public PatientCardService(PatientRepository repository) {
        this.repository = repository;
    }

    public void generateCard(Long patientId) throws Exception {
        Patient p = repository.findPatientById(patientId);
        if (p == null) throw new Exception("Patient not found");

        Document doc = new Document(new Rectangle(300, 200));
        File targetFile = new File(outputDir + "/card_" + p.getId() + ".pdf");
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(targetFile));
        doc.open();

        // add logo placeholder
        Rectangle card = doc.getPageSize();
        PdfContentByte cb = writer.getDirectContent();
        cb.setColorStroke(BaseColor.BLACK);
        cb.rectangle(5, 5, card.getWidth()-10, card.getHeight()-10);
        cb.stroke();

        Paragraph header = new Paragraph("PATIENT CARD", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
        header.setAlignment(Element.ALIGN_CENTER);
        doc.add(header);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        if (p.getPhoto() != null) {
            Image photo = Image.getInstance(p.getPhoto());
            photo.scaleToFit(80, 80);
            PdfPCell photoCell = new PdfPCell(photo);
            photoCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(photoCell);
        } else {
            table.addCell("");
        }

        PdfPCell info = new PdfPCell();
        info.setBackgroundColor(new BaseColor(230, 230, 250));
        info.addElement(new Paragraph("Name: " + p.getName()));
        info.addElement(new Paragraph("Address: " + p.getAddress()));
        info.addElement(new Paragraph("DOB: " + p.getDob()));
        info.setBorder(Rectangle.NO_BORDER);
        table.addCell(info);

        doc.add(table);

        // QR
        String qrData = "{ "id":" + p.getId() + ", "name":"" + p.getName() + "" }";
        BarcodeQRCode qrcode = new BarcodeQRCode(qrData, 100, 100, null);
        Image qrImage = qrcode.getImage();
        qrImage.setAbsolutePosition(200, 20);
        doc.add(qrImage);

        doc.close();

        repository.markAsProcessed(patientId);
    }
}
