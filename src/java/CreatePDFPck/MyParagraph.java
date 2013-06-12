/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CreatePDFPck;

import com.itextpdf.text.BaseColor;


public class MyParagraph {
    
    private String _ID;
    private String _BaseFont;
    private int _Style;
    private float _FontSize;
    private BaseColor _TextColor;
    private float _IndentationLeft;
    private float _IndentationRight;
    private float _SpacingBefore;
    private float _SpacingAfter;
    private boolean _NewPage;
    private boolean _Numbered;

    
    public MyParagraph(String ID, 
            String BaseFont,
            int Style,
            float FontSize,
            BaseColor TextColor, 
            float IndentationLeft, 
            float IndentationRight, 
            float SpacingBefore, 
            float SpacingAfter, 
            boolean NewPage, 
            boolean Numbered){
        this._ID=ID;
        this._BaseFont= BaseFont;
        this._Style = Style;
        this._FontSize = FontSize;
        this._TextColor = TextColor;
        this._IndentationLeft = IndentationLeft;
        this._IndentationRight= IndentationRight;
        this._SpacingBefore= SpacingBefore;
        this._SpacingAfter = SpacingAfter;
        this._NewPage= NewPage;
        this._Numbered = Numbered;
    }    
    
    public MyParagraph(){
        this._ID="";
        this._BaseFont=null;
        this._Style = 0;
        this._FontSize=0;
        this._TextColor = new BaseColor(255,255,255);
        this._IndentationLeft=0;
        this._IndentationRight=0;
        this._SpacingBefore=0;
        this._SpacingAfter=0;
        this._NewPage=false;
        this._NewPage = true;   
    }

    void SetID                      (String ID)                 {this._ID                   = ID                    ;}   
    void SetBaseFont                (String BaseFont)           {this._BaseFont             = BaseFont              ;}
    void SetStyle                   (int Style)                 {this._Style                = Style                 ;}    
    void SetFontSize                (float FontSize)            {this._FontSize             = FontSize              ;}
    void SetTextColor               (BaseColor TextColor)       {this._TextColor            = TextColor             ;}
    void SetIndentationLeft         (float IndentationLeft)     {this._IndentationLeft      = IndentationLeft       ;}
    void SetIndentationRight        (float IndentationRight)    {this._IndentationRight     = IndentationRight      ;}
    void SetSpacingBefore           (float SpacingBefore)       {this._SpacingBefore        = SpacingBefore         ;}    
    void SetSpacingAfter            (float SpacingAfter )       {this._SpacingAfter         = SpacingAfter          ;}   
    void SetNewPage                 (boolean NewPage )          {this._NewPage              = NewPage               ;}     
    void SetNumbered                (boolean Numbered)          {this._Numbered             = Numbered              ;}    
    
    
    String      GetID()                     {return this._ID                    ;}
    String      GetBaseFont()               {return this._BaseFont              ;}
    int         GetStyle()                  {return this._Style                 ;}
    float       GetFontSize()               {return this._FontSize              ;}
    BaseColor   GetTextColor()              {return this._TextColor             ;}
    float       GetIndentationLeft()        {return this._IndentationLeft       ;}
    float       GetIndentationRight()       {return this._IndentationRight      ;}
    float       GetSpacingAfter()           {return this._SpacingAfter          ;}
    float       GetSpacingBefore()          {return this._SpacingBefore         ;}
    boolean     GetNewPage()                {return this._NewPage               ;}
    boolean     GetNumbered()               {return this._Numbered              ;}


}
