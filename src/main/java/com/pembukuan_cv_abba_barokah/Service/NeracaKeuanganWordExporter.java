package com.pembukuan_cv_abba_barokah.Service;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class NeracaKeuanganWordExporter {

    public static void export(
            File file,
            String tahun,

            String bank,
            String piutang,
            String persediaan,
            String jumlahAsetLancar,
            String inventaris,
            String jumlahAsetTidakLancar,
            String totalAset,

            String utang,
            String totalKewajiban,
            String ekuitas,
            String modal,
            String totalEkuitas,
            String jumlahKewajibanEkuitas) throws Exception {

        XWPFDocument doc = new XWPFDocument();

        // ================= JUDUL =================
        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runTitle = title.createRun();
        runTitle.setBold(true);
        runTitle.setFontSize(14);
        runTitle.setText("NERACA KEUANGAN");

        // ================= IDENTITAS =================
        createLeftParagraph(doc, "Nama   : CV. ABBA BAROKAH");
        createLeftParagraph(doc, "NPWP   : 92.357.426.3-612.000");
        createLeftParagraph(doc, "Alamat : JL. MH. THAMRIN 3 NO.10 RT.003 RW.002 Tlogobendung, Gresik");

        doc.createParagraph().createRun().addBreak();

        // ================= TABEL 5 KOLOM =================
        XWPFTable table = doc.createTable(1, 5);
        removeBorders(table);
        table.removeRow(0);

        // HEADER
        addRow(table, "ASET", "", "", "KEWAJIBAN DAN EKUITAS", "", true);

        // Spacer
        addRow(table, "", "", "", "", "", false);

        // ASET LANCAR & KEWAJIBAN
        addRow(table, "Aset Lancar", "", "", "Kewajiban Jangka Pendek", "", true);
        addRow(table, "Bank", bank, "", "Utang Usaha", utang, false);
        addRow(table, "Piutang Usaha", piutang, "", "TOTAL KEWAJIBAN", totalKewajiban, false);
        addRow(table, "Persediaan Barang", persediaan, "", "EKUITAS", "", true);
        addRow(table, "Jumlah Aset Lancar", jumlahAsetLancar, "", "Ekuitas", ekuitas, false);

        addRow(table, "Aset Tidak Lancar", "", "", "Modal", modal, false);
        addRow(table, "Pembelian Inventaris", inventaris, "", "TOTAL EKUITAS", totalEkuitas, true);
        addRow(table, "Jumlah Aset Tidak Lancar", jumlahAsetTidakLancar, "", "JUMLAH KEWAJIBAN DAN EKUITAS",
                jumlahKewajibanEkuitas, true);

        addRow(table, "TOTAL ASET", totalAset, "", "", "", true);

        setWidth(table);

        // ================= SIGNATURE =================
        XWPFParagraph signBlock = doc.createParagraph();
        signBlock.setAlignment(ParagraphAlignment.RIGHT);
        signBlock.setKeepNext(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

        XWPFRun r1 = signBlock.createRun();
        r1.setText("Gresik, " + LocalDate.now().format(formatter));
        r1.addBreak();

        XWPFRun r2 = signBlock.createRun();
        r2.setBold(true);
        r2.setText("Direktur,");
        r2.addBreak();
        r2.addBreak();
        r2.addBreak();

        XWPFRun r3 = signBlock.createRun();
        r3.setBold(true);
        r3.setText("Bambang Sugiarto");

        // ================= SAVE =================
        try (FileOutputStream fos = new FileOutputStream(file)) {
            doc.write(fos);
        }

        doc.close();
    }

    // ================= UTIL =================

    private static void createLeftParagraph(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        p.setAlignment(ParagraphAlignment.LEFT);
        p.createRun().setText(text);
    }

    private static void addRow(
            XWPFTable table,
            String leftText,
            String leftValue,
            String spacer,
            String rightText,
            String rightValue,
            boolean bold) {

        XWPFTableRow row = table.createRow();

        ensureCells(row, 5);

        setCell(row.getCell(0), leftText, bold, ParagraphAlignment.LEFT);
        setCell(row.getCell(1), leftValue, bold, ParagraphAlignment.RIGHT);
        setCell(row.getCell(2), spacer, false, ParagraphAlignment.LEFT);
        setCell(row.getCell(3), rightText, bold, ParagraphAlignment.LEFT);
        setCell(row.getCell(4), rightValue, bold, ParagraphAlignment.RIGHT);
    }

    private static void setCell(
            XWPFTableCell cell,
            String text,
            boolean bold,
            ParagraphAlignment align) {

        cell.removeParagraph(0);
        XWPFParagraph p = cell.addParagraph();
        p.setAlignment(align);
        XWPFRun run = p.createRun();
        run.setBold(bold);
        run.setText(text == null ? "" : text);
    }

    private static void ensureCells(XWPFTableRow row, int total) {
        while (row.getTableCells().size() < total) {
            row.createCell();
        }
    }

    private static void removeBorders(XWPFTable table) {
        table.getCTTbl().getTblPr().unsetTblBorders();
    }

    private static void setWidth(XWPFTable table) {

        table.setWidth("100%");

        for (XWPFTableRow row : table.getRows()) {

            ensureCells(row, 5);

            row.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(4000));
            row.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
            row.getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
            row.getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(4000));
            row.getCell(4).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
        }
    }
}