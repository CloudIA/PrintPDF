

package CreatePDFPck;

import com.itextpdf.text.BaseColor;

public class MyCell {
    
    private String _ID;
    private String _BaseFont;
    private int _Style;
    private float _FontSize;
    private BaseColor _TextColor;
    private float _BorderWidth;
    private BaseColor _BorderColor; 
    private BaseColor _BackGroundColor;
    private int _HorizontalAlignment;
    private int _VerticalAlignment;
    
    public MyCell() {
        this._ID = "";
        this._BaseFont = "";
        this._Style=0;
        this._FontSize=0;
        this._TextColor= new BaseColor(0,0,0);
        this._BorderWidth=0;
        this._BorderColor= new BaseColor(0,0,0);
        this._BackGroundColor= new BaseColor(0,0,0);
        this._HorizontalAlignment=0;
        this._VerticalAlignment=0;
    }
    
    
    void SetID                      (String ID)                     {this._ID                   = ID                    ;}   
    void SetBaseFont                (String BaseFont)               {this._BaseFont             = BaseFont              ;}
    void SetStyle                   (int Style)                     {this._Style                = Style                 ;}    
    void SetFontSize                (float FontSize)                {this._FontSize             = FontSize              ;}
    void SetTextColor               (BaseColor TextColor)           {this._TextColor            = TextColor             ;}
    void SetBorderWidth             (float BorderWidth)             {this._BorderWidth          = BorderWidth           ;}
    void SetBorderColor             (BaseColor BorderColor)         {this._BorderColor          = BorderColor           ;}    
    void SetBackGroundColor         (BaseColor BackGroundColor)     {this._BackGroundColor      = BackGroundColor       ;}   
    void SetHorizontalAlignment     (int HorizontalAlignment)       {this._HorizontalAlignment  = HorizontalAlignment   ;}  
    void SetVerticalAlignment       (int VerticalAlignment)         {this._VerticalAlignment    = VerticalAlignment     ;}       
    
    
    
    String      GetID()                     {return this._ID                    ;}
    String      GetBaseFont()               {return this._BaseFont              ;}
    int         GetStyle()                  {return this._Style                 ;}
    float       GetFontSize()               {return this._FontSize              ;}
    BaseColor   GetTextColor()              {return this._TextColor             ;}
    float       GetBorderWidth()            {return this._BorderWidth           ;}
    BaseColor   GetBorderColor()            {return this._BorderColor           ;}
    BaseColor   GetBackGroundColor ()       {return this._BackGroundColor       ;} 
    int         GetHorizontalAlignment()    {return this._HorizontalAlignment   ;}
    int         GetVerticalAlignment()      {return this._VerticalAlignment     ;}
    
    
    
}
