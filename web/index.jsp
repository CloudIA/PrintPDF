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
            int result;
            MyDocument _MyDocument = new MyDocument("/home/marco/glassfish-4.0/glassfish4/glassfish/domains/CloudIA/applications/CloudIA_PrintPDF/","PDF_Config.xml");
            result = _MyDocument.CreateDocument("/home/marco/glassfish-4.0/glassfish4/glassfish/domains/CloudIA/applications/CloudIA_PrintPDF/", "prova.pdf");
            out.print("<br>CreateDocument=" + result); 
            result = _MyDocument.SetHeaderFooter();
            result = _MyDocument.OpenDocument();
            result = _MyDocument.AddChapter("Capitolo", "c1");
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            result = _MyDocument.AddParagraph("Paragrafo ","h1");            
            result = _MyDocument.AddChapter("Capitolo","c1");
            result = _MyDocument.AddParagraph("Paragrafo","h1");
            result = _MyDocument.AddPhrase("Lorem ipsum dolor sit amet, consectetur adipisici elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquid ex ea commodi consequat. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "p1");
            result = _MyDocument.AddParagraph("Paragrafo ","h1");
            result = _MyDocument.AddParagraph("Paragrafo ","h1");              
            result = _MyDocument.AddChapter("Cpitolo 3","c1");
            result = _MyDocument.OpenTable(7,"t1");
            result = _MyDocument.AddCell("prova","ce1",2,1);
            result = _MyDocument.AddCell("prova","ce1",2,1);
            result = _MyDocument.AddCell("prova","ce1",2,1);
            result = _MyDocument.AddCell("2","ce1",1,1);
            String tmp[] = new String[7];
            tmp[0]="1";
            tmp[1]="2";
            tmp[2]="3";
            tmp[3]="4";
            tmp[4]="5";
            tmp[5]="6";
            tmp[6]="7";
            for(int i=0;i<20;i++) _MyDocument.AddRow(tmp,"ce2");
            _MyDocument.CloseTable();
            _MyDocument.AddChapter("Capitolo","c1");
            _MyDocument.AddChapter("Capitolo","c1");
            _MyDocument.AddImage("/home/marco/online-world-map.jpg","i1","Immagine Grande");
            _MyDocument.AddImage("/home/marco/online-world-map-small.jpg","i1","Immagine Piccola");
            _MyDocument.CloseDocument();

            %>
        <h1>Hello World!</h1>
        
        <div id="pdf" style="height: 80%; width: 50%">It appears you don't have Adobe Reader or PDF support in this web browser. <a href="/pdf/sample.pdf">Click here to download the PDF</a></div>
    </body>
</html>
