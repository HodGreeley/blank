package com.couchbase.mobile;

import com.couchbase.lite.*;
import java.util.Map;
import java.util.HashMap;

public class Client {
    public static void main(String args[]) {
        DBService dbService = DBService.getInstance();
        Database db = dbService.getDatabase();

        Document document = db.createDocument();
        String documentId = document.getId();

        Map<String, Object> profile = new HashMap<>();
        profile.put("firstName", "Hod");
        profile.put("lastName", "Greeley");

        try {
            document.putProperties(profile);
        } catch (CouchbaseLiteException ex) {
            ex.printStackTrace();
        }

        document = db.getDocument(documentId);
        UnsavedRevision update = document.createRevision();

        update.getProperties().put("test", "profile");
        
        try {
            update.save();
        } catch (CouchbaseLiteException ex) {
            ex.printStackTrace();
        }

        try {
            document.delete();
        } catch (CouchbaseLiteException ex) {
            ex.printStackTrace();
        }
    }
}
