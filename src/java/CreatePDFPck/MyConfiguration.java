package CreatePDFPck;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MyConfiguration {

    //DOCUMENT VARIABLE ***************
    private String _Author;
    private String _Subject;
    private PdfName _Version;
    //DOCUMENT VARIABLE ***************
    
    //PAGE VARIABLE *******************
    private ArrayList<MyPage> _MyPageList;
    //PAGE VARIABLE *******************
    
    //HEADER VARIABLE *****************
        //ITEM VARIABLE ***************
    private ArrayList<MyItem> _MyHeaderList;
        //ITEM VARIABLE ***************
    //HEADER VARIABLE *****************
    
    //FOOTER VARIABLE *****************
        //ITEM VARIABLE ***************
    private ArrayList<MyItem> _MyFooterList;
        //ITEM VARIABLE ***************
    //FOOTER VARIABLE *****************    
    
    //CHAPTER VARIABLE **************
    private ArrayList<MyChapter> _MyChapterList;
    //CHAPTER VARIABLE **************
    
    //PARAGRAPH VARIABLE ************
    private ArrayList<MyParagraph> _MyParagraphList;
    //PARAGRAPH VARIABLE ************
    
    //PHRASE VARIABLE ************
    private ArrayList<MyPhrase> _MyPhraseList;
    //PHRASE VARIABLE ************    
    
    //IMAGE VARIABLE ************
    private ArrayList<MyImage> _MyImageList;
    //IMAGE VARIABLE ************

    //TABLE VARIABLE ************
    private ArrayList<MyTable> _MyTableList;
    //TABLE VARIABLE ************    
    
    //CELL VARIABLE ************
    private ArrayList<MyCell> _MyCellList;
    //CELL VARIABLE ************     
    
    private File _ConfigFile;
    private String _Path;
    private String _FileName;
   
    private boolean _Configured;
    
    public MyConfiguration(String Path, String FileName) {

        this._FileName = FileName;
        this._Path = Path;

        this._Configured=false;
        
        this._MyPageList = new ArrayList<>();
        this._MyHeaderList = new ArrayList<>();
        this._MyFooterList = new ArrayList<>();
        this._MyChapterList = new ArrayList<>();
        this._MyParagraphList = new ArrayList<>();
        this._MyPhraseList = new ArrayList<>();
        this._MyImageList = new ArrayList<>();
        this._MyTableList = new ArrayList<>();
        this._MyCellList = new ArrayList<>();
        
        
        this._ConfigFile = new File(this._Path, this._FileName);

        if (this._ConfigFile.canRead()) {
            // Costruiamo una factory per processare il nostro flusso di dati
            
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document document = (Document) builder.parse(this._ConfigFile);
            

            //DOCUMENT ****************************************************
            NodeList NodeListDocument = document.getElementsByTagName("Document");
            for (int i = 0; i < NodeListDocument.getLength(); i++) {
                Node DocumentAttributes = NodeListDocument.item(i);
                if (DocumentAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    this._Author = ((Element) DocumentAttributes).getElementsByTagName("Author").item(0).getFirstChild().getNodeValue();
                    this._Subject = ((Element) DocumentAttributes).getElementsByTagName("Subject").item(0).getFirstChild().getNodeValue();

                    String Version = ((Element) DocumentAttributes).getElementsByTagName("Version").item(0).getFirstChild().getNodeValue();
                    switch (Version) {
                        case "PDF_VERSION_1_2":
                            this._Version = PdfWriter.PDF_VERSION_1_2;
                            break;
                        case "PDF_VERSION_1_3":
                            this._Version = PdfWriter.PDF_VERSION_1_3;
                            break;
                        case "PDF_VERSION_1_4":
                            this._Version = PdfWriter.PDF_VERSION_1_4;
                            break;
                        case "PDF_VERSION_1_5":
                            this._Version = PdfWriter.PDF_VERSION_1_5;
                            break;
                        case "PDF_VERSION_1_6":
                            this._Version = PdfWriter.PDF_VERSION_1_6;
                            break;
                        case "PDF_VERSION_1_7":
                            this._Version = PdfWriter.PDF_VERSION_1_7;
                            break;
                        default:
                            this._Version = PdfWriter.PDF_VERSION_1_7;
                    }
                }
            }
            //DOCUMENT ****************************************************

            //HEADER ****************************************************
            NodeList NodeListHeader = document.getElementsByTagName("Header");
            for (int i = 0; i < NodeListHeader.getLength(); i++) {
                Node HeaderAttributes = NodeListHeader.item(i);              
                if (HeaderAttributes.getNodeType() == Node.ELEMENT_NODE) {  
                    MyItem Tmp = new MyItem();
                    Tmp.SetID(((Element) HeaderAttributes).getAttribute("id"));
                    Tmp.SetWidth(Float.parseFloat(((Element) HeaderAttributes).getElementsByTagName("Width").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetContent(((Element) HeaderAttributes).getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                                        
                    String HorizontalAlignment = ((Element) HeaderAttributes).getElementsByTagName("HorizontalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(HorizontalAlignment){
                        case "ALIGN_LEFT":
                            Tmp.SetHorizontalAlignment(0);
                            break;
                        case "ALIGN_CENTER":
                            Tmp.SetHorizontalAlignment(1);
                            break;
                        case "ALIGN_RIGHT":
                            Tmp.SetHorizontalAlignment(2);
                            break; 
                        case "ALIGN_JUSTIFIED":
                            Tmp.SetHorizontalAlignment(3);
                            break;                      
                        default:
                            Tmp.SetHorizontalAlignment(0);
                            break; 
                    }
                    
                    String VerticalAlignment = ((Element) HeaderAttributes).getElementsByTagName("VerticalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(VerticalAlignment){
                        case "ALIGN_TOP":
                            Tmp.SetVerticalAlignment(4);
                            break;
                        case "ALIGN_MIDDLE":
                            Tmp.SetVerticalAlignment(5);
                            break;
                        case "ALIGN_BOTTOM":
                            Tmp.SetVerticalAlignment(6);
                            break; 
                        case "ALIGN_BASELINE":
                            Tmp.SetVerticalAlignment(7);
                            break;                      
                        default:
                            Tmp.SetVerticalAlignment(5);
                            break; 
                    }                    
                    
                    String Border = ((Element) HeaderAttributes).getElementsByTagName("Border").item(0).getFirstChild().getNodeValue();
                    switch(Border){
                        case "TOP":
                            Tmp.SetBorder(1);
                            break;
                        case "BOTTOM":
                            Tmp.SetBorder(2);
                            break;
                        case "LEFT":
                            Tmp.SetBorder(4);
                            break; 
                        case "RIGHT":
                            Tmp.SetBorder(8);
                            break;
                        case "NO_BORDER":
                            Tmp.SetBorder(0);
                            break;
                        case "BOX":
                            Tmp.SetBorder(15);
                            break;                            
                        default:
                            Tmp.SetBorder(0);
                            break; 
                    }                     
                    
                    Tmp.SetHeight(Float.parseFloat(((Element) HeaderAttributes).getElementsByTagName("Height").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetFontSize(Float.parseFloat(((Element) HeaderAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetBaseFont(((Element) HeaderAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());
                    String Style = ((Element) HeaderAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch(Style){
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break; 
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;                            
                        default:
                            Tmp.SetStyle(0);
                            break; 
                    }                      
                    
                    
                    String TextColor = ((Element) HeaderAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TxtColorSplit[] = TextColor.split(",");
                    Tmp.SetBaseColor(new BaseColor(Integer.parseInt(TxtColorSplit[0], 16),Integer.parseInt(TxtColorSplit[1], 16),Integer.parseInt(TxtColorSplit[2], 16)));
                    
                    String Enable = ((Element) HeaderAttributes).getElementsByTagName("Enable").item(0).getFirstChild().getNodeValue(); 
                    if("true".equals(Enable)) Tmp.SetEnable(true);
                    else Tmp.SetEnable(false);
                    
                    if(this._MyHeaderList==null) this._MyHeaderList = new ArrayList<>();
                    this._MyHeaderList.add(Tmp);
                    
                }
            }
            //HEADER ****************************************************
            
            
           //FOOTER ****************************************************
            NodeList NodeListFooter = document.getElementsByTagName("Footer");
            for (int i = 0; i < NodeListFooter.getLength(); i++) {
                Node FooterAttributes = NodeListFooter.item(i);              
                if (FooterAttributes.getNodeType() == Node.ELEMENT_NODE) {  
                    MyItem Tmp = new MyItem();
                    Tmp.SetID(((Element) FooterAttributes).getAttribute("id"));
                    Tmp.SetWidth(Float.parseFloat(((Element) FooterAttributes).getElementsByTagName("Width").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetContent(((Element) FooterAttributes).getElementsByTagName("Content").item(0).getFirstChild().getNodeValue());
                                        
                    String HorizontalAlignment = ((Element) FooterAttributes).getElementsByTagName("HorizontalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(HorizontalAlignment){
                        case "ALIGN_LEFT":
                            Tmp.SetHorizontalAlignment(0);
                            break;
                        case "ALIGN_CENTER":
                            Tmp.SetHorizontalAlignment(1);
                            break;
                        case "ALIGN_RIGHT":
                            Tmp.SetHorizontalAlignment(2);
                            break; 
                        case "ALIGN_JUSTIFIED":
                            Tmp.SetHorizontalAlignment(3);
                            break;                      
                        default:
                            Tmp.SetHorizontalAlignment(0);
                            break; 
                    }
                    
                    String VerticalAlignment = ((Element) FooterAttributes).getElementsByTagName("VerticalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(VerticalAlignment){
                        case "ALIGN_TOP":
                            Tmp.SetVerticalAlignment(4);
                            break;
                        case "ALIGN_MIDDLE":
                            Tmp.SetVerticalAlignment(5);
                            break;
                        case "ALIGN_BOTTOM":
                            Tmp.SetVerticalAlignment(6);
                            break; 
                        case "ALIGN_BASELINE":
                            Tmp.SetVerticalAlignment(7);
                            break;                      
                        default:
                            Tmp.SetVerticalAlignment(5);
                            break; 
                    }                    
                    
                    String Border = ((Element) FooterAttributes).getElementsByTagName("Border").item(0).getFirstChild().getNodeValue();
                    switch(Border){
                        case "TOP":
                            Tmp.SetBorder(1);
                            break;
                        case "BOTTOM":
                            Tmp.SetBorder(2);
                            break;
                        case "LEFT":
                            Tmp.SetBorder(4);
                            break; 
                        case "RIGHT":
                            Tmp.SetBorder(8);
                            break;
                        case "NO_BORDER":
                            Tmp.SetBorder(0);
                            break;
                        case "BOX":
                            Tmp.SetBorder(15);
                            break;                            
                        default:
                            Tmp.SetBorder(0);
                            break; 
                    }                     
                    
                    Tmp.SetHeight(Float.parseFloat(((Element) FooterAttributes).getElementsByTagName("Height").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetFontSize(Float.parseFloat(((Element) FooterAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetBaseFont(((Element) FooterAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());
                    String Style = ((Element) FooterAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch(Style){
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break; 
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;                            
                        default:
                            Tmp.SetStyle(0);
                            break; 
                    }                      
                    
                    
                    String TextColor = ((Element) FooterAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TxtColorSplit[] = TextColor.split(",");
                    Tmp.SetBaseColor(new BaseColor(Integer.parseInt(TxtColorSplit[0], 16),Integer.parseInt(TxtColorSplit[1], 16),Integer.parseInt(TxtColorSplit[2], 16)));

                    String Enable = ((Element) FooterAttributes).getElementsByTagName("Enable").item(0).getFirstChild().getNodeValue(); 
                    if("true".equals(Enable)) Tmp.SetEnable(true);
                    else Tmp.SetEnable(false);
                    
                    if(this._MyFooterList==null) this._MyFooterList = new ArrayList<>();
                    this._MyFooterList.add(Tmp);
                    
                }
            }
            //FOOTER ****************************************************            
            
            //PAGE ****************************************************
            NodeList NodeListPage = document.getElementsByTagName("Page");
            for (int i = 0; i < NodeListPage.getLength(); i++) {
                Node PageAttributes = NodeListPage.item(i);              
                if (PageAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    MyPage Tmp= new MyPage();
                    Tmp.SetID(((Element) PageAttributes).getAttribute("id"));
                    
                    String Size = ((Element) PageAttributes).getElementsByTagName("Size").item(0).getFirstChild().getNodeValue();
                    switch(Size){
                        case "A0":  Tmp.SetSize(PageSize.A0);   break;
                        case "A1":  Tmp.SetSize(PageSize.A1);   break;
                        case "A2":  Tmp.SetSize(PageSize.A2);   break;
                        case "A3":  Tmp.SetSize(PageSize.A3);   break;                                
                        case "A4":  Tmp.SetSize(PageSize.A4);   break;  
                        case "A5":  Tmp.SetSize(PageSize.A5);   break;   
                        case "A6":  Tmp.SetSize(PageSize.A6);   break;
                        case "A7":  Tmp.SetSize(PageSize.A7);   break;
                        case "A8":  Tmp.SetSize(PageSize.A8);   break;
                        case "A9":  Tmp.SetSize(PageSize.A9);   break;
                        case "A10":  Tmp.SetSize(PageSize.A10);   break;    
                        case "A4_LANDSCAPE":  Tmp.SetSize(PageSize.A4_LANDSCAPE);   break; 
                    }
                    
                    
                    String BackColorSplit[] = ((Element) PageAttributes).getElementsByTagName("BackColor").item(0).getFirstChild().getNodeValue().split(",");
                    Tmp.SetBackColor(new BaseColor(Integer.parseInt(BackColorSplit[0], 16),Integer.parseInt(BackColorSplit[1], 16),Integer.parseInt(BackColorSplit[2], 16)));
                    Tmp.SetMarginLeft(Float.parseFloat(((Element) PageAttributes).getElementsByTagName("Left").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetMarginRight(Float.parseFloat(((Element) PageAttributes).getElementsByTagName("Right").item(0).getFirstChild().getNodeValue()));            
                    Tmp.SetMarginTop(Float.parseFloat(((Element) PageAttributes).getElementsByTagName("Top").item(0).getFirstChild().getNodeValue()));
                    Tmp.SetMarginBottom(Float.parseFloat(((Element) PageAttributes).getElementsByTagName("Bottom").item(0).getFirstChild().getNodeValue()));
                    
                    if(this._MyPageList== null) this._MyPageList = new ArrayList<>();
                    this._MyPageList.add(Tmp);
                                               
                }
            }
            //PAGE ****************************************************
            

            //CHAPTER ****************************************************
            NodeList NodeListChapter = document.getElementsByTagName("Chapter");
            for (int i = 0; i < NodeListChapter.getLength(); i++) {
                Node ChapterAttributes = NodeListChapter.item(i);
                if (ChapterAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    MyChapter Tmp = new MyChapter();
                    
                    Tmp.SetID(((Element) ChapterAttributes).getAttribute("id"));
                    
                    Tmp.SetBaseFont(((Element) ChapterAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());

                    String Style = ((Element) ChapterAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch (Style) {
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break;
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;
                        default:
                            Tmp.SetStyle(0);
                            break;
                    }

                    Tmp.SetFontSize(Float.parseFloat(((Element) ChapterAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    
                    String TextColor = ((Element) ChapterAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TxtColorSplit[] = TextColor.split(",");
                    Tmp.SetTextColor(new BaseColor(Integer.parseInt(TxtColorSplit[0], 16),Integer.parseInt(TxtColorSplit[1], 16),Integer.parseInt(TxtColorSplit[2], 16)));

                    Tmp.SetIndentationLeft(Float.parseFloat(((Element) ChapterAttributes).getElementsByTagName("IndentationLeft").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetIndentationRight(Float.parseFloat(((Element) ChapterAttributes).getElementsByTagName("IndentationRight").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetSpacingBefore(Float.parseFloat(((Element) ChapterAttributes).getElementsByTagName("SpacingBefore").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetSpacingAfter(Float.parseFloat(((Element) ChapterAttributes).getElementsByTagName("SpacingAfter").item(0).getFirstChild().getNodeValue()));

                    String AutoNumber = ((Element) ChapterAttributes).getElementsByTagName("AutoNumber").item(0).getFirstChild().getNodeValue();
                    if("true".equals(AutoNumber)) Tmp.SetNumbered(true);
                    else Tmp.SetNumbered(false);
                    
                    String AutoNewPage = ((Element) ChapterAttributes).getElementsByTagName("AutoNewPage").item(0).getFirstChild().getNodeValue();
                    if("true".equals(AutoNewPage)) Tmp.SetNewPage(true);
                    else Tmp.SetNewPage(false);
                    
                    if(this._MyChapterList==null) this._MyChapterList=new ArrayList<>();
                    this._MyChapterList.add(Tmp);
                    
                }
            }
            //CHAPTER ****************************************************             
            
            //PARAGRAPH ****************************************************
            NodeList NodeListParagraph = document.getElementsByTagName("Paragraph");
            for (int i = 0; i < NodeListParagraph.getLength(); i++) {
                Node ParagraphAttributes = NodeListParagraph.item(i);
                if (ParagraphAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    MyParagraph Tmp = new MyParagraph();
                    
                    Tmp.SetID(((Element) ParagraphAttributes).getAttribute("id"));
                    
                    Tmp.SetBaseFont(((Element) ParagraphAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());

                    String Style = ((Element) ParagraphAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch (Style) {
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break;
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;
                        default:
                            Tmp.SetStyle(0);
                            break;
                    }

                    Tmp.SetFontSize(Float.parseFloat(((Element) ParagraphAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    
                    String TextColor = ((Element) ParagraphAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TxtColorSplit[] = TextColor.split(",");
                    Tmp.SetTextColor(new BaseColor(Integer.parseInt(TxtColorSplit[0], 16),Integer.parseInt(TxtColorSplit[1], 16),Integer.parseInt(TxtColorSplit[2], 16)));

                    Tmp.SetIndentationLeft(Float.parseFloat(((Element) ParagraphAttributes).getElementsByTagName("IndentationLeft").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetIndentationRight(Float.parseFloat(((Element) ParagraphAttributes).getElementsByTagName("IndentationRight").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetSpacingBefore(Float.parseFloat(((Element) ParagraphAttributes).getElementsByTagName("SpacingBefore").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetSpacingAfter(Float.parseFloat(((Element) ParagraphAttributes).getElementsByTagName("SpacingAfter").item(0).getFirstChild().getNodeValue()));

                    String AutoNumber = ((Element) ParagraphAttributes).getElementsByTagName("AutoNumber").item(0).getFirstChild().getNodeValue();
                    if("true".equals(AutoNumber)) Tmp.SetNumbered(true);
                    else Tmp.SetNumbered(false);
                    
                    String AutoNewPage = ((Element) ParagraphAttributes).getElementsByTagName("AutoNewPage").item(0).getFirstChild().getNodeValue();
                    if("true".equals(AutoNewPage)) Tmp.SetNewPage(true);
                    else Tmp.SetNewPage(false);
                    
                    if(this._MyParagraphList==null) this._MyParagraphList=new ArrayList<>();
                    this._MyParagraphList.add(Tmp);
                    
                }
            }
            //PARAGRAPH ****************************************************
            
            
            //PHRASE ****************************************************
            NodeList NodeListPhrase = document.getElementsByTagName("Phrase");
            for (int i = 0; i < NodeListPhrase.getLength(); i++) {
                Node PhraseAttributes = NodeListPhrase.item(i);
                if (PhraseAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    MyPhrase Tmp = new MyPhrase();
                    
                    Tmp.SetID(((Element) PhraseAttributes).getAttribute("id"));
                    
                    Tmp.SetBaseFont(((Element) PhraseAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());

                    String Style = ((Element) PhraseAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch (Style) {
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break;
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;
                        default:
                            Tmp.SetStyle(0);
                            break;
                    }

                    Tmp.SetFontSize(Float.parseFloat(((Element) PhraseAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    
                    String TextColor = ((Element) PhraseAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TxtColorSplit[] = TextColor.split(",");
                    Tmp.SetTextColor(new BaseColor(Integer.parseInt(TxtColorSplit[0], 16),Integer.parseInt(TxtColorSplit[1], 16),Integer.parseInt(TxtColorSplit[2], 16)));
                    
                    Tmp.SetSpacingBefore(Float.parseFloat(((Element) PhraseAttributes).getElementsByTagName("SpacingBefore").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetSpacingAfter(Float.parseFloat(((Element) PhraseAttributes).getElementsByTagName("SpacingAfter").item(0).getFirstChild().getNodeValue()));                   
                    
                    String HorizontalAlignment = ((Element) PhraseAttributes).getElementsByTagName("HorizontalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(HorizontalAlignment){
                        case "ALIGN_LEFT":
                            Tmp.SetHorizontalAlignment(0);
                            break;
                        case "ALIGN_CENTER":
                            Tmp.SetHorizontalAlignment(1);
                            break;
                        case "ALIGN_RIGHT":
                            Tmp.SetHorizontalAlignment(2);
                            break; 
                        case "ALIGN_JUSTIFIED":
                            Tmp.SetHorizontalAlignment(3);
                            break;                      
                        default:
                            Tmp.SetHorizontalAlignment(0);
                            break; 
                    }                    
                    
                    
                    Tmp.SetIndentationLeft(Float.parseFloat(((Element) PhraseAttributes).getElementsByTagName("IndentationLeft").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetIndentationRight(Float.parseFloat(((Element) PhraseAttributes).getElementsByTagName("IndentationRight").item(0).getFirstChild().getNodeValue()));
                    


                    
                    if(this._MyPhraseList==null) this._MyPhraseList=new ArrayList<>();
                    this._MyPhraseList.add(Tmp);
                    
                }
            }
            //PHRASE ****************************************************             
            
            //IMAGE ****************************************************
            NodeList NodeListImage = document.getElementsByTagName("Image");
            for (int i = 0; i < NodeListImage.getLength(); i++) {
                Node ImageAttributes = NodeListImage.item(i);
                if (ImageAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    MyImage Tmp = new MyImage();
                    
                    Tmp.SetID(((Element) ImageAttributes).getAttribute("id"));
                    
                    String HorizontalAlignment = ((Element) ImageAttributes).getElementsByTagName("HorizontalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(HorizontalAlignment){
                        case "ALIGN_LEFT":
                            Tmp.SetHorizontalAlignment(0);
                            break;
                        case "ALIGN_CENTER":
                            Tmp.SetHorizontalAlignment(1);
                            break;
                        case "ALIGN_RIGHT":
                            Tmp.SetHorizontalAlignment(2);
                            break; 
                        case "ALIGN_JUSTIFIED":
                            Tmp.SetHorizontalAlignment(3);
                            break;                      
                        default:
                            Tmp.SetHorizontalAlignment(0);
                            break; 
                    }

                    String Border = ((Element) ImageAttributes).getElementsByTagName("Border").item(0).getFirstChild().getNodeValue();
                    switch(Border){
                        case "TOP":
                            Tmp.SetBorder(1);
                            break;
                        case "BOTTOM":
                            Tmp.SetBorder(2);
                            break;
                        case "LEFT":
                            Tmp.SetBorder(4);
                            break; 
                        case "RIGHT":
                            Tmp.SetBorder(8);
                            break;
                        case "NO_BORDER":
                            Tmp.SetBorder(0);
                            break;
                        case "BOX":
                            Tmp.SetBorder(15);
                            break;                            
                        default:
                            Tmp.SetBorder(0);
                            break; 
                    }                     

                    Tmp.SetBorderWidth(Float.parseFloat(((Element) ImageAttributes).getElementsByTagName("BorderWidth").item(0).getFirstChild().getNodeValue()));
                    
                    String BorderColor = ((Element) ImageAttributes).getElementsByTagName("BorderColor").item(0).getFirstChild().getNodeValue();                    
                    String BorderColorSplit[] = BorderColor.split(",");
                    Tmp.SetBorderColor(new BaseColor(Integer.parseInt(BorderColorSplit[0], 16),Integer.parseInt(BorderColorSplit[1], 16),Integer.parseInt(BorderColorSplit[2], 16))); 
                    
                    Tmp.SetBaseFont(((Element) ImageAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());                   
                    
                    String Style = ((Element) ImageAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch(Style){
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break; 
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;                            
                        default:
                            Tmp.SetStyle(0);
                            break; 
                    } 
                    
                    Tmp.SetFontSize(Float.parseFloat(((Element) ImageAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    
                    String TextColor = ((Element) ImageAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TextColorSplit[] = TextColor.split(",");
                    Tmp.SetBorderColor(new BaseColor(Integer.parseInt(TextColorSplit[0], 16),Integer.parseInt(TextColorSplit[1], 16),Integer.parseInt(TextColorSplit[2], 16)));                     
                    
                    String Enable = ((Element) ImageAttributes).getElementsByTagName("Enable").item(0).getFirstChild().getNodeValue(); 
                    if("true".equals(Enable)) Tmp.SetEnable(true);
                    else Tmp.SetEnable(false);                    
                    
                    if(this._MyImageList==null) this._MyImageList=new ArrayList<>();
                    this._MyImageList.add(Tmp);
                    
                }
            }
            //IMAGE ****************************************************
            
            //TABLE ****************************************************
            NodeList NodeListTable = document.getElementsByTagName("Table");
            for (int i = 0; i < NodeListTable.getLength(); i++) {
                Node TableAttributes = NodeListTable.item(i);
                if (TableAttributes.getNodeType() == Node.ELEMENT_NODE) {
                    MyTable Tmp = new MyTable();
                    
                    Tmp.SetID(((Element) TableAttributes).getAttribute("id"));
                    
                    Tmp.SetWidthPercentage(Float.parseFloat(((Element) TableAttributes).getElementsByTagName("WidthPercentage").item(0).getFirstChild().getNodeValue()));                    
                    
                    Tmp.SetSpacingBefore(Float.parseFloat(((Element) TableAttributes).getElementsByTagName("SpacingBefore").item(0).getFirstChild().getNodeValue()));
                    
                    Tmp.SetSpacingAfter(Float.parseFloat(((Element) TableAttributes).getElementsByTagName("SpacingAfter").item(0).getFirstChild().getNodeValue()));
                    
                    String HorizontalAlignment = ((Element) TableAttributes).getElementsByTagName("HorizontalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(HorizontalAlignment){
                        case "ALIGN_LEFT":
                            Tmp.SetHorizontalAlignment(0);
                            break;
                        case "ALIGN_CENTER":
                            Tmp.SetHorizontalAlignment(1);
                            break;
                        case "ALIGN_RIGHT":
                            Tmp.SetHorizontalAlignment(2);
                            break; 
                        case "ALIGN_JUSTIFIED":
                            Tmp.SetHorizontalAlignment(3);
                            break;                      
                        default:
                            Tmp.SetHorizontalAlignment(0);
                            break; 
                    }
                    
                    if(this._MyTableList==null) this._MyTableList=new ArrayList<>();
                    this._MyTableList.add(Tmp);
                }
            }
            //TABLE ****************************************************
            
            //CELL ****************************************************
            NodeList NodeListCell = document.getElementsByTagName("Cell");
            for (int i = 0; i < NodeListCell.getLength(); i++) {
                Node CellAttributes = NodeListCell.item(i);
                if (CellAttributes.getNodeType() == Node.ELEMENT_NODE) {      
                    MyCell Tmp = new MyCell();
                                      
                    Tmp.SetID(((Element) CellAttributes).getAttribute("id"));
                          
                    Tmp.SetBaseFont(((Element) CellAttributes).getElementsByTagName("BaseFont").item(0).getFirstChild().getNodeValue());
                    
                    String Style = ((Element) CellAttributes).getElementsByTagName("Style").item(0).getFirstChild().getNodeValue();
                    switch(Style){
                        case "NORMAL":
                            Tmp.SetStyle(0);
                            break;
                        case "BOLD":
                            Tmp.SetStyle(1);
                            break;
                        case "ITALIC":
                            Tmp.SetStyle(2);
                            break; 
                        case "UNDERLINE":
                            Tmp.SetStyle(4);
                            break;
                        case "STRIKETHRU":
                            Tmp.SetStyle(8);
                            break;
                        case "BOLDITALIC":
                            Tmp.SetStyle(1 | 2);
                            break;                            
                        default:
                            Tmp.SetStyle(0);
                            break; 
                    }    
                    
                    Tmp.SetFontSize(Float.parseFloat(((Element) CellAttributes).getElementsByTagName("FontSize").item(0).getFirstChild().getNodeValue()));
                    
                    String TextColor = ((Element) CellAttributes).getElementsByTagName("TextColor").item(0).getFirstChild().getNodeValue();                    
                    String TxtColorSplit[] = TextColor.split(",");
                    Tmp.SetTextColor(new BaseColor(Integer.parseInt(TxtColorSplit[0], 16),Integer.parseInt(TxtColorSplit[1], 16),Integer.parseInt(TxtColorSplit[2], 16)));
                    
                    Tmp.SetBorderWidth(Float.parseFloat(((Element) CellAttributes).getElementsByTagName("BorderWidth").item(0).getFirstChild().getNodeValue()));
                    
                    String BorderColor = ((Element) CellAttributes).getElementsByTagName("BorderColor").item(0).getFirstChild().getNodeValue();                    
                    String BorderColorSplit[] = BorderColor.split(",");
                    Tmp.SetBorderColor(new BaseColor(Integer.parseInt(BorderColorSplit[0], 16),Integer.parseInt(BorderColorSplit[1], 16),Integer.parseInt(BorderColorSplit[2], 16)));

                    String BackGroundColor = ((Element) CellAttributes).getElementsByTagName("BackGroundColor").item(0).getFirstChild().getNodeValue();                    
                    String BackGroundColorSplit[] = BackGroundColor.split(",");
                    Tmp.SetBackGroundColor(new BaseColor(Integer.parseInt(BackGroundColorSplit[0], 16),Integer.parseInt(BackGroundColorSplit[1], 16),Integer.parseInt(BackGroundColorSplit[2], 16)));                      
                    
                    
                    String HorizontalAlignment = ((Element) CellAttributes).getElementsByTagName("HorizontalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(HorizontalAlignment){
                        case "ALIGN_LEFT":
                            Tmp.SetHorizontalAlignment(0);
                            break;
                        case "ALIGN_CENTER":
                            Tmp.SetHorizontalAlignment(1);
                            break;
                        case "ALIGN_RIGHT":
                            Tmp.SetHorizontalAlignment(2);
                            break; 
                        case "ALIGN_JUSTIFIED":
                            Tmp.SetHorizontalAlignment(3);
                            break;                      
                        default:
                            Tmp.SetHorizontalAlignment(0);
                            break; 
                    }
                    
                    String VerticalAlignment = ((Element) CellAttributes).getElementsByTagName("VerticalAlignment").item(0).getFirstChild().getNodeValue();
                    switch(VerticalAlignment){
                        case "ALIGN_TOP":
                            Tmp.SetVerticalAlignment(4);
                            break;
                        case "ALIGN_MIDDLE":
                            Tmp.SetVerticalAlignment(5);
                            break;
                        case "ALIGN_BOTTOM":
                            Tmp.SetVerticalAlignment(6);
                            break; 
                        case "ALIGN_BASELINE":
                            Tmp.SetVerticalAlignment(7);
                            break;                      
                        default:
                            Tmp.SetVerticalAlignment(5);
                            break; 
                    }  
                    
                    if(this._MyCellList==null) this._MyCellList=new ArrayList<>();
                    this._MyCellList.add(Tmp);
                    
                }
            }
            //CELL ****************************************************

            this._Configured=true;
        } catch (Exception e) {
            this._Configured=false;
        }


        }
        else{
            this._Configured=false;
            
        }

    }
    
    public String GetAuthor()           {return this._Author;}
    public String GetSubject()          {return this._Subject;}
    public PdfName GetVersion()          {return this._Version;}    
 
    public ArrayList<MyPage> GetMyPageList()            {return this._MyPageList        ;}
    public ArrayList<MyItem> GetMyHeaderList()          {return this._MyHeaderList      ;}
    public ArrayList<MyItem> GetMyFooterList()          {return this._MyFooterList      ;}
    public ArrayList<MyChapter> GetMyChapterList()      {return this._MyChapterList     ;}
    public ArrayList<MyParagraph> GetMyParagraphList()  {return this._MyParagraphList   ;}
    public ArrayList<MyPhrase> GetMyPhraseList()        {return this._MyPhraseList      ;} 
    public ArrayList<MyImage> GetMyImageList()          {return this._MyImageList       ;}
    public ArrayList<MyTable> GetMyTableList()          {return this._MyTableList       ;}
    public ArrayList<MyCell> GetMyCellList()            {return this._MyCellList        ;}
    
    public boolean  GetConfigured()                     {return this._Configured        ;}
    
}
