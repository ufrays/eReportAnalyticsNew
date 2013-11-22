package org.sap.era.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.Document;
import org.sap.era.service.DocumentService;
import org.sap.era.service.excel.CmisHelper;

/**
 * Servlet implementation class FileDownloadServlet
 */
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private Document doc;
	
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action"); 
        if (action != null && action.equalsIgnoreCase("getfile")) {  
            String docId = request.getParameter("docid");  
            
            CmisHelper cmisHelper = CmisHelper.retrieveCmisHelperClass(request);  
            Document doc = cmisHelper.getDocumentById(docId);
            response.setContentType(CONTENT_TYPE);
            //doc.get
            response.setHeader("Content-Disposition","attachment;filename="+ doc.getName());
            cmisHelper.streamOutDocument(response, docId);  
            
        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
