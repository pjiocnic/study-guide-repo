
package com.example.patientcard.service;

import com.example.patientcard.model.Patient;
import com.example.patientcard.repository.PatientRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class PatientCardService {

    private final PatientRepository repository;

    @Value("${card.output.dir}")
    private String outputDir;

    public PatientCardService(PatientRepository repository) {
        this.repository = repository;
    }

    public void generateCards() throws Exception {
        List<Patient> patients = repository.findUnprocessedPatients();
        for (Patient p : patients) {
            generateCard(p);
            repository.markAsProcessed(p.getId());
        }
    }

    private void generateCard(Patient p) throws Exception {
        Document doc = new Document(new Rectangle(300, 200));
        File targetFile = new File(outputDir + "/card_" + p.getId() + ".pdf");
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(targetFile));
        doc.open();

        // Header
        doc.add(new Paragraph("PATIENT CARD", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        // Photo
        if (p.getPhoto() != null) {
            Image photo = Image.getInstance(p.getPhoto());
            photo.scaleToFit(80, 80);
            PdfPCell photoCell = new PdfPCell(photo);
            photoCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(photoCell);
        } else {
            table.addCell("");
        }

        // Info
        PdfPCell info = new PdfPCell();
        info.addElement(new Paragraph("Name: " + p.getName()));
        info.addElement(new Paragraph("Address: " + p.getAddress()));
        info.addElement(new Paragraph("DOB: " + p.getDob()));
        info.setBorder(Rectangle.NO_BORDER);
        table.addCell(info);

        doc.add(table);

        // QR Code with JSON
        String qrData = "{ "id":" + p.getId() + ", "name":"" + p.getName() + "" }";
        BarcodeQRCode qrcode = new BarcodeQRCode(qrData, 100, 100, null);
        Image qrImage = qrcode.getImage();
        qrImage.setAbsolutePosition(200, 20);
        doc.add(qrImage);

        doc.close();
        System.out.println("Card created for patient " + p.getId());
    }
}
