
package CreatePDFPck;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

public class MyPage {
    private String _ID;             //ID della formattazione della pagina
    private Rectangle _Size;        //Dimensione della pagina
    private BaseColor _BackColor;   //Colore del fondo della pagina
    private float _MarginLeft;      //Margine sinistro
    private float _MarginRight;     //Margine destro
    private float _MarginTop;       //Margine alto
    private float _MarginBottom;    //Margine basso
    
    public MyPage(String ID, Rectangle Size, BaseColor BackColor, float MarginLeft, float MarginRight, float MarginTop, float MarginBottom){
        this._ID=ID;
        this._Size = Size;
        this._BackColor = BackColor;
        this._MarginLeft = MarginLeft;
        this._MarginRight = MarginRight;
        this._MarginTop = MarginTop;
        this._MarginBottom = MarginBottom;
    }
    
    public MyPage(){
        this._ID="";
        this._Size=PageSize.A4;
        this._BackColor = new BaseColor(255,255,255);
        this._MarginLeft=36;
        this._MarginRight=72;
        this._MarginTop=108;
        this._MarginBottom=180;
        
    }
    
    public void SetID           (String ID)             {this._ID = ID;}
    public void SetSize         (Rectangle Size)        {this._Size = Size;}
    public void SetBackColor    (BaseColor BackColor)   {this._BackColor=BackColor;}
    public void SetMarginLeft   (float MarginLeft)      {this._MarginLeft = MarginLeft;}
    public void SetMarginRight  (float MarginRight)     {this._MarginRight = MarginRight;}
    public void SetMarginTop    (float MarginTop)       {this._MarginTop = MarginTop;}
    public void SetMarginBottom (float MarginBottom)    {this._MarginBottom = MarginBottom;}
    
    public String       GetID()             {return this._ID;}
    public Rectangle    GetSize()           {return this._Size;}
    public BaseColor    GetBaseColor()      {return this._BackColor;}
    public float        GetMarginLeft()     {return this._MarginLeft;}
    public float        GetMarginRight()    {return this._MarginRight;}
    public float        GetMarginTop()      {return this._MarginTop;}
    public float        GetMarginBottom()   {return this._MarginBottom;}
    
    
}
