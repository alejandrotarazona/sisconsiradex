/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author SisCon
 */
public class MostrarPDF extends DispatchAction { 

    /* forward name="success" path="" */

     public ActionForward displayPdf(ActionMapping mapping, ActionForm form,  
            HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
  
        /*String filepath = "/test/pdf/sample.pdf";  
          
        //For Local:   
        String baseFolder = "/Users/raghu/Documents/Shared/personal/Final_Out";  
                  
        String contentType = getServlet().getServletContext().getMimeType(filepath);  
        response.setContentType(contentType);  
        //New  
        response.setHeader("Expires", "0");  
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");  
        response.setHeader("Pragma", "public");  
          
        OutputStream out = response.getOutputStream();  
        byte[] pdfContent = this.loadFile(baseFolder + filepath);  
        out.write(pdfContent, 0, pdfContent.length);   
        if (out != null) {  
            out.close();  
        }  
  
        return null;  
    }  
          
    public static byte[] loadFile(String sourcePath) {  
        byte[] output = null;  
        InputStream inputStream = null;  
        try {  
            inputStream = new FileInputStream(sourcePath);  
            output = readFully(inputStream);  
            if (inputStream != null) {  
                inputStream.close();  
            }  
        }   
        catch(IOException ioex) {  
            //Handle Exception  
        }         
  
        return output;  
    }
    * struts-config.xml 
------------------ 
<action path="/pdf" type="demo.struts.action.BaseAction" name="saform" parameter="action"> 
<forward name="home" path="/pdf/home.jsp"/> 
</action> 

JSP 
--- 
<% 
String pdfurl3 = request.getContextPath() + "/pdf.do?action=displayPdf"; 
%> 
Click 
<a href="#" onClick="showPdf('<%=pdfurl3%>'); return false;">here to see the pdf file 

javascript 
---------- 
var theChild; 
function showPdf(url) { 
if(theChild!=null) { 
if(getCurrentBrowserName() != 'msie') { 
theChild.close(); 
} 
} 
theChild = popupPdf(url); 
theChild.focus(); 
} 

function popupPdf(url) { 
alert('Attention: The document will be opened in another window. Please close window after printing or viewing!'); 
var attributes = 'menubar=no,toolbar=no,scrollbars=yes,resizable=yes,fullscreen=no'; 
attributes = attributes + ',width=' + (screen.availWidth - 150); 
attributes = attributes + ',height=' + (screen.availHeight - 250); 
attributes = attributes + ',screenX=50,screenY=50,left=10,top=10'; 
var wOpen; 
wOpen = window.open(url, 'theChild', attributes); 

//wOpen.focus(); 
wOpen.moveTo(75,50); 
wOpen.resizeTo(screen.availWidth, screen.availHeight); 

return wOpen; 

}	

function getCurrentBrowserName() { 
var browserName = ""; 

var ua = navigator.userAgent.toLowerCase(); 
if ( ua.indexOf( "opera" ) != -1 ) { 
browserName = "opera"; 
} 
else if ( ua.indexOf( "msie" ) != -1 ) { 
browserName = "msie"; 
} 
else if ( ua.indexOf( "safari" ) != -1 ) { 
browserName = "safari"; 
} 
else if ( ua.indexOf( "mozilla" ) != -1 ) { 
if ( ua.indexOf( "firefox" ) != -1 ) { 
browserName = "firefox"; 
} 
else { 
browserName = "mozilla"; 
} 
} 
else { 
browserName = ""; 
} 

return browserName; 
}	

Action Class 
------------ 
public class BaseAction extends DispatchAction { 

public ActionForward displayPdf(ActionMapping mapping, ActionForm form, 
HttpServletRequest request, HttpServletResponse response) 
throws Exception { 

String filepath = "/test/pdf/sample.pdf"; 

//For Local: 
String baseFolder = "/Users/raghu/Documents/Shared/personal/Final_Out"; 

String contentType = getServlet().getServletContext().getMimeType(filepath); 
response.setContentType(contentType); 
//New 
response.setHeader("Expires", "0"); 
response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0"); 
response.setHeader("Pragma", "public"); 

OutputStream out = response.getOutputStream(); 
byte[] pdfContent = this.loadFile(baseFolder + filepath); 
out.write(pdfContent, 0, pdfContent.length); 
if (out != null) { 
out.close(); 
} 

return null; 
} 

public static byte[] loadFile(String sourcePath) { 
byte[] output = null; 
InputStream inputStream = null; 
try { 
inputStream = new FileInputStream(sourcePath); 
output = readFully(inputStream); 
if (inputStream != null) { 
inputStream.close(); 
} 
} 
catch(IOException ioex) { 
//Handle Exception 
}	

return output; 
} 
}*/
         return null;
}
}