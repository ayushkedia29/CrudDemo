package com.unbxd.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.unbxd.model.Student;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrudCollectionsImpl implements CrudCollections {

    @Override
    public void insetInTo(MongoClient mongoClient) {
        MongoCollection collection = mongoClient.getDatabase("db1").getCollection("Student_Collection");
        Document cr = new Document("id",1);
        cr.append("firstname","Cristiano");
        cr.append("lastname","Ronaldo");
        cr.append("age","15");

        Document messi = new Document("id",2);
        messi.append("firstname","Lionel");
        messi.append("lastname","Messi");
        messi.append("age","14");

        Document tp = new Document("id",3);
        tp.append("firstname","");
        tp.append("lastname","");
        tp.append("age","");

        collection.insertOne(cr);
        collection.insertOne(messi);
        collection.insertOne(tp);

    }

    @Override

    public Object readCollection(MongoClient mongoClient, int id) throws JsonProcessingException {
        MongoCollection collection = mongoClient.getDatabase("db1").getCollection("Student_Collection");
        Document doc = (Document) collection.find(eq("id", id)).first();
        if(doc==null){
            return "No record found";
        }
        System.out.println(doc);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Student student = objectMapper.readValue(doc.toJson(), Student.class);
        /*

        student.setAge((Integer) doc.get("age"));
        student.setFirstname((String) doc.get("firstname"));
        student.setLastname((String) doc.get("lastname"));
        student.setId((Integer) doc.get("id"));
         */
        return student;
    }

    @Override
    public void updateCollection(MongoClient mongoClient, int id) {
        MongoCollection collection = mongoClient.getDatabase("db1").getCollection("Student_Collection");

        BasicDBObject searchQry = new BasicDBObject("id", 3);
        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("firstname", "Neymar");
        updateFields.append("lastname", "Junior");
        updateFields.append("age", 12);
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);
        collection.updateOne(searchQry, setQuery);
    }

    @Override
    public long deleteCollection(MongoClient mongoClient, int id) {
        MongoCollection collection = mongoClient.getDatabase("db1").getCollection("Student_Collection");
        collection.deleteOne(Filters.eq("id", id));
        return collection.count();
    }

    @Override
    public void insetNew(MongoClient mongoClient, Student student) {
        MongoCollection collection = mongoClient.getDatabase("db1").getCollection("Student_Collection");
        Document newdoc = new Document("id",student.getId());
        newdoc.append("firstname",student.getFirstname());
        newdoc.append("lastname",student.getLastname());
        newdoc.append("age",student.getAge());
    }
}
