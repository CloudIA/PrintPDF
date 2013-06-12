package CreatePDFPck;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyDocument {

    private Document _MyDocument;
    private PdfWriter _PdfWriter;
    private MyHeaderFooter _MyHeaderFooter;
    private String _Author;
    private String _Subject;
    private PdfName _Version;
    private ArrayList<MyPage> _MyPageList;
    private ArrayList<MyChapter> _MyChapterList;
    private ArrayList<MyParagraph> _MyParagraphList;
    private ArrayList<MyPhrase> _MyPhraseList;
    private ArrayList<MyItem> _MyHeaderList;
    private ArrayList<MyItem> _MyFooterList;
    private ArrayList<MyImage> _MyImageList;
    private ArrayList<MyTable> _MyTableList;
    private ArrayList<MyCell> _MyCellList;
    
    private ArrayList<Chapter> _ChapterList;
    
    private PdfPTable _PdfPTable;
    
    private int _NumberOfChapter;

    public MyDocument(String Path,String FileName) {

        this._MyPageList = new ArrayList<>();
        this._MyChapterList = new ArrayList<>();
        this._MyParagraphList = new ArrayList<>();
        this._MyPhraseList = new ArrayList<>();
        this._MyHeaderList = new ArrayList<>();
        this._MyFooterList = new ArrayList<>();
        this._MyImageList = new ArrayList<>();
        this._MyTableList = new ArrayList<>();
        this._MyCellList = new ArrayList<>();
        
        this._ChapterList = new ArrayList<>();
        
        MyConfiguration _MyConfiguration = new MyConfiguration(Path,FileName);

        this._Author = _MyConfiguration.GetAuthor();
        this._Subject = _MyConfiguration.GetSubject();
        this._Version = _MyConfiguration.GetVersion();

        this._MyPageList = _MyConfiguration.GetMyPageList();
        this._MyChapterList = _MyConfiguration.GetMyChapterList();
        this._MyParagraphList = _MyConfiguration.GetMyParagraphList();
        this._MyPhraseList = _MyConfiguration.GetMyPhraseList();
        this._MyHeaderList = _MyConfiguration.GetMyHeaderList();
        this._MyFooterList = _MyConfiguration.GetMyFooterList();
        this._MyImageList = _MyConfiguration.GetMyImageList();
        this._MyTableList = _MyConfiguration.GetMyTableList();
        this._MyCellList = _MyConfiguration.GetMyCellList();

        this._NumberOfChapter = 0;
    }

    public Document ReturnMyDocument() {
        return _MyDocument;

    }
    //return 1 OK
    //return -10 se ci sono problemi con il file
    public int CreateDocument(String Path, String FileName) {
        try {
            this._MyDocument = new Document(this._MyPageList.get(0).GetSize(), this._MyPageList.get(0).GetMarginLeft(), this._MyPageList.get(0).GetMarginRight(), this._MyPageList.get(0).GetMarginTop(), this._MyPageList.get(0).GetMarginBottom());
            this._MyDocument.addAuthor(this._Author);
            this._MyDocument.addCreator(this._Author);
            this._MyDocument.addSubject(this._Subject);
            this._PdfWriter = PdfWriter.getInstance(_MyDocument, new FileOutputStream(Path + FileName));
            this._PdfWriter.setPdfVersion(this._Version);
            return 1;

        } catch (FileNotFoundException | DocumentException ex) {
            return -10;
        }
    }

    //return 1 Ok
    //return -1 Se non è stato creato il pdfWriter
    public int SetHeaderFooter() {
        if (this._PdfWriter == null) {
            return -1;
        } else {
            //******************HEADER FOOTER PARAMTER**************************//
            this._MyHeaderFooter = new MyHeaderFooter();
            //******HEADER PARAMETER******//
            this._MyHeaderFooter.SetMyHeaderItemList(this._MyHeaderList);
            //******FOOTER PARAMETER******//
            this._MyHeaderFooter.setMyFooterItemList(this._MyFooterList);
            //******************HEADER FOOTER PARAMTER**************************//
            this._PdfWriter.setPageEvent(_MyHeaderFooter);
            return 1;
        }
    }

    //return 1 OK
    //return -1 se il documento non è aperto
    //return -2 se l'oggetto documento non è stato creato
    public int OpenDocument() {
        if (this._MyDocument == null) {
            return -2;
        } else {
            if (!this._MyDocument.isOpen()) {
                this._MyDocument.open();
                return 1;
            } else {
                return -1;
            }
        }
    }   


    //return 1 OK
    //return -1 Non ho trovato l'id    
    public int AddChapter(String Text, String ID) {
        this._NumberOfChapter++;
        for (int i = 0; i < this._MyChapterList.size(); i++) {
            if (this._MyChapterList.get(i).GetID().equals(ID)) {
                Paragraph TmpParagraph = new Paragraph(new Chunk(Text, FontFactory.getFont(this._MyChapterList.get(i).GetBaseFont(), this._MyChapterList.get(i).GetFontSize(), this._MyChapterList.get(i).GetStyle(), this._MyChapterList.get(i).GetTextColor())));
                TmpParagraph.setIndentationLeft(this._MyChapterList.get(i).GetIndentationLeft());
                TmpParagraph.setIndentationRight(this._MyChapterList.get(i).GetIndentationRight());
                TmpParagraph.setSpacingBefore(this._MyChapterList.get(i).GetSpacingBefore());
                TmpParagraph.setSpacingAfter(this._MyChapterList.get(i).GetSpacingAfter());
                Chapter TmpChapter = new Chapter(TmpParagraph, this._NumberOfChapter);
                if (!this._MyChapterList.get(i).GetNumbered()) TmpChapter.setNumberDepth(0);
                if (!this._MyChapterList.get(i).GetNewPage()) TmpChapter.setTriggerNewPage(false);;
                this._ChapterList.add(TmpChapter);
                return 1;
            }
        }
        return -1;
    }

    //return 1 OK
    //return -1 Non ho trovato l'id
    //return -2 Se non ho aggiunto prima un capitolo
    public int AddParagraph(String Text, String ID) {
        for (int i = 0; i < this._MyParagraphList.size(); i++) {
            if (this._MyParagraphList.get(i).GetID().equals(ID)) {
                Paragraph TmpParagraph = new Paragraph(new Chunk(Text, FontFactory.getFont(this._MyParagraphList.get(i).GetBaseFont(), this._MyParagraphList.get(i).GetFontSize(), this._MyParagraphList.get(i).GetStyle(), this._MyParagraphList.get(i).GetTextColor())));
                TmpParagraph.setIndentationLeft(this._MyParagraphList.get(i).GetIndentationLeft());
                TmpParagraph.setIndentationRight(this._MyParagraphList.get(i).GetIndentationRight());
                TmpParagraph.setSpacingBefore(this._MyParagraphList.get(i).GetSpacingBefore());
                TmpParagraph.setSpacingAfter(this._MyParagraphList.get(i).GetSpacingAfter());
                if(this._ChapterList.isEmpty()) return -2;
                this._ChapterList.get(this._ChapterList.size() - 1).addSection(TmpParagraph);
                return 1;
            }
        }
        return -1;
    }
    
    //return 1 OK
    //return -1 Non ho trovato l'id
    //return -10 Se c'è stato un errore con il documento
    public int AddPhrase(String Text, String ID) {
        for (int i = 0; i < this._MyPhraseList.size(); i++) {
            if (this._MyPhraseList.get(i).GetID().equals(ID)) {
                Chunk TmpChunk = new Chunk(Text, FontFactory.getFont(this._MyPhraseList.get(i).GetBaseFont(), this._MyPhraseList.get(i).GetFontSize(), this._MyPhraseList.get(i).GetStyle(), this._MyPhraseList.get(i).GetTextColor()));
                Paragraph TmpParagraph = new Paragraph(TmpChunk);
                TmpParagraph.setAlignment(this._MyPhraseList.get(i).GetHorizontalAlignment());
                TmpParagraph.setSpacingBefore(this._MyPhraseList.get(i).GetSpacingBefore());
                TmpParagraph.setSpacingAfter(this._MyPhraseList.get(i).GetSpacingAfter());
                if (this._ChapterList.isEmpty()) {
                    try {
                        this._MyDocument.add(TmpChunk);
                    } catch (DocumentException ex) {
                        return -10;
                    }
                }
                else this._ChapterList.get(this._ChapterList.size() - 1).add(TmpParagraph);
                return 1;
            }
        }
        return -1;
    }

    //return 1 OK
    //return -1 Non ho trovato l'id
    //return -10 Se c'è stato un errore con il documento  
    //return -11 se c'è stato un errore con l'immagine
    //return -12 se il docuemnto oggetto non è stato creato
    public int AddImage(String URL, String ID, String Text) {

        if (this._MyDocument != null) {
            for (int i = 0; i < this._MyImageList.size(); i++) {
                if (this._MyImageList.get(i).GetID().equals(ID)) {
                    try {
                        Image TmpImage = Image.getInstance(URL);
                        Paragraph paragraph = new Paragraph();

                        if (!"".equals(Text)) {
                            paragraph.add(Text);

                        }

                        float ImageHeight = TmpImage.getHeight();
                        float ImageWidth = TmpImage.getWidth();

                        float PageHeight = this._MyDocument.getPageSize().getHeight() - this._MyDocument.topMargin() - this._MyDocument.bottomMargin();
                        float PageWidth = this._MyDocument.getPageSize().getWidth() - this._MyDocument.rightMargin() - this._MyDocument.leftMargin();

                        if ((ImageHeight > PageHeight) || (ImageWidth > PageWidth)) {

                            float PercentageHeight = (PageHeight / ImageHeight) * 100;
                            float PercentageWidth = (PageWidth / ImageWidth) * 100;

                            if (PercentageHeight > PercentageWidth) {
                                TmpImage.scalePercent(PercentageWidth);
                            } else {
                                TmpImage.scalePercent(PercentageHeight);
                            }

                        }

                        TmpImage.setAlignment(this._MyImageList.get(i).GetHorizontalAlignment());
                        TmpImage.setBorder(this._MyImageList.get(i).GetBorder());
                        TmpImage.setBorderColor(this._MyImageList.get(i).GetBorderColor());
                        TmpImage.setBorderWidth(this._MyImageList.get(i).GetBorderWidth());
                        paragraph.setFont(FontFactory.getFont(this._MyImageList.get(i).GetBaseFont(), this._MyImageList.get(i).GetFontSize(), this._MyImageList.get(i).GetStyle(), this._MyImageList.get(i).GetTextColor()));
                        paragraph.setAlignment(this._MyImageList.get(i).GetHorizontalAlignment());


                        if (this._ChapterList.isEmpty()) {
                            try {
                                this._MyDocument.add(TmpImage);
                                this._MyDocument.add(paragraph);
                            } catch (DocumentException ex) {
                                return -10;
                            }
                        } else {
                            this._ChapterList.get(this._ChapterList.size() - 1).add(TmpImage);
                            this._ChapterList.get(this._ChapterList.size() - 1).add(paragraph);
                        }

                        return 1;

                    } catch (BadElementException | IOException ex) {
                        return -11;
                    }
                }
            }

            return -1;
        } else {
            return -12;
        }
    }
    
    //return 1 OK
    //return -1 Non ho trovato l'id
    public int OpenTable(int Column, String ID){
        this._PdfPTable= new PdfPTable(Column);
        for (int i = 0; i < this._MyTableList.size(); i++) {
            if (this._MyTableList.get(i).GetID().equals(ID)) {
                this._PdfPTable.setWidthPercentage(this._MyTableList.get(i).GetWidthPercentage());
                this._PdfPTable.setSpacingBefore(this._MyTableList.get(i).GetSpacingBefore());
                this._PdfPTable.setSpacingAfter(this._MyTableList.get(i).GetSpacingAfter());
                this._PdfPTable.setHorizontalAlignment(this._MyTableList.get(i).GetHorizontalAlignment());
                return 1;
            }   
        }
        return -1;
    }
    
    //return 1 OK
    //return 2 Le size di row è minore del numero di colonne, le colonne in più vengono riempite con ""
    //return 3 Le size di row è maggiore del numero di colonne, le celle in più verrano elminate
    //return -1 Non ho trovato l'id
    //return -2 Se non ho creato la tabella
    public int AddRow(String[] Row, String ID) {
        if (this._PdfPTable != null) {
            for (int i = 0; i < this._MyCellList.size(); i++) {
                if (this._MyCellList.get(i).GetID().equals(ID)) {
                    //Controllo che il numero di String[] sia uguale al numero di colonne
                    if (Row.length == this._PdfPTable.getNumberOfColumns()) {
                        for (int j = 0; j < Row.length; j++) {
                            Phrase TmpPhrase = new Phrase(new Chunk(Row[j], FontFactory.getFont(this._MyCellList.get(i).GetBaseFont(), this._MyCellList.get(i).GetFontSize(), this._MyCellList.get(i).GetStyle(), this._MyCellList.get(i).GetTextColor())));
                            PdfPCell TmpPdfPCell = new PdfPCell(TmpPhrase);
                            TmpPdfPCell.setHorizontalAlignment(this._MyCellList.get(i).GetHorizontalAlignment());
                            TmpPdfPCell.setVerticalAlignment(this._MyCellList.get(i).GetVerticalAlignment());
                            TmpPdfPCell.setBorderWidth(this._MyCellList.get(i).GetBorderWidth());
                            TmpPdfPCell.setBorderColor(this._MyCellList.get(i).GetBorderColor());
                            TmpPdfPCell.setBackgroundColor(this._MyCellList.get(i).GetBackGroundColor());
                            this._PdfPTable.addCell(TmpPdfPCell);
                        }
                        return 1;
                    } else if (Row.length < this._PdfPTable.getNumberOfColumns()) {
                        for (int j = 0; j < Row.length; j++) {
                            Phrase TmpPhrase = new Phrase(new Chunk(Row[j], FontFactory.getFont(this._MyCellList.get(i).GetBaseFont(), this._MyCellList.get(i).GetFontSize(), this._MyCellList.get(i).GetStyle(), this._MyCellList.get(i).GetTextColor())));
                            PdfPCell TmpPdfPCell = new PdfPCell(TmpPhrase);
                            TmpPdfPCell.setHorizontalAlignment(this._MyCellList.get(i).GetHorizontalAlignment());
                            TmpPdfPCell.setVerticalAlignment(this._MyCellList.get(i).GetVerticalAlignment());
                            TmpPdfPCell.setBorderWidth(this._MyCellList.get(i).GetBorderWidth());
                            TmpPdfPCell.setBorderColor(this._MyCellList.get(i).GetBorderColor());
                            TmpPdfPCell.setBackgroundColor(this._MyCellList.get(i).GetBackGroundColor());
                            this._PdfPTable.addCell(TmpPdfPCell);
                        }
                        for (int j = 0; j < (this._PdfPTable.getNumberOfColumns() - Row.length); j++) {
                            Phrase TmpPhrase = new Phrase(new Chunk("", FontFactory.getFont(this._MyCellList.get(i).GetBaseFont(), this._MyCellList.get(i).GetFontSize(), this._MyCellList.get(i).GetStyle(), this._MyCellList.get(i).GetTextColor())));
                            PdfPCell TmpPdfPCell = new PdfPCell(TmpPhrase);
                            TmpPdfPCell.setHorizontalAlignment(this._MyCellList.get(i).GetHorizontalAlignment());
                            TmpPdfPCell.setVerticalAlignment(this._MyCellList.get(i).GetVerticalAlignment());
                            TmpPdfPCell.setBorderWidth(this._MyCellList.get(i).GetBorderWidth());
                            TmpPdfPCell.setBorderColor(this._MyCellList.get(i).GetBorderColor());
                            TmpPdfPCell.setBackgroundColor(this._MyCellList.get(i).GetBackGroundColor());
                            this._PdfPTable.addCell(TmpPdfPCell);
                        }
                        return 2;
                    } else if (Row.length > this._PdfPTable.getNumberOfColumns()) {
                        for (int j = 0; j < this._PdfPTable.getNumberOfColumns(); j++) {
                            Phrase TmpPhrase = new Phrase(new Chunk(Row[j], FontFactory.getFont(this._MyCellList.get(i).GetBaseFont(), this._MyCellList.get(i).GetFontSize(), this._MyCellList.get(i).GetStyle(), this._MyCellList.get(i).GetTextColor())));
                            PdfPCell TmpPdfPCell = new PdfPCell(TmpPhrase);
                            TmpPdfPCell.setHorizontalAlignment(this._MyCellList.get(i).GetHorizontalAlignment());
                            TmpPdfPCell.setVerticalAlignment(this._MyCellList.get(i).GetVerticalAlignment());
                            TmpPdfPCell.setBorderWidth(this._MyCellList.get(i).GetBorderWidth());
                            TmpPdfPCell.setBorderColor(this._MyCellList.get(i).GetBorderColor());
                            TmpPdfPCell.setBackgroundColor(this._MyCellList.get(i).GetBackGroundColor());
                            this._PdfPTable.addCell(TmpPdfPCell);
                        }
                        return 3;
                    }
                }
            }
            return -1;

        } else {
            return -2;
        }


    }

    //return 1 OK
    //return -1 Non ho trovato l'id
    //return -2 Se non ho creato la tabella    
    public int AddCell(String Text, String ID, int colspan) {
        if (this._PdfPTable != null) {
            for (int i = 0; i < this._MyCellList.size(); i++) {
                if (this._MyCellList.get(i).GetID().equals(ID)) {
                    Phrase TmpPhrase = new Phrase(new Chunk(Text, FontFactory.getFont(this._MyCellList.get(i).GetBaseFont(), this._MyCellList.get(i).GetFontSize(), this._MyCellList.get(i).GetStyle(), this._MyCellList.get(i).GetTextColor())));
                    PdfPCell TmpPdfPCell = new PdfPCell(TmpPhrase);
                    TmpPdfPCell.setColspan(colspan);
                    TmpPdfPCell.setHorizontalAlignment(this._MyCellList.get(i).GetHorizontalAlignment());
                    TmpPdfPCell.setVerticalAlignment(this._MyCellList.get(i).GetVerticalAlignment());
                    TmpPdfPCell.setBorderWidth(this._MyCellList.get(i).GetBorderWidth());
                    TmpPdfPCell.setBorderColor(this._MyCellList.get(i).GetBorderColor());
                    TmpPdfPCell.setBackgroundColor(this._MyCellList.get(i).GetBackGroundColor());
                    this._PdfPTable.addCell(TmpPdfPCell);
                    return 1;
                }
            }
            return -1;

        }
        else{
            return -2;
        }

    }
        
    
    //return 1 OK
    //return -2 Se non ho creato la tabella     
    //return -10 Se c'è stato un errore con il documento  
    public int CloseTable() {

        if (this._PdfPTable != null) {
            if (this._ChapterList.isEmpty()) {
                try {
                    this._MyDocument.add(_PdfPTable);
                    return 1;
                } catch (DocumentException ex) {
                    return -10;
                }
            } else {
                this._ChapterList.get(this._ChapterList.size() - 1).add(this._PdfPTable);
                return 1;
            }

        } else {
            return -2;
        }

    }

    //return 1 OK
    //return -1 Se il docuemento non è aperto
    //return -10 Se c'è stato un errore nel documento
    //return -2 Se l'oggetto documento non è creato
    public int CloseDocument() {
        if (this._MyDocument != null) {
            for (int i = 0; i < this._ChapterList.size(); i++) {
                try {
                    this._MyDocument.add(this._ChapterList.get(i));
                } catch (DocumentException ex) {
                    return -10;
                }
            }
            if (this._MyDocument.isOpen()) {
                this._MyDocument.close();
                return 1;
            } else {
                return -1;
            }
        } else {
            return -2;

        }



    }
}
