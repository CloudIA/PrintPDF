package CreatePDFPck;

import com.itextpdf.text.BaseColor;

public class MyItem {

    private String _ID;
    private float _Width;
    private String _Content;
    private int _HorizontalAlignment;
    private int _VerticalAlignment;
    private int _Border;
    private float _Height;
    private float _FontSize;
    private String _BaseFont;
    private int _Style;
    private BaseColor _BaseColor;
    private boolean _Enable;

    public MyItem() {
        this._ID="";
        this._Width = 0;
        this._Content = "";
        this._HorizontalAlignment = 0;
        this._VerticalAlignment = 0;
        this._Border = 0;
        this._Height = 0;
        this._FontSize = 0;
        this._BaseFont = "";
        this._Style = 0;
        this._BaseColor = new BaseColor(0, 0, 0, 0);
        this._Enable=false;
    }

    public MyItem(String ID,
            float Width,
            String Content,
            int HorizontalAlignment,
            int VerticalAlignment,
            int Border,
            float Height,
            float FontSize,
            String BaseFont,
            int Style,
            BaseColor BaseColor,
            boolean Enable) {
        
        this._ID=ID;
        this._Width = Width;
        this._Content=Content;
        this._HorizontalAlignment = HorizontalAlignment;
        this._VerticalAlignment = VerticalAlignment;
        this._Border = Border;
        this._Height=Height;
        this._FontSize=FontSize;
        this._BaseFont=BaseFont;
        this._Style = Style;
        this._BaseColor=BaseColor;
        this._Enable = Enable;
    }
    
    void SetID                      (String ID)                 {this._ID                   = ID                    ;}    
    void SetWidth                   (float Width)               {this._Width                = Width                 ;}
    void SetContent                 (String Content)            {this._Content              = Content               ;}
    void SetHorizontalAlignment     (int HorizontalAlignment)   {this._HorizontalAlignment  = HorizontalAlignment   ;}
    void SetVerticalAlignment       (int VerticalAlignment)     {this._VerticalAlignment    = VerticalAlignment     ;}
    void SetBorder                  (int Border)                {this._Border               = Border                ;}
    void SetHeight                  (float Height)              {this._Height               = Height                ;}
    void SetFontSize                (float FontSize)            {this._FontSize             = FontSize              ;}
    void SetBaseFont                (String BaseFont)           {this._BaseFont             = BaseFont              ;}
    void SetStyle                   (int Style)                 {this._Style                = Style                 ;}
    void SetBaseColor               (BaseColor BaseColor)       {this._BaseColor            = BaseColor             ;}
    void SetEnable                  (boolean Enable)            {this._Enable               = Enable                ;}    
    
    String      GetID()                     {return this._ID                    ;}
    float       GetWidth()                  {return this._Width                 ;}
    String      GetContent()                {return this._Content               ;}
    int         GetHorizontalAlignment()    {return this._HorizontalAlignment   ;}
    int         GetVerticalAlignment()      {return this._VerticalAlignment     ;}
    int         GetBorder()                 {return this._Border                ;}
    float       GetHeight()                 {return this._Height                ;}
    float       GetFontSize()               {return this._FontSize              ;}
    String      GetBaseFont()               {return this._BaseFont              ;}
    int         GetStyle()                  {return this._Style                 ;}
    BaseColor   GetBaseColor()              {return this._BaseColor             ;}
    boolean     GetEnable()                 {return this._Enable                ;}    
    
    
    
}
