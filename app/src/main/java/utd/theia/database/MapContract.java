package utd.theia.database;

import android.provider.BaseColumns;

/**
 * Created by Sagar on 11/3/2015.
 */
public final class MapContract {
    public MapContract() {
    }

    public static abstract class RoomIndicatorEntry implements BaseColumns {
        public static final String TABLE_NAME = "room_indicator";
        public static final String COLUMN_NAME_FROM = "from_room";
        public static final String COLUMN_NAME_TO = "to_room";
        public static final String COLUMN_NAME_DISTANCE = "distance";
        public static final String COLUMN_NAME_DIRECTION = "direction";
        public static final String[] PRIMARY_KEY = {COLUMN_NAME_FROM, COLUMN_NAME_TO};

        private static final String TEXT_TYPE = " TEXT";
        private static final String INTEGER_TYPE = " INTEGER";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ROOM_INDICATOR =
                "CREATE TABLE " + RoomIndicatorEntry.TABLE_NAME + " (" +
                        RoomIndicatorEntry.COLUMN_NAME_FROM + TEXT_TYPE + COMMA_SEP +
                        RoomIndicatorEntry.COLUMN_NAME_TO + TEXT_TYPE + COMMA_SEP +
                        RoomIndicatorEntry.COLUMN_NAME_DISTANCE + INTEGER_TYPE + COMMA_SEP +
                        RoomIndicatorEntry.COLUMN_NAME_DIRECTION + TEXT_TYPE + COMMA_SEP +
                        "PRIMARY KEY(" + COLUMN_NAME_FROM + ", " + COLUMN_NAME_TO + ")" +
                        " )";

        public static final String SQL_DELETE_ROOM_INDICATOR =
                "DROP TABLE IF EXISTS " + RoomIndicatorEntry.TABLE_NAME;

    }


}
