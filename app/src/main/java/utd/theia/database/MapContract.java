package utd.theia.database;

import android.provider.BaseColumns;

/**
 * Created by Sagar on 11/3/2015.
 */
public final class MapContract {
    public MapContract() {
    }

    public static abstract class MapEntry implements BaseColumns {
        public static final String TABLE_NAME = "Map";
        
    }
}
