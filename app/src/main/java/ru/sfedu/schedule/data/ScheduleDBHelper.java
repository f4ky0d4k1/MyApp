package ru.sfedu.schedule.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScheduleDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ScheduleDBHelper.class.getSimpleName();

    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "schedule.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Конструктор {@link ScheduleDBHelper}.
     *
     * @param context Контекст приложения
     */
    public ScheduleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Вызывается при создании базы данных
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Строка для создания таблицы
        String SQL_CREATE_FACULTIES_TABLE = "CREATE TABLE " + ScheduleContract.Faculties.TABLE_NAME + " ("
                + ScheduleContract.Faculties._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ScheduleContract.Faculties.COLUMN_FACULTY_ID + " INTEGER NOT NULL, "
                + ScheduleContract.Faculties.COLUMN_FACULTY_NAME + " TEXT NOT NULL, "
                + ScheduleContract.Faculties.COLUMN_FACULTY_UPDATE + " TEXT NOT NULL );";


        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_FACULTIES_TABLE);

        // Строка для создания таблицы
        String SQL_CREATE_GROUPS_TABLE = "CREATE TABLE " + ScheduleContract.Groups.TABLE_NAME + " ("
                + ScheduleContract.Groups._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ScheduleContract.Groups.COLUMN_GROUP_ID + " INTEGER NOT NULL, "
                + ScheduleContract.Groups.COLUMN_SUBGROUP_NUMBER + " INTEGER NOT NULL, "
                + ScheduleContract.Groups.COLUMN_GROUP_NUMBER + " TEXT NOT NULL, "
                + ScheduleContract.Groups.COLUMN_COURSE_NUMBER + " INTEGER NOT NULL, "
                + ScheduleContract.Groups.COLUMN_CONTRACT_ID + " INTEGER NOT NULL, "
                + ScheduleContract.Groups.COLUMN_FACULTY_ID + " INTEGER NOT NULL, "
                + ScheduleContract.Groups.COLUMN_UPDATE + " TEXT NOT NULL );";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_GROUPS_TABLE);

        // Строка для создания таблицы
        String SQL_CREATE_SCHEDULE_TABLE = "CREATE TABLE " + ScheduleContract.Schedule.TABLE_NAME + " ("
                + ScheduleContract.Schedule._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ScheduleContract.Schedule.COLUMN_DATE + " DATE NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_WEEK + " INTEGER NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_DAY + " TEXT NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_CLASS_NUMBER + " INTEGER NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_GROUP_NUMBER + " TEXT NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_OBJECT_NAME + " TEXT NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_PROFESSOR_NAME + " TEXT NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_PLACE + " TEXT NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_MESSAGES + " TEXT NOT NULL, "
                + ScheduleContract.Schedule.COLUMN_UPDATE + " TEXT NOT NULL );";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_SCHEDULE_TABLE);
    }

    /**
     * Вызывается при обновлении схемы базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
