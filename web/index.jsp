<%@page import="com.itextpdf.text.Font.FontFamily"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="java.awt.Color"%>
<%@page import="CreatePDFPck.MyDocument" %>
<%@page import="CreatePDFPck.MyConfiguration" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="pdfobject.js"></script>
        <script type="text/javascript" src="pdfobject.js"></script>
        <script type="text/javascript">

        window.onload = function (){

        var success = new PDFObject({ url: "prova.pdf" }).embed("pdf");
	
        };

        </script>        

        
    </head>
    <body>
        <%
            boolean debug = false;
            int result;
            MyDocument _MyDocument = new MyDocument("/home/marco/glassfish-4.0/glassfish4/glassfish/domains/CloudIA/applications/CloudIA_PrintPDF/","PDF_Config.xml");
            result = _MyDocument.CreateDocument("/home/marco/glassfish-4.0/glassfish4/glassfish/domains/CloudIA/applications/CloudIA_PrintPDF/", "prova.pdf");
            if(debug) out.print("<br>CreateDocument=" + result); 
            result = _MyDocument.SetHeaderFooter();
            if(debug) out.print("<br>SetHeaderFooter=" + result); 
            result = _MyDocument.OpenDocument();
            if(debug) out.print("<br>OpenDocument=" + result); 
            result = _MyDocument.AddChapter("Capitolo", "c1");
            if(debug) out.print("<br>AddChapter=" + result); 
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            if(debug) out.print("<br>AddParagraph=" + result); 
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            if(debug) out.print("<br>AddParagraph=" + result); 
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            if(debug) out.print("<br>AddParagraph=" + result); 
            result = _MyDocument.AddChapter("Capitolo","c1");
            if(debug) out.print("<br>AddChapter=" + result); 
            result = _MyDocument.AddParagraph("Paragrafo","h1");
            if(debug) out.print("<br>AddParagraph=" + result); 
            result = _MyDocument.AddPhrase("Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "p1");
            if(debug) out.print("<br>AddPhrase=" + result); 
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            if(debug) out.print("<br>AddParagraph=" + result); 
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            if(debug) out.print("<br>AddParagraph=" + result); 
            result = _MyDocument.AddChapter("Cpitolo 3","c1");
            if(debug) out.print("<br>AddChapter=" + result); 
            result = _MyDocument.OpenTable(7,"t1");
            if(debug) out.print("<br>OpenTable=" + result); 
            result = _MyDocument.AddCell("prova","ce1",2,1);
            if(debug) out.print("<br>AddCell=" + result); 
            result = _MyDocument.AddCell("prova","ce1",2,1);
            if(debug) out.print("<br>AddCell=" + result); 
            result = _MyDocument.AddCell("prova","ce1",2,1);
            if(debug) out.print("<br>AddCell=" + result); 
            result = _MyDocument.AddCell("2","ce1",1,1);
            if(debug) out.print("<br>AddCell=" + result); 
            String tmp[] = new String[7];
            tmp[0]="1";
            tmp[1]="2";
            tmp[2]="3";
            tmp[3]="4";
            tmp[4]="5";
            tmp[5]="6";
            tmp[6]="7";
            for(int i=0;i<20;i++) result=_MyDocument.AddRow(tmp,"ce2");
            if(debug) out.print("<br>AddRow=" + result); 
            result = _MyDocument.CloseTable();
            if(debug) out.print("<br>CloseTable=" + result); 
            result = _MyDocument.AddChapter("Capitolo","c1");
            if(debug) out.print("<br>AddChapter=" + result); 
            result = _MyDocument.AddChapter("Capitolo","c1");
            if(debug) out.print("<br>AddChapter=" + result); 
            result = _MyDocument.AddImage("/home/marco/online-world-map.jpg","i1","Immagine Grande");
            if(debug) out.print("<br>AddImage=" + result); 
            result = _MyDocument.AddImage("/home/marco/online-world-map-small.jpg","i1","Immagine Piccola");
            if(debug) out.print("<br>AddImage=" + result); 
            result = _MyDocument.CloseDocument();
            if(debug) out.print("<br>CloseDocument=" + result); 

            %>
        <h1>Hello World!</h1>
        
        <div id="pdf" style="height: 80%; width: 50%">It appears you don't have Adobe Reader or PDF support in this web browser. <a href="/pdf/sample.pdf">Click here to download the PDF</a></div>
    </body>
</html>
