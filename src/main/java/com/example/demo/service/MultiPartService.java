package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Document;
import com.example.demo.repository.DocumentRepository;

@Service
public class MultiPartService {
	
	@Autowired
	DocumentRepository documentRepository;

	public Map<String, Object> saveFile(MultipartFile file){
		Map<String, Object> result = new HashMap<>();
		String fileName = file.getOriginalFilename();
		String filePath = "/home/piyush/Videos/"+fileName;
		File dest = new File(filePath);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
		Logger log= 	Logger.getLogger(MultiPartService.class.getName());
		log.log(log.getLevel().ALL, "Context", e);
		log.warning(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		result.put("fileName", fileName);
		result.put("filePath", filePath);
		return result;
	}
	


	public Map<String, Object> insertUser(Document document) {
    Map<String, Object> result = new HashMap<>();
    Document document2 = documentRepository.findByDocumentId(document.getDocumentId());
    System.out.println("document2============"+document2);
    if(document2 != null) {
    	result.put("response", "error");
    	return result;
    }
		Document document1 = documentRepository.save(document);
		result.put("response", document1);
		return result;
	}



	public Map<String, Object> fetchUser() {
        Map<String, Object> result = new HashMap<>();
        List<Document> documentList = documentRepository.findAll();
        List<Document> documents = new ArrayList<>();
        for(Document document: documentList) {
        	if(document.isKYCDone()) {
        		documents.add(document);
        	}
        }
        result.put("response", documents);
		return result;
	}



	public Map<String, Object> doKyc(Map<String, Object> body) {
		Map<String, Object> result = new HashMap<>();
		String documentId = (String) body.get("documentId");
	    Document document2 = documentRepository.findByDocumentId(documentId);
	    if(body.get("key").equals("accept")) {
		    document2.setKYCDone(true);
		    documentRepository.save(document2);
	    }
	    result.put("response", document2);
		return result;
	}
	
	
	
}
