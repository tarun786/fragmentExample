package com.example.sqlitepractice;

public class ContactContract
{
    private ContactContract()
    {

    }

    /**
     * database schema
     */
    public static class ContactEntry
    {
        public static final String TABLE_NAME = "contact_info";
        public static final String CONTACT_ID = "contact_id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
    }
}
