package com.tcwong.mongodb.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
public class MongoDemoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static String COLLECTION_DEMO = "collectiondemo";

    @PostMapping("/insertDocument")
    public void insertDocument(String document) {
        //获取集合
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_DEMO);
        Document parse = Document.parse(document);
        //插入文档
        collection.insertOne(parse);
    }

    @PutMapping("/updateDocument")
    public Long updateDocument(String queryDocument, String ducument) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_DEMO);
        BasicDBObject queryParse = BasicDBObject.parse(queryDocument);
        BasicDBObject parse = BasicDBObject.parse(ducument);
        UpdateResult result = collection.updateOne(queryParse, new BasicDBObject("$set", parse));
        return result.getModifiedCount();
    }

    @PutMapping("/updateDocumentOnlyHave")
    public Long updateDocumentOnlyHave(String id, String ducument) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_DEMO);
        BasicDBObject parse = BasicDBObject.parse(ducument);
        Set<String> keySet = parse.keySet();
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("id", id);
        for (String key : keySet) {
            dbObject.put(key, new BasicDBObject("$exists", true));
        }
        UpdateResult result = collection.updateOne(dbObject, new BasicDBObject("$set", parse));
        return result.getModifiedCount();
    }

    @GetMapping("/listDocuments")
    public List<Document> findDocuments() {
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_DEMO);
        FindIterable<Document> documents = collection.find();
        List<Document> listDocuments = new ArrayList<>();
        for (Document document : documents) {
            listDocuments.add(document);
        }
        return listDocuments;
    }


    @DeleteMapping("/deleteDocument")
    public DeleteResult deleteDocument(String name) {
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_DEMO);
        DeleteResult result = collection.deleteOne(new BasicDBObject("name", name));
        return result;
    }

}

