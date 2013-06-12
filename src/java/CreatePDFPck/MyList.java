/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CreatePDFPck;

import com.itextpdf.text.List;

/**
 *
 * @author marco.bonamente
 */
public class MyList {
    
    List _List;
    
    public MyList(){ 
        _List = new List();
        
    }
    
    public void AddListItem(String Item){
        _List.add(Item);
    }
    
    List ReturnList(){
        return _List;
    }
    
    
}
