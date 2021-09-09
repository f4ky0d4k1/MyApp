package ru.sfedu.schedule.data;

import android.provider.BaseColumns;

public final class ScheduleContract {

    private ScheduleContract() {
    };

    public static final class Faculties implements BaseColumns {
        public final static String TABLE_NAME = "faculties";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_FACULTY_ID = "faculty_id";
        public final static String COLUMN_FACULTY_NAME = "faculty_name";
        public final static String COLUMN_FACULTY_ACCESS = "faculty_access";
        public final static String COLUMN_FACULTY_UPDATE = "date";

    }

    public static final class Groups implements BaseColumns {
        public final static String TABLE_NAME = "groups";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_GROUP_ID = "group_id";
        public final static String COLUMN_SUBGROUP_NUMBER = "subgroup_number";
        public final static String COLUMN_GROUP_NUMBER = "group_number";
        public final static String COLUMN_COURSE_NUMBER = "course_number";
        public final static String COLUMN_CONTRACT_ID = "contract_id";
        public final static String COLUMN_FACULTY_ID = "faculty_id";
        public final static String COLUMN_GROUP_ACCESS = "group_access";
        public final static String COLUMN_UPDATE = "date";

    }

    public static final class Schedule implements BaseColumns {
        public final static String TABLE_NAME = "schedule";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_WEEK = "week";
        public final static String COLUMN_DAY = "day";
        public final static String COLUMN_CLASS_NUMBER= "class_number";
        public final static String COLUMN_GROUP_NUMBER = "group_id";
        public final static String COLUMN_OBJECT_NAME = "object_name";
        public final static String COLUMN_PROFESSOR_NAME = "professor_name";
        public final static String COLUMN_PLACE = "place";
        public final static String COLUMN_MESSAGES = "messages";
        public final static String COLUMN_UPDATE = "date";

    }

}
