package com.cenfotec.workshop.service;

import com.cenfotec.workshop.domain.Workshop;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;

public class WordDocumentService {

    public ByteArrayOutputStream handleSimpleDoc(Workshop workshop) throws Exception {
        XWPFDocument document = new XWPFDocument();

        // create header-footer
        XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) headerFooterPolicy = document.createHeaderFooterPolicy();
        // create header start
        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
        XWPFParagraph paragraph = header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText("Tiempo de duración: " + workshop.getTiempoDuracion());

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Workshop: " + workshop.getNombreWorkshop());
        titleRun.setColor("000000");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        XWPFParagraph subTitle = document.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("Autor: " + workshop.getAutor());
        subTitleRun.setColor("000000");
        subTitleRun.setFontFamily("Courier");
        subTitleRun.setFontSize(16);
        subTitleRun.setTextPosition(20);
        subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);


        XWPFParagraph sectionTitle = document.createParagraph();
        XWPFRun sectionTRun = sectionTitle.createRun();
        sectionTRun.setText("Categoria: " + workshop.getCategoria());
        sectionTRun.addBreak();
        sectionTRun.addBreak();
        sectionTRun.setText("Objetivo: " + workshop.getObjetivo());
        sectionTRun.addBreak();
        sectionTRun.addBreak();
        sectionTRun.setText("Lista de actividades: " + workshop.getListaActividades().toString());
        sectionTRun.addBreak();
        sectionTRun.addBreak();
        sectionTRun.setText("Palabras clave: " + workshop.getPalabrasClave().toString());
        sectionTRun.setColor("000000");
        sectionTRun.setBold(true);
        sectionTRun.setFontFamily("Courier");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        document.close();
        return out;
    }
}