package com.example.h.sidraeffendi;

/**
 * Created by h on 6/24/2015.
 */
import android.provider.BaseColumns;

public class TableData {
    public static final String DB_NAME = "com.example.h.sidraeffendi.tasks";
    public static final int DB_VERSION = 2;
    public static final String TABLE1 = "tasks";

    public class Columns {
        public static final String COURSE = "Course";
        public static final String PROFESSOR = "Professor";
        public static final String VENUE = "Venue";
        public static final String CODE = "Code";
        public static final String _ID = BaseColumns._ID;
    }
}
