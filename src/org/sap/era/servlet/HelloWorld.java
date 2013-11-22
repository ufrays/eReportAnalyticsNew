package org.sap.era.servlet;

import java.io.File;  
import java.io.FileInputStream;
import java.io.IOException;  
import java.io.InputStream;
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.sap.era.service.excel.*; 
  
/** 
 * Servlet implementation class HelloWorld 
 */  
public class HelloWorld extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public HelloWorld() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
  
        // Read-in the action parameter  
        String action = request.getParameter("action");  
  
        // Show the list of all files  
        if (action == null || action.equalsIgnoreCase("list")) {  
            response.getWriter().println("<html><head><title>SAP HANA Cloud Platform: MyDocs App</title></head><body>");  
            CmisHelper cmisHelper = retrieveCmisHelperClass(request);  
            List<MyDocsDTO> docs = cmisHelper.getDocumentsList();  
            response.getWriter().println(buildHtmlPageForShowingAllDocuments(docs));  
            response.getWriter().println("</body></html>");  
        }  
  
        // If the user wants to get the file out of the repository...  
        if (action != null && action.equalsIgnoreCase("getfile")) {  
            String docId = request.getParameter("docid");  
            CmisHelper cmisHelper = retrieveCmisHelperClass(request);  
            
            cmisHelper.streamOutDocument(response, docId);  
        }  
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String realPathOfApp = getServletContext().getRealPath("");  
        CmisHelper cmis = new CmisHelper();  
        File file = cmis.uploadDocument(realPathOfApp, request);  
        InputStream is = new FileInputStream(file);
        if (is == null){
        	throw new IOException();
        }
        cmis.addDocument(file);  
        // Delete the file as it is in the temporary folder on your server  
        file.delete();  
  
        // Print out the list of all files  
        // After having add the file above that file should now appear in the list  
        response.getWriter().println("<html><head><title>SAP HANA Cloud Platform: MyDocs App</title></head><body>");  
        CmisHelper cmisHelper = retrieveCmisHelperClass(request);  
        List<MyDocsDTO> docs = cmisHelper.getDocumentsList();  
        response.getWriter().println(buildHtmlPageForShowingAllDocuments(docs));  
        response.getWriter().println("</body></html>");  
    }  
  
    /** 
     *  
     * @param docs 
     * @return HTML string with the field for the file upload and the lkist of all files 
     */  
    private String buildHtmlPageForShowingAllDocuments(List<MyDocsDTO> docs) {  
        String result = "";  
        // Print the "Upload File" field prior to the list of files, so that the end user can upload files  
        result += "<fieldset><legend>Upload File</legend><form action='HelloWorld'";  
        result += "method='post' enctype='multipart/form-data'>";  
        result += "<label for='filename'>File: </label><input id='filename' type='file' name='filename' size='50'/><br/>";  
        result += "<input type='submit' value='Upload File'/></form></fieldset>";  
  
        // And now print the list of files  
        result += "<h1>List of all files</h1>";  
        result += "<ul>";  
  
        if (docs != null && docs.size() > 0) {  
            for (int i = 0; i < docs.size(); i++) {  
                MyDocsDTO doc = docs.get(i);  
                String row = "<li>" + "<a href=" + '"' + doc.getDownloadLink() + '"' + ">" + doc.getFilename() + "<a> (" + doc.getFileLength() + " bytes)</li>";  
                result += row;  
            }  
        }  
  
        result += "</ul>";  
        return result;  
    }  
  
    /** 
     * This method ensures that an existing CmisHelper class that was saved in the HttpServletRequest is re-used whenever 
     * possible instead of creating new instances of CmisHelper classes everytime you call the doGet or doPost from the 
     * method 
     *  
     * @param request 
     *            The HttpServletRequest 
     * @return The CmisHelper class 
     */  
    private CmisHelper retrieveCmisHelperClass(HttpServletRequest request) {  
        CmisHelper result = null;  
        if (request != null) {  
            HttpSession httpSession = request.getSession();  
            if (httpSession != null) {  
                CmisHelper cmisHelperHttpSession = (CmisHelper) httpSession.getAttribute("myCmisHelper");  
                // If an instance of CmisHelper is already there, use it  
                if (cmisHelperHttpSession != null) {  
                    result = cmisHelperHttpSession;  
                }  
                // If there isn't one, create a new one and store it in the session of the HttpServletRequest so that it  
                // can be re-used the next time  
                else {  
                    result = new CmisHelper();  
                    httpSession.setAttribute("myCmisHelper", result);  
                }  
  
            }  
        }  
        return result;  
    }  
}  