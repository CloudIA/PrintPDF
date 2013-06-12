
package CreatePDFPck;



public class MyTable {

     private String _ID;
     private float _WidthPercentage;
     private float _SpacingBefore;
     private float _SpacingAfter;
     private int _HorizontalAlignment;
     
     public MyTable(){
         this._ID="";
         this._WidthPercentage=0;
         this._SpacingBefore=0;
         this._SpacingAfter=0;
         this._HorizontalAlignment=0;
     }
     
     public MyTable(String ID,
             float WidthPercentage,
             float SpacingBefore,
             float SpacingAfter,
             int HorizontalAlignment){
         this._ID=ID;
         this._WidthPercentage=WidthPercentage;
         this._SpacingBefore=SpacingBefore;
         this._SpacingAfter=SpacingAfter;
         this._HorizontalAlignment=HorizontalAlignment;
     }
     
    void SetID                      (String ID)                 {this._ID                   = ID                    ;}
    void SetWidthPercentage         (float WidthPercentage)     {this._WidthPercentage      = WidthPercentage       ;}       
    void SetSpacingBefore           (float SpacingBefore)       {this._SpacingBefore        = SpacingBefore         ;}
    void SetSpacingAfter            (float SpacingAfter)        {this._SpacingAfter         = SpacingAfter          ;}  
    void SetHorizontalAlignment     (int HorizontalAlignment)   {this._HorizontalAlignment  = HorizontalAlignment   ;}
    
    String      GetID()                     {return this._ID                    ;}
    float       GetWidthPercentage()        {return this._WidthPercentage       ;}    
    float       GetSpacingBefore()          {return this._SpacingBefore         ;}
    float       GetSpacingAfter()           {return this._SpacingAfter          ;}
    int         GetHorizontalAlignment()    {return this._HorizontalAlignment   ;}
    
    
}


