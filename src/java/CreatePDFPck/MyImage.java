/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CreatePDFPck;

import com.itextpdf.text.BaseColor;



public class MyImage {
    
    private String _ID;
    private int _HorizontalAlignment;
    private int _Border;
    private float _BorderWidth;
    private BaseColor _BorderColor;
    private String _BaseFont;    
    private int _Style;
    private float _FontSize;
    private BaseColor _TextColor;
    private boolean _Enable;
    
    
    public MyImage(){
        this._ID="";
        this._HorizontalAlignment = 0;
        this._Border = 0;
        this._BorderWidth=0;
        this._BorderColor = new BaseColor(0, 0, 0, 0);
        this._BaseFont = "";
        this._Style = 0;
        this._FontSize = 0;
        this._TextColor = new BaseColor(0, 0, 0, 0);
        this._Enable=false;
    }
    
    public MyImage(String ID,
            int HorizontalAlignment,
            int Border,
            float BorderWidth,
            BaseColor BorderColor,
            String BaseFont,
            int Style,
            float FontSize,
            BaseColor TextColor,
            boolean Enable){
        
        this._ID = ID;
        this._HorizontalAlignment = HorizontalAlignment;
        this._Border = Border;
        this._BorderWidth = BorderWidth;
        this._BorderColor = BorderColor;
        this._BaseFont = BaseFont;
        this._Style = Style;
        this._FontSize= FontSize;
        this._TextColor=TextColor;
        this._Enable=false;
    }
    
    void SetID                      (String ID)                 {this._ID                   = ID                    ;}    
    void SetHorizontalAlignment     (int HorizontalAlignment)   {this._HorizontalAlignment  = HorizontalAlignment   ;}
    void SetBorder                  (int Border)                {this._Border               = Border                ;}
    void SetBorderWidth             (float BorderWidth)         {this._BorderWidth          = BorderWidth           ;}
    void SetBorderColor             (BaseColor BorderColor)     {this._BorderColor          = BorderColor           ;}
    void SetBaseFont                (String BaseFont)           {this._BaseFont             = BaseFont              ;}
    void SetStyle                   (int Style)                 {this._Style                = Style                 ;}    
    void SetFontSize                (float FontSize)            {this._FontSize             = FontSize              ;}
    void SetTextColor               (BaseColor TextColor)       {this._TextColor            = TextColor             ;}
    void SetEnable                  (boolean Enable)            {this._Enable               = Enable                ;}    
    
    String      GetID()                     {return this._ID                    ;}
    int         GetHorizontalAlignment()    {return this._HorizontalAlignment   ;}
    int         GetBorder()                 {return this._Border                ;}
    float       GetBorderWidth()            {return this._BorderWidth           ;}
    BaseColor   GetBorderColor()            {return this._BorderColor           ;}
    String      GetBaseFont()               {return this._BaseFont              ;}    
    int         GetStyle()                  {return this._Style                 ;}
    float       GetFontSize()               {return this._FontSize              ;}
    BaseColor   GetTextColor()              {return this._TextColor             ;}
    boolean     GetEnable()                 {return this._Enable                ;}        

    
}
