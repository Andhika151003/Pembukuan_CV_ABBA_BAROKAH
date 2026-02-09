package com.pembukuan_cv_abba_barokah.Service;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.math.BigInteger;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

public class LaporanLabaRugiWordExporter {

    private String rp(BigDecimal value) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(value);
    }

    public void export(File file, String tahun, LaporanLabaRugiService service) throws Exception {

        XWPFDocument doc = new XWPFDocument();

        // ================= JUDUL =================
        XWPFParagraph title = doc.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun rTitle = title.createRun();
        rTitle.setText("LAPORAN LABA RUGI");
        rTitle.setBold(true);
        rTitle.setFontSize(16);

        doc.createParagraph();

        // ================= IDENTITAS =================
        addParagraph(doc, "Nama   : CV. ABBA BAROKAH");
        addParagraph(doc, "NPWP   : 92.357.426.3-612.000");
        addParagraph(doc, "Alamat : JL. MH. THAMRIN 3 NO.10 RT.003 RW.002 Tlogobendung, Gresik");

        doc.createParagraph();

        // ================= AMBIL DATA =================
        BigDecimal penjualan = service.totalPenjualan(tahun);
        BigDecimal returPenjualan = service.totalReturPenjualan(tahun);
        BigDecimal totalPendapatan = service.totalPendapatan(tahun);
        BigDecimal hpp = service.totalHpp(tahun);
        BigDecimal returPembelian = service.totalReturPembelian(tahun);
        BigDecimal totalPembelian = service.totalPembelian(tahun);
        BigDecimal labaKotor = service.labaKotor(tahun);
        BigDecimal biayaAdministrasi = service.totalBiayaAdministrasi(tahun);
        BigDecimal biayaOperasional = service.totalBiayaOperasional(tahun);
        BigDecimal biayaPemasaran = service.totalBiayaPemasaran(tahun);
        BigDecimal biayaLain = service.totalBiayaPemeliharaan(tahun);
        BigDecimal totalBiaya = service.totalBiaya(tahun);
        BigDecimal labaBersih = service.labaBersih(tahun);

        // ================= TABEL TANPA GARIS =================
        XWPFTable table = doc.createTable();
        removeBorders(table);

        addSection(table, "DEBIT");
        addRow(table, "1.1 Total Penjualan", rp(penjualan), false);
        addRow(table, "1.2 Total Retur Penjualan", rp(returPenjualan), false);
        addRow(table, "TOTAL PENDAPATAN", rp(totalPendapatan), true);

        addSection(table, "KREDIT");
        addRow(table, "2.1 Total HPP", rp(hpp), false);
        addRow(table, "2.2 Total Retur Pembelian", rp(returPembelian), false);
        addRow(table, "TOTAL PEMBELIAN", rp(totalPembelian), true);
        addRow(table, "TOTAL LABA / RUGI", rp(labaKotor), true);

        addRow(table, "3.1 Total Biaya Administrasi", rp(biayaAdministrasi), false);
        addRow(table, "3.2 Total Biaya Operasional", rp(biayaOperasional), false);
        addRow(table, "3.3 Total Biaya Pemasaran", rp(biayaPemasaran), false);
        addRow(table, "3.4 Total Biaya Lain-lain", rp(biayaLain), false);
        addRow(table, "TOTAL BIAYA", rp(totalBiaya), true);
        addRow(table, "TOTAL LABA BERSIH", rp(labaBersih), true);
        setTableWidth(table);

        doc.createParagraph();

        // ================= SIGNATURE BLOCK =================
        XWPFParagraph signBlock = doc.createParagraph();
        signBlock.setAlignment(ParagraphAlignment.RIGHT);

        // gunakan CT untuk anti pecah halaman
        CTP ctp = signBlock.getCTP();
        CTPPr pPr = ctp.isSetPPr() ? ctp.getPPr() : ctp.addNewPPr();
        pPr.addNewKeepNext();
        pPr.addNewKeepLines();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));

        XWPFRun r1 = signBlock.createRun();
        r1.setText("Gresik, " + LocalDate.now().format(formatter));
        r1.addBreak();

        XWPFRun r2 = signBlock.createRun();
        r2.setText("Direktur,");
        r2.setBold(true);
        r2.addBreak();
        r2.addBreak();
        r2.addBreak();

        XWPFRun r3 = signBlock.createRun();
        r3.setText("Bambang Sugiarto");
        r3.setBold(true);

        FileOutputStream fos = new FileOutputStream(file);
        doc.write(fos);
        fos.close();
        doc.close();
    }

    private void addParagraph(XWPFDocument doc, String text) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun r = p.createRun();
        r.setText(text);
    }

    private void addSection(XWPFTable table, String text) {
        XWPFTableRow row = table.createRow();
        setCell(row, 0, text, true, ParagraphAlignment.LEFT);
        setCell(row, 1, "", false, ParagraphAlignment.RIGHT);
    }

    private void addRow(XWPFTable table, String label, String value, boolean bold) {
        XWPFTableRow row = table.createRow();
        setCell(row, 0, label, bold, ParagraphAlignment.LEFT);
        setCell(row, 1, value, bold, ParagraphAlignment.RIGHT);
    }

    private void setCell(XWPFTableRow row, int index, String text,
            boolean bold, ParagraphAlignment align) {

        XWPFTableCell cell = row.getTableCells().size() > index ? row.getCell(index) : row.createCell();

        cell.removeParagraph(0);

        XWPFParagraph p = cell.addParagraph();
        p.setAlignment(align);

        XWPFRun run = p.createRun();
        run.setText(text);
        run.setBold(bold);
    }

    private void removeBorders(XWPFTable table) {
        CTTblPr tblPr = table.getCTTbl().getTblPr();
        CTTblBorders borders = tblPr.addNewTblBorders();
        borders.addNewTop().setVal(STBorder.NONE);
        borders.addNewBottom().setVal(STBorder.NONE);
        borders.addNewLeft().setVal(STBorder.NONE);
        borders.addNewRight().setVal(STBorder.NONE);
        borders.addNewInsideH().setVal(STBorder.NONE);
        borders.addNewInsideV().setVal(STBorder.NONE);
    }

    private void setTableWidth(XWPFTable table) {

        table.setWidth("100%");

        CTTbl ctTbl = table.getCTTbl();
        CTTblPr tblPr = ctTbl.getTblPr();

        CTTblLayoutType layout = tblPr.isSetTblLayout()
                ? tblPr.getTblLayout()
                : tblPr.addNewTblLayout();

        layout.setType(STTblLayoutType.FIXED);

        for (XWPFTableRow row : table.getRows()) {

            XWPFTableCell cell0 = row.getCell(0);
            XWPFTableCell cell1 = row.getCell(1);

            if (cell0 != null) {
                cell0.getCTTc().addNewTcPr().addNewTcW()
                        .setW(BigInteger.valueOf(7000));
            }

            if (cell1 != null) {
                cell1.getCTTc().addNewTcPr().addNewTcW()
                        .setW(BigInteger.valueOf(2500));
            }
        }
    }
}