package org.sap.era.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNameConstraintViolationException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

import com.sap.ecm.api.RepositoryOptions;
import com.sap.ecm.api.RepositoryOptions.Visibility;
import com.sap.ecm.api.EcmService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DocumentService {
	static String uniqueName = "com.foo.MyRepository";
	static String secretKey = "my_super_secret_key_123";
	static String lookupName = "java:comp/env/" + "EcmService";
	Session openCmisSession;
	InitialContext ctx;
	EcmService ecmSvc;
	Folder root;
	
	public DocumentService(){
		try {
			openCmisSession = null;
			ctx = new InitialContext();
			ecmSvc = (EcmService) ctx.lookup(lookupName);
			try {
		        // connect to my repository
		        openCmisSession = ecmSvc.connect(uniqueName, secretKey);
		      }
		      catch (CmisObjectNotFoundException e) {
		        // repository does not exist, so try to create it
		        RepositoryOptions options = new RepositoryOptions();
		        options.setUniqueName(uniqueName);
		        options.setRepositoryKey(secretKey);
		        options.setVisibility(Visibility.PROTECTED);
		        ecmSvc.createRepository(options);
		        // should be created now, so connect to it
		        openCmisSession = ecmSvc.connect(uniqueName, secretKey);
		      }
			root = openCmisSession.getRootFolder();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// create a new file in the root folder
	public Document createNewDocument(String docName,String path) throws IOException{
		Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
	    properties.put(PropertyIds.NAME, docName);
	    FileInputStream stream = new FileInputStream(path);
	    long streamSize = (long)stream.available();
	    ContentStream contentStream = openCmisSession.getObjectFactory()
                .createContentStream("HelloWorld.txt",streamSize,"text/plain; charset=UTF-8", stream);
	    Document doc = root.createDocument(properties, contentStream, VersioningState.NONE);
	    //doc.getId()
	    
		return doc;
	}
	
	// find a file in the root folder
	public Document getDocumentByID(String docID){
		
		ItemIterable<CmisObject> children = root.getChildren();
		Document doc = null;
		for (CmisObject o : children) {
			if (o.getId().equals(docID)){
				doc = (Document)o;
				break;
			}
		}
		//doc.
		return doc;
	}
	
	
}
