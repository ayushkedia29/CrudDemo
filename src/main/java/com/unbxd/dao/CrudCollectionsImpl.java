package com.unbxd.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.unbxd.model.Student;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class CrudCollectionsImpl implements CrudCollections {

    @Override
    public void insetInTo(MongoCollection collection) {
        Document cr = new Document("id",1);
        cr.append("firstname","Cristiano");
        cr.append("lastname","Ronaldo");
        cr.append("age","15");

        Document messi = new Document("id",2);
        messi.append("firstname","Lionel");
        messi.append("lastname","Messi");
        messi.append("age","14");

        Document tp = new Document("id",3);
        messi.append("firstname","");
        messi.append("lastname","");
        messi.append("age","");

        collection.insertOne(cr);
        collection.insertOne(messi);
        collection.insertOne(tp);

    }

    @Override
    public Student readCollection(MongoCollection collection, int id) {
        Document doc = (Document) collection.find(eq("id", id)).first();
        Student student = new Student();
        //doc.toJson();
        student.setAge((Integer) doc.get("age"));
        student.setFirstname((String) doc.get("firstname"));
        student.setLastname((String) doc.get("lastname"));
        student.setId((Integer) doc.get("id"));
        return student;
    }

    @Override
    public void updateCollection(MongoCollection collection, int id) {
        Bson update1 =   set("firstname","Neymar");
        Bson update2 =   set("lastname","Junior");
        Bson update3 =   set("age",12);
        Bson update = combine(update1,update2,update3);
        Bson filter = eq("id",id);

        Document updateResult = (Document) collection.findOneAndUpdate(filter, update);
        Object data = collection.find(filter).first();
        System.out.println(data);
        System.out.println(updateResult);
    }

    @Override
    public long deleteCollection(MongoCollection collection, int id) {
        collection.deleteOne(Filters.eq("id", id));
        return collection.count();
    }

    @Override
    public void insetNew(MongoCollection<Document> collection, Student student) {
        Document newdoc = new Document("id",student.getId());
        newdoc.append("firstname",student.getFirstname());
        newdoc.append("lastname",student.getLastname());
        newdoc.append("age",student.getAge());
    }
}
