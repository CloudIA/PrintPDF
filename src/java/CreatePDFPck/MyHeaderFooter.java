package CreatePDFPck;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyHeaderFooter extends PdfPageEventHelper {

    private ArrayList<MyItem> _MyHeaderItemList;    //Lista che contiene gli header
    private ArrayList<MyItem> _MyFooterItemList;    //Lista che contiene i footer
    private float _TotalHeaderWidth;                //Larghezza del header
    private float _TotalFooterWidth;                //Larghezza del footer
    private PdfTemplate _TotalNumberOfPageHeader;   //Variabile che memorizza il numero totale di pagine se si vuole visualizzare nell'header
    private PdfTemplate _TotalNumberOfPageFooter;   //Variabile che memorizza il numero totale di pagine se si vuole visualizzare nel footer
    private String _ChapterName;                    //Variabile che memorizza il capitolo se si vuole visualizzare nell'header o nel footer
    private float _PageNumberHeight;                //Altezza della cella che contiene il numero di pagina
    private float _PageNumberFontSize;              //Font size della cella che contiene il numero di pagina
    private String _PageNumberBaseFont;             //Font della cella che contiene il numero di pagina
    private int _PageNumberStyle;                   //Stile dlla cella che contiene il numero di pagina
    private BaseColor _PageNumberBaseColor;         //colore della cella che contiene il numero di pagina

    //Inizializzo le liste che conterrano gli header e i footer
    public MyHeaderFooter() {
        _MyHeaderItemList = new ArrayList<>();
        _MyFooterItemList = new ArrayList<>();
    }

    //Funzione che passa la lista degli header all'interno della classe caricati dal file di configurazione
    public void SetMyHeaderItemList(ArrayList<MyItem> MyHeaderItemList) {
        this._MyHeaderItemList = MyHeaderItemList;
    }

    //Funzione che passa la lista dei footer all'interno della classe caricati dal file di configurazione
    public void setMyFooterItemList(ArrayList<MyItem> MyFooterItemList) {
        this._MyFooterItemList = MyFooterItemList;
    }

    //Funzione che aggiunge un header custom
    public void AddMyHeaderItem(String ID,
            float Width,
            String Content,
            int HorizontalAlignmentint,
            int VerticalAlignment,
            int Border,
            float Height,
            float FontSize,
            String BaseFont,
            int Style,
            BaseColor BaseColor,
            boolean Enable) {

        if (_MyHeaderItemList == null) {
            _MyHeaderItemList = new ArrayList<>();
        }
        _MyHeaderItemList.add(new MyItem(ID, Width, Content, HorizontalAlignmentint, VerticalAlignment, Border, Height, FontSize, BaseFont, Style, BaseColor, Enable));

    }

    //Funzione che aggiunge un footer custom
    public void AddMyFooterItem(String ID,
            float Width,
            String Content,
            int HorizontalAlignmentint,
            int VerticalAlignment,
            int Border,
            float Height,
            float FontSize,
            String BaseFont,
            int Style,
            BaseColor BaseColor,
            boolean Enable) {

        if (_MyFooterItemList == null) {
            _MyFooterItemList = new ArrayList<>();
        }
        _MyFooterItemList.add(new MyItem(ID, Width, Content, HorizontalAlignmentint, VerticalAlignment, Border, Height, FontSize, BaseFont, Style, BaseColor, Enable));
    }

    //Funzione che restiuisce l'item i-esimo header se i> (numero massi di header) ritorna null
    public MyItem GetMyHeaderItem(int i) {
        if (i < this._MyHeaderItemList.size()) {
            return this._MyHeaderItemList.get(i);
        } else {
            return null;
        }
    }

    //Funzione che restiuisce la lista degli header
    public ArrayList GetMyHeaderItemList() {
        return this._MyHeaderItemList;
    }

    //Funzione che restiuisce l'item i-esimo footer se i> (numero massimo di footer) ritorna null
    public MyItem GetMyFooterItem(int i) {
        if (i < this._MyFooterItemList.size()) {
            return this._MyFooterItemList.get(i);
        } else {
            return null;
        }
    }

    //Funzione che restiuisce la lista dei footer
    public ArrayList GetMyFooterItemList() {
        return this._MyFooterItemList;
    }

    //Override della funzione che viene chiamata quando si apre un documento nuovo
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {

        //Calcolo la dimensione totale dell'header***********************
        float HeaderWidth = 0;
        //Scorro tutti gli elementi, se Enable dell'elemento è true allora sommo alla variabile HeaderWidht la width dell'elemento
        for (int i = 0; i < this._MyHeaderItemList.size(); i++) {
            if (this._MyHeaderItemList.get(i).GetEnable()) {
                HeaderWidth = HeaderWidth + this._MyHeaderItemList.get(i).GetWidth();
            }
        }
        this._TotalHeaderWidth = HeaderWidth;
        //**************************************************************

        //Calcolo la dimensione totale dell'header***********************
        float FooterWidth = 0;
        //Scorro tutti gli elementi, se Enable dell'elemento è true allora sommo alla variabile FooterWidth la width dell'elemento
        for (int i = 0; i < this._MyFooterItemList.size(); i++) {
            if (this._MyFooterItemList.get(i).GetEnable()) {
                FooterWidth = FooterWidth + this._MyFooterItemList.get(i).GetWidth();
            }
        }
        this._TotalFooterWidth = FooterWidth;
        //**************************************************************

        //Creo la cella che memorizzerà il numero totale di pagine se questa è nell'header
        for (int i = 0; i < _MyHeaderItemList.size(); i++) {
            if (("#Page".equals(_MyHeaderItemList.get(i).GetContent())) && (_MyHeaderItemList.get(i).GetEnable())) {
                this._TotalNumberOfPageHeader = writer.getDirectContent().createTemplate(20, this._MyHeaderItemList.get(i).GetHeight());
            }
        }
        //Creo la cella che memorizzerà il numero totale di pagine se questa è nel footer
        for (int i = 0; i < this._MyFooterItemList.size(); i++) {
            if (("#Page".equals(_MyFooterItemList.get(i).GetContent())) && (_MyFooterItemList.get(i).GetEnable())) {
                this._TotalNumberOfPageFooter = writer.getDirectContent().createTemplate(20, this._MyFooterItemList.get(i).GetHeight());
            }
        }

    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        boolean HeaderEnable = false;
        boolean FooterEnable = false;

        //Controllo se devo impostare l'header
        for (int i = 0; i < this._MyHeaderItemList.size(); i++) {
            HeaderEnable = ((HeaderEnable) || (this._MyHeaderItemList.get(i).GetEnable()));
        }
        //Controllo se devo impostare il footer
        for (int i = 0; i < this._MyFooterItemList.size(); i++) {
            FooterEnable = ((FooterEnable) || (this._MyFooterItemList.get(i).GetEnable()));
        }

        if (HeaderEnable) {
            SetHeader(writer, document);
        }
        if (FooterEnable) {
            SetFooter(writer, document);
        }

    }

    //Aggiorno la variabile che contiene il nome del capitolo
    @Override
    public void onChapter(PdfWriter writer, Document document, float paragraphPosition, Paragraph title) {

        this._ChapterName = title.getContent();

    }

    //Quando chiudo il documento controllo se devo scrivere il numero totale di pagine se si scrivo la cella
    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {

        //la cella con il numero totale di pagine avrà le stesse caratteristiche della cella originaria contentente il testo #Page
        //ma l'allineamento sarà a sinistra
        if (this._TotalNumberOfPageFooter != null) {
            ColumnText.showTextAligned(this._TotalNumberOfPageFooter, Element.ALIGN_LEFT, new Phrase(new Chunk(String.valueOf(writer.getPageNumber() - 1), FontFactory.getFont(this._PageNumberBaseFont, this._PageNumberFontSize, this._PageNumberStyle, this._PageNumberBaseColor))), 0, this._PageNumberHeight / 2 - this._PageNumberFontSize / 2, 0);
        }
        if (this._TotalNumberOfPageHeader != null) {
            ColumnText.showTextAligned(this._TotalNumberOfPageHeader, Element.ALIGN_LEFT, new Phrase(new Chunk(String.valueOf(writer.getPageNumber() - 1), FontFactory.getFont(this._PageNumberBaseFont, this._PageNumberFontSize, this._PageNumberStyle, this._PageNumberBaseColor))), 0, this._PageNumberHeight / 2 - this._PageNumberFontSize / 2, 0);
        }


    }

    //Funzione che imposta l'header
    private void SetHeader(PdfWriter writer, Document document) {

        //Controllo quante colonne deve contenere l'header***********************************
        int ColumnDimension = 1;
        for (int i = 0; i < this._MyHeaderItemList.size(); i++) {
            if (this._MyHeaderItemList.get(i).GetEnable()) {
                //Se è presente un tag in Content Che contiene la stringa #Page allora aumento il numero di colonne
                //Se #Page è all'inizio o alla fine dell'header incremento il numero di colonne di 2 per mantenere l'allineamento
                //Se #Page è al centro dell'header incremento il numero di colonne di 3 per mantenere l'allineamento
                if ("#Page".equals(this._MyHeaderItemList.get(i).GetContent())) {
                    if ((i == 0) || (i == (this._MyHeaderItemList.size() - 1))) {
                        ColumnDimension = ColumnDimension + 2;
                    } else if ((i < (this._MyHeaderItemList.size() - 1)) && (i > 0)) {
                        ColumnDimension = ColumnDimension + 3;
                    }
                } else {
                    ColumnDimension++;
                }
            }
        }
        //***********************************************************************************

        //Creo la tabella
        PdfPTable HeaderTable = new PdfPTable(ColumnDimension);


        try {
            //Imposto la dimensione massima della tabella
            HeaderTable.setTotalWidth(this._TotalHeaderWidth);

            //Ciclo per tutti gli elementi della tabella
            for (int i = 0; i < this._MyHeaderItemList.size(); i++) {
                //Se l'elemento i-esimo è abilitato
                if (this._MyHeaderItemList.get(i).GetEnable()) {

                    //imposto le dimensioni della cella che importo dal file di configurazione, i bordi, e gli allineamenti
                    HeaderTable.getDefaultCell().setFixedHeight(this._MyHeaderItemList.get(i).GetHeight()); //Altezza delle celle
                    HeaderTable.getDefaultCell().setBorder(this._MyHeaderItemList.get(i).GetBorder()); //Visualizza il bordo della tabella
                    HeaderTable.getDefaultCell().setHorizontalAlignment(this._MyHeaderItemList.get(i).GetHorizontalAlignment()); //Allineamento orizzontale
                    HeaderTable.getDefaultCell().setVerticalAlignment(this._MyHeaderItemList.get(i).GetVerticalAlignment());    //Allineamento verticale

                    //Se l'elemento contiene il testo #Page (visualizza il numero progressivo di pagina)  questa cella verrà sostiuita 
                    //con le celle che contengono il numero di pagina
                    if ("#Page".equals(this._MyHeaderItemList.get(i).GetContent())) {

                        //Imposto le variabili che contengono i valori della cella originaria 
                        this._PageNumberHeight = this._MyHeaderItemList.get(i).GetHeight();
                        this._PageNumberFontSize = this._MyHeaderItemList.get(i).GetFontSize();
                        this._PageNumberBaseFont = this._MyHeaderItemList.get(i).GetBaseFont();
                        this._PageNumberStyle = this._MyHeaderItemList.get(i).GetStyle();
                        this._PageNumberBaseColor = this._MyHeaderItemList.get(i).GetBaseColor();

                        //Reimposto le dimensioni delle celle, creo un vettore temporaneo con le nuove dimensioni
                        int Tmp[] = new int[ColumnDimension];

                        //se #Page è il primo elemento********************************************************************
                        if (i == 0) {
                            //Salvo nel vettore temporaneo le dimensioni delle celle che non sono #Page
                            for (int j = i; j < (this._MyHeaderItemList.size() - 1); j++) {
                                Tmp[j + 3] = (int) this._MyHeaderItemList.get(j + 1).GetWidth();
                            }
                            //Imposto il testo che conterrà "pagina X di Y" a destra della cella
                            HeaderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                            //Creo un chunk che contiene la stringa con i font che ha la cella #Page
                            Chunk PageNOf = new Chunk(String.format("Pagina %d di  ", writer.getPageNumber()), FontFactory.getFont(this._MyHeaderItemList.get(i).GetBaseFont(), this._MyHeaderItemList.get(i).GetFontSize(), this._MyHeaderItemList.get(i).GetStyle(), this._MyHeaderItemList.get(i).GetBaseColor()));
                            float lunghezza = PageNOf.getWidthPoint();
                            //Imposto i valori di Tmp corrispondenti alla celle che ho creato nuove
                            Tmp[i] = ((int) lunghezza) + 2;
                            Tmp[i + 1] = 20;
                            Tmp[i + 2] = ((int) this._MyHeaderItemList.get(i).GetWidth() - (int) lunghezza - 20) - 2;
                            //Setto all'interno della tabella i nuovi valori di lunghezza
                            HeaderTable.setWidths(Tmp);
                            //Inserisco nella tabella la cella che contiene "Pagina X di "
                            HeaderTable.addCell(new Phrase(PageNOf));
                            //Inserisco nella tabella la cella che contiene il numero totale di pagine
                            PdfPCell cell = new PdfPCell(Image.getInstance(this._TotalNumberOfPageHeader));
                            //Impost il bordo come nella cella originaria #Page
                            cell.setBorder(this._MyHeaderItemList.get(i).GetBorder());
                            //Aggiungo la cella che contiene il numero totale di celle
                            HeaderTable.addCell(cell);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            HeaderTable.addCell("");
                            //******************************************************************************************

                            //Se #page è all'interno********************************************************************
                        } else if ((i < (this._MyHeaderItemList.size() - 1)) && (i > 0)) {
                            //Salvo nel vettore temporaneo le dimensioni delle celle che non sono #Pages
                            for (int j = 0; j < i; j++) {
                                Tmp[j] = (int) this._MyHeaderItemList.get(j).GetWidth();
                            }
                            for (int j = i; j < (this._MyHeaderItemList.size() - 1); j++) {
                                Tmp[j + 4] = (int) this._MyHeaderItemList.get(j + 1).GetWidth();
                            }
                            //Imposto il testo che conterrà "pagina X di Y" a destra della cella
                            HeaderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                            //Creo un chunk che contiene la stringa con i font che ha la cella #Page
                            Chunk PageNOf = new Chunk(String.format("Pagina %d di  ", writer.getPageNumber()), FontFactory.getFont(this._MyHeaderItemList.get(i).GetBaseFont(), this._MyHeaderItemList.get(i).GetFontSize(), this._MyHeaderItemList.get(i).GetStyle(), this._MyHeaderItemList.get(i).GetBaseColor()));
                            float lunghezza = PageNOf.getWidthPoint();
                            //Imposto i valori di Tmp corrispondenti alla celle che ho creato nuove
                            Tmp[i] = (((int) this._MyHeaderItemList.get(i).GetWidth()) - (int) lunghezza - 20) / 2;
                            Tmp[i + 1] = ((int) lunghezza) + 2;
                            Tmp[i + 2] = 20;
                            Tmp[i + 3] = (((int) this._MyHeaderItemList.get(i).GetWidth()) - (int) lunghezza - 20) / 2;
                            //Setto all'interno della tabella i nuovi valori di lunghezza
                            HeaderTable.setWidths(Tmp);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            HeaderTable.addCell("");
                            //Inserisco nella tabella la cella che contiene "Pagina X di "
                            HeaderTable.addCell(new Phrase(PageNOf));
                            //Inserisco nella tabella la cella che contiene il numero totale di pagine
                            PdfPCell cell = new PdfPCell(Image.getInstance(this._TotalNumberOfPageHeader));
                            //Impost il bordo come nella cella originaria #Page
                            cell.setBorder(this._MyHeaderItemList.get(i).GetBorder());
                            //Aggiungo la cella che contiene il numero totale di celle
                            HeaderTable.addCell(cell);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            HeaderTable.addCell("");
                            //******************************************************************************************

                            //Se #page è alla fine dell'header**********************************************************
                        } else if (i == (this._MyHeaderItemList.size() - 1)) {
                            //Salvo nel vettore temporaneo le dimensioni delle celle che non sono #Pages
                            for (int j = 0; j < i; j++) {
                                Tmp[j] = (int) this._MyHeaderItemList.get(j).GetWidth();
                            }
                            //Imposto il testo che conterrà "pagina X di Y" a destra della cella
                            HeaderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                            //Creo un chunk che contiene la stringa con i font che ha la cella #Page
                            Chunk PageNOf = new Chunk(String.format("Pagina %d di  ", writer.getPageNumber()), FontFactory.getFont(this._MyHeaderItemList.get(i).GetBaseFont(), this._MyHeaderItemList.get(i).GetFontSize(), this._MyHeaderItemList.get(i).GetStyle(), this._MyHeaderItemList.get(i).GetBaseColor()));
                            float lunghezza = PageNOf.getWidthPoint();
                            //Imposto i valori di Tmp corrispondenti alla celle che ho creato nuove
                            Tmp[i] = ((int) this._MyHeaderItemList.get(i).GetWidth() - (int) lunghezza - 20) - 2;
                            Tmp[i + 1] = ((int) lunghezza) + 2;
                            Tmp[i + 2] = 20;
                            //Setto all'interno della tabella i nuovi valori di lunghezza
                            HeaderTable.setWidths(Tmp);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            HeaderTable.addCell("");
                            //Inserisco nella tabella la cella che contiene "Pagina X di "
                            HeaderTable.addCell(new Phrase(PageNOf));
                            //Inserisco nella tabella la cella che contiene il numero totale di pagine
                            PdfPCell cell = new PdfPCell(Image.getInstance(this._TotalNumberOfPageHeader));
                            //Impost il bordo come nella cella originaria #Page
                            cell.setBorder(this._MyHeaderItemList.get(i).GetBorder());
                            //Aggiungo la cella che contiene il numero totale di celle
                            HeaderTable.addCell(cell);

                        }
                        //******************************************************************************************

                        //Se il testo contiene #Chapter
                    } else if ("#Chapter".equals(this._MyHeaderItemList.get(i).GetContent())) {
                        //Scrivo il capitolo
                        HeaderTable.addCell(new Phrase(18, new Chunk(this._ChapterName, FontFactory.getFont(this._MyHeaderItemList.get(i).GetBaseFont(), this._MyHeaderItemList.get(i).GetFontSize(), this._MyHeaderItemList.get(i).GetStyle(), this._MyHeaderItemList.get(i).GetBaseColor()))));

                        //Se il testo contiene il campo #Image prendo
                    } else if (this._MyHeaderItemList.get(i).GetContent().toLowerCase().contains("#Image".toLowerCase())) {
                        //Splitto la stringa per prendere l'url dell'immagine
                        String TmpString[] = this._MyHeaderItemList.get(i).GetContent().split(" ");
                        try {
                            //Inserisco l'immagine
                            HeaderTable.addCell(Image.getInstance(TmpString[1])); //Inserisci il testo nella cella 3
                        } catch (BadElementException | IOException ex) {
                        }
                    } else {
                        HeaderTable.addCell(new Phrase(18, new Chunk(this._MyHeaderItemList.get(i).GetContent(), FontFactory.getFont(this._MyHeaderItemList.get(i).GetBaseFont(), this._MyHeaderItemList.get(i).GetFontSize(), this._MyHeaderItemList.get(i).GetStyle(), this._MyHeaderItemList.get(i).GetBaseColor()))));

                    }
                }


            }
            //Scrivo la tabella
            HeaderTable.writeSelectedRows(0, -1, document.leftMargin(), document.getPageSize().getHeight(), writer.getDirectContent());


        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }


    }

    private void SetFooter(PdfWriter writer, Document document) {

        //Controllo quante colonne deve contenere l'header***********************************
        int ColumnDimension = 0;
        for (int i = 0; i < this._MyFooterItemList.size(); i++) {
            if (this._MyFooterItemList.get(i).GetEnable()) {
                //Se è presente un tag in Content Che contiene la stringa #Page allora aumento il numero di colonne
                //Se #Page è all'inizio o alla fine dell'header incremento il numero di colonne di 2 per mantenere l'allineamento
                //Se #Page è al centro dell'header incremento il numero di colonne di 3 per mantenere l'allineamento
                if ("#Page".equals(this._MyFooterItemList.get(i).GetContent())) {
                    if ((i == 0) || (i == (this._MyFooterItemList.size() - 1))) {
                        ColumnDimension = ColumnDimension + 2;
                    } else if ((i < (this._MyFooterItemList.size() - 1)) && (i > 0)) {
                        ColumnDimension = ColumnDimension + 3;
                    }
                } else {
                    ColumnDimension++;
                }
            }
        }
        //***********************************************************************************

        //Creo la tabella
        PdfPTable FooterTable = new PdfPTable(ColumnDimension);


        try {
            //Imposto la dimensione massima della tabella
            FooterTable.setTotalWidth(this._TotalFooterWidth);

            //Ciclo per tutti gli elementi della tabella
            for (int i = 0; i < this._MyFooterItemList.size(); i++) {
                //Se l'elemento i-esimo è abilitato
                if (this._MyFooterItemList.get(i).GetEnable()) {
                    //imposto le dimensioni della cella che importo dal file di configurazione, i bordi, e gli allineamenti
                    FooterTable.getDefaultCell().setFixedHeight(this._MyFooterItemList.get(i).GetHeight()); //Altezza delle celle
                    FooterTable.getDefaultCell().setBorder(this._MyFooterItemList.get(i).GetBorder()); //Visualizza il bordo della tabella
                    FooterTable.getDefaultCell().setHorizontalAlignment(this._MyFooterItemList.get(i).GetHorizontalAlignment());
                    FooterTable.getDefaultCell().setVerticalAlignment(this._MyFooterItemList.get(i).GetVerticalAlignment());
                    
                    //Se l'elemento contiene il testo #Page (visualizza il numero progressivo di pagina)  questa cella verrà sostiuita 
                    //con le celle che contengono il numero di pagina
                    if ("#Page".equals(this._MyFooterItemList.get(i).GetContent())) {

                        //Imposto le variabili che contengono i valori della cella originaria 
                        this._PageNumberHeight = this._MyFooterItemList.get(i).GetHeight();
                        this._PageNumberFontSize = this._MyFooterItemList.get(i).GetFontSize();
                        this._PageNumberBaseFont = this._MyFooterItemList.get(i).GetBaseFont();
                        this._PageNumberStyle = this._MyFooterItemList.get(i).GetStyle();
                        this._PageNumberBaseColor = this._MyFooterItemList.get(i).GetBaseColor();
                        
                        //Reimposto le dimensioni delle celle, creo un vettore temporaneo con le nuove dimensioni
                        int Tmp[] = new int[ColumnDimension];

                        //se #Page è il primo elemento******************************************************************** 
                        if (i == 0) {
                            //Salvo nel vettore temporaneo le dimensioni delle celle che non sono #Page
                            for (int j = i; j < (this._MyFooterItemList.size() - 1); j++) {
                                Tmp[j + 3] = (int) this._MyFooterItemList.get(j + 1).GetWidth();
                            }
                            //Imposto il testo che conterrà "pagina X di Y" a destra della cella
                            FooterTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                            //Creo un chunk che contiene la stringa con i font che ha la cella #Page
                            Chunk PageNOf = new Chunk(String.format("Pagina %d di  ", writer.getPageNumber()), FontFactory.getFont(this._MyFooterItemList.get(i).GetBaseFont(), this._MyFooterItemList.get(i).GetFontSize(), this._MyFooterItemList.get(i).GetStyle(), this._MyFooterItemList.get(i).GetBaseColor()));
                            float lunghezza = PageNOf.getWidthPoint();
                            //Imposto i valori di Tmp corrispondenti alla celle che ho creato nuove
                            Tmp[i] = ((int) lunghezza) + 2;
                            Tmp[i + 1] = 20;
                            Tmp[i + 2] = ((int) this._MyFooterItemList.get(i).GetWidth() - (int) lunghezza - 20) - 2;
                            //Setto all'interno della tabella i nuovi valori di lunghezza
                            FooterTable.setWidths(Tmp);
                            //Inserisco nella tabella la cella che contiene "Pagina X di "
                            FooterTable.addCell(new Phrase(PageNOf));
                            //Inserisco nella tabella la cella che contiene il numero totale di pagine
                            PdfPCell cell = new PdfPCell(Image.getInstance(this._TotalNumberOfPageFooter));
                            //Impost il bordo come nella cella originaria #Page
                            cell.setBorder(this._MyFooterItemList.get(i).GetBorder());
                            //Aggiungo la cella che contiene il numero totale di celle
                            FooterTable.addCell(cell);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            FooterTable.addCell("");
                            //******************************************************************************************
                            
                            //Se #page è all'interno********************************************************************
                        } else if ((i < (this._MyFooterItemList.size() - 1)) && (i > 0)) {
                            //Salvo nel vettore temporaneo le dimensioni delle celle che non sono #Pages
                            for (int j = 0; j < i; j++) {
                                Tmp[j] = (int) this._MyFooterItemList.get(j).GetWidth();
                            }
                            for (int j = i; j < (this._MyFooterItemList.size() - 1); j++) {
                                Tmp[j + 4] = (int) this._MyFooterItemList.get(j + 1).GetWidth();
                            }
                            //Imposto il testo che conterrà "pagina X di Y" a destra della cella
                            FooterTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                            //Creo un chunk che contiene la stringa con i font che ha la cella #Page
                            Chunk PageNOf = new Chunk(String.format("Pagina %d di  ", writer.getPageNumber()), FontFactory.getFont(this._MyFooterItemList.get(i).GetBaseFont(), this._MyFooterItemList.get(i).GetFontSize(), this._MyFooterItemList.get(i).GetStyle(), this._MyFooterItemList.get(i).GetBaseColor()));
                            float lunghezza = PageNOf.getWidthPoint();
                            //Imposto i valori di Tmp corrispondenti alla celle che ho creato nuove
                            Tmp[i] = (((int) this._MyFooterItemList.get(i).GetWidth()) - (int) lunghezza - 20) / 2;
                            Tmp[i + 1] = ((int) lunghezza) + 2;
                            Tmp[i + 2] = 20;
                            Tmp[i + 3] = (((int) this._MyFooterItemList.get(i).GetWidth()) - (int) lunghezza - 20) / 2;
                            //Setto all'interno della tabella i nuovi valori di lunghezza
                            FooterTable.setWidths(Tmp);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            FooterTable.addCell("");
                            //Inserisco nella tabella la cella che contiene "Pagina X di "
                            FooterTable.addCell(new Phrase(PageNOf));
                            //Inserisco nella tabella la cella che contiene il numero totale di pagine
                            PdfPCell cell = new PdfPCell(Image.getInstance(this._TotalNumberOfPageFooter));
                            //Impost il bordo come nella cella originaria #Page
                            cell.setBorder(this._MyFooterItemList.get(i).GetBorder());
                            //Aggiungo la cella che contiene il numero totale di celle
                            FooterTable.addCell(cell);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            FooterTable.addCell("");
                            //******************************************************************************************
                            
                            //Se #page è alla fine dell'header**********************************************************
                        } else if (i == (this._MyFooterItemList.size() - 1)) {
                            //Salvo nel vettore temporaneo le dimensioni delle celle che non sono #Pages
                            for (int j = 0; j < i; j++) {
                                Tmp[j] = (int) this._MyFooterItemList.get(j).GetWidth();
                            }
                            //Imposto il testo che conterrà "pagina X di Y" a destra della cella
                            FooterTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                            //Creo un chunk che contiene la stringa con i font che ha la cella #Page
                            Chunk PageNOf = new Chunk(String.format("Pagina %d di  ", writer.getPageNumber()), FontFactory.getFont(this._MyFooterItemList.get(i).GetBaseFont(), this._MyFooterItemList.get(i).GetFontSize(), this._MyFooterItemList.get(i).GetStyle(), this._MyFooterItemList.get(i).GetBaseColor()));
                            float lunghezza = PageNOf.getWidthPoint();
                            //Imposto i valori di Tmp corrispondenti alla celle che ho creato nuove
                            Tmp[i] = ((int) this._MyHeaderItemList.get(i).GetWidth() - (int) lunghezza - 20) - 2;
                            Tmp[i + 1] = ((int) lunghezza) + 2;
                            Tmp[i + 2] = 20;
                            //Setto all'interno della tabella i nuovi valori di lunghezza
                            FooterTable.setWidths(Tmp);
                            //Inserisco la cella che serve per mantenere la formattazione impostata nel file
                            FooterTable.addCell("");
                            //Inserisco nella tabella la cella che contiene "Pagina X di "
                            FooterTable.addCell(new Phrase(PageNOf));
                            //Inserisco nella tabella la cella che contiene il numero totale di pagine
                            PdfPCell cell = new PdfPCell(Image.getInstance(this._TotalNumberOfPageFooter));
                            //Impost il bordo come nella cella originaria #Page
                            cell.setBorder(this._MyHeaderItemList.get(i).GetBorder());
                            //Aggiungo la cella che contiene il numero totale di celle
                            FooterTable.addCell(cell);
                        }
                        //******************************************************************************************

                        //Se il testo contiene #Chapter
                    } else if ("#Chapter".equals(this._MyFooterItemList.get(i).GetContent())) {
                        //Scrivo il capitolo
                        FooterTable.addCell(new Phrase(18, new Chunk(this._ChapterName, FontFactory.getFont(this._MyFooterItemList.get(i).GetBaseFont(), this._MyFooterItemList.get(i).GetFontSize(), this._MyFooterItemList.get(i).GetStyle(), this._MyFooterItemList.get(i).GetBaseColor()))));

                        //Se il testo contiene il campo #Image prendo
                    } else if (this._MyFooterItemList.get(i).GetContent().toLowerCase().contains("#Image".toLowerCase())) {
                        //Splitto la stringa per prendere l'url dell'immagine
                        String TmpString[] = this._MyFooterItemList.get(i).GetContent().split(" ");
                        try {
                            //Inserisco l'immagine
                            FooterTable.addCell(Image.getInstance(TmpString[1])); //Inserisci il testo nella cella 3
                        } catch (BadElementException | IOException ex) {
                        }

                    } else {
                        FooterTable.addCell(new Phrase(18, new Chunk(this._MyFooterItemList.get(i).GetContent(), FontFactory.getFont(this._MyFooterItemList.get(i).GetBaseFont(), this._MyFooterItemList.get(i).GetFontSize(), this._MyFooterItemList.get(i).GetStyle(), this._MyFooterItemList.get(i).GetBaseColor()))));

                    }
                }


            }

            float MaxFooterItemHeight = 0;
            for (int i = 0; i < _MyFooterItemList.size(); i++) {
                float Tmp = this._MyFooterItemList.get(i).GetHeight();
                if (Tmp > MaxFooterItemHeight) {
                    MaxFooterItemHeight = Tmp;
                }
            }
            //Scrivo la tabella
            FooterTable.writeSelectedRows(0, -1, document.leftMargin(), MaxFooterItemHeight, writer.getDirectContent());


        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }

    }
}


