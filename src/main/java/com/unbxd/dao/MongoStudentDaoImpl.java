package com.unbxd.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.unbxd.model.Student;
import com.unbxd.util.MongoStandAloneClient;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MongoStudentDaoImpl implements StudentDao {
    private final MongoClient mongoClient;
    private MongoCollection collection;

    @Inject
    public MongoStudentDaoImpl(MongoStandAloneClient mgClient){
        this.mongoClient = mgClient.getClient();
         this.collection = mongoClient.getDatabase("db1").getCollection("Student_Collection");
    }

    @Override

    public Student readDB(int id) throws JsonProcessingException {
        Document doc = (Document) collection.find(eq("id", id)).first();
        if(doc==null){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper.readValue(doc.toJson(), Student.class);
    }

    @Override
    public boolean updateDB(int id, Student student) {

        Document doc = (Document) collection.find(eq("id", id)).first();
        if(doc==null){
            return false;
        }

        BasicDBObject searchQry = new BasicDBObject("id", id);
        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("firstname", student.getFirstname());
        updateFields.append("lastname", student.getLastname());
        updateFields.append("age", student.getAge());
        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);
        collection.updateOne(searchQry, setQuery);
        return true;
    }

    @Override
    public boolean deleteDB(int id) {

        Document doc = (Document) collection.find(eq("id", id)).first();
        if(doc==null){
            return false;
        }

        collection.deleteOne(Filters.eq("id", id));
        return true;
    }

    @Override
    public void insetDB(Student student) {
        Document doc = (Document) collection.find(eq("id", student.getId())).first();
        if(doc!=null){
            collection.deleteOne(Filters.eq("id", student.getId()));
        }
        Document newdoc = new Document("id",student.getId());
        newdoc.append("firstname",student.getFirstname());
        newdoc.append("lastname",student.getLastname());
        newdoc.append("age",student.getAge());

        collection.insertOne(newdoc);
    }

}
