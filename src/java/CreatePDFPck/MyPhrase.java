
package CreatePDFPck;

import com.itextpdf.text.BaseColor;



public class MyPhrase {
    
    private String _ID;
    private String _BaseFont;
    private int _Style;
    private float _FontSize;    
    private BaseColor _TextColor;
    private float   _SpacingBefore;     
    private float   _SpacingAfter;
    private int _HorizontalAlignment;
    private float   _IndentationLeft;   
    private float   _IndentationRight;
    
    
    public MyPhrase(String ID, 
            String BaseFont,
            int Style,
            float FontSize,
            BaseColor TextColor,
            float SpacingBefore, 
            float SpacingAfter,
            int HorizontalAlignment,
            float IndentationLeft, 
            float IndentationRight){
        this._ID=ID;
        this._BaseFont= BaseFont;
        this._Style = Style;
        this._FontSize = FontSize;
        this._TextColor = TextColor;
        this._HorizontalAlignment = HorizontalAlignment;
        this._SpacingBefore= SpacingBefore;
        this._SpacingAfter = SpacingAfter;
        this._IndentationLeft = IndentationLeft;
        this._IndentationRight= IndentationRight;        

    }
    
    public MyPhrase(){
        this._ID = "";
        this._BaseFont=null;
        this._Style=0;
        this._FontSize =0;
        this._TextColor= new BaseColor(255,255,255);
        this._HorizontalAlignment=0;
        this._SpacingBefore=0;
        this._SpacingAfter=0;
        this._IndentationLeft = 0;
        this._IndentationRight= 0;  
        
    }
    
    public void SetID                       (String ID)                 {this._ID                   = ID                    ;}   
    public void SetBaseFont                 (String BaseFont)           {this._BaseFont             = BaseFont              ;}   
    public void SetStyle                    (int Style)                 {this._Style                = Style                 ;}
    public void SetFontSize                 (float FontSize)            {this._FontSize             = FontSize              ;}
    public void SetTextColor                (BaseColor TextColor)       {this._TextColor            = TextColor             ;}
    public void SetHorizontalAlignment      (int HorizontalAlignment)   {this._HorizontalAlignment  = HorizontalAlignment   ;}
    public void SetSpacingBefore            (float SpacingBefore)       {this._SpacingBefore        = SpacingBefore         ;}              
    public void SetSpacingAfter             (float SpacingAfter)        {this._SpacingAfter         = SpacingAfter          ;}
    public void SetIndentationLeft          (float IndentationLeft)     {this._IndentationLeft      = IndentationLeft       ;}
    public void SetIndentationRight         (float IndentationRight)    {this._IndentationRight     = IndentationRight      ;}
    
    
    public String       GetID()                     {return this._ID                    ;}
    public String       GetBaseFont()               {return this._BaseFont              ;}
    public int          GetStyle()                  {return this._Style                 ;}
    public float        GetFontSize()               {return this._FontSize              ;}
    public BaseColor    GetTextColor()              {return this._TextColor             ;}
    public int          GetHorizontalAlignment()    {return this._HorizontalAlignment   ;}   
    public float        GetSpacingAfter()           {return this._SpacingAfter          ;}
    public float        GetSpacingBefore()          {return this._SpacingBefore         ;}    
    public float        GetIndentationLeft()        {return this._IndentationLeft       ;}
    public float        GetIndentationRight()       {return this._IndentationRight      ;}

  
    
    
}
