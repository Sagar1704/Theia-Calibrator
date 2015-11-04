package utd.theia.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sagar on 11/4/2015.
 */
public class MapDB {
    private MapDbHelper mapDbHelper;

    public MapDB(Context context) {
        mapDbHelper = new MapDbHelper(context);
    }

    public void upsert(String table, String columns, String[] primary, ContentValues values) {
        if (!check(table, primary, values)) {
            SQLiteDatabase db = mapDbHelper.getWritableDatabase();
            db.insert(
                    table,
                    columns,
                    values);
        } else {
            update(table, primary, values);
        }
    }

    private boolean check(String table, String[] primary, ContentValues values) {
        SQLiteDatabase db = mapDbHelper.getReadableDatabase();
        Cursor c = db.query(
                table,
                primary,
                getSelection(primary),
                getSelectionArgs(new ArrayList<String>(Arrays.asList(primary)), values),
                null, null, null);

        if (c != null && c.getCount() > 0)
            return true;
        return false;
    }

    private void update(String table, String[] primary, ContentValues values) {
        SQLiteDatabase db = mapDbHelper.getReadableDatabase();

        db.update(
                table,
                values,
                getSelection(primary),
                getSelectionArgs(new ArrayList<String>(Arrays.asList(primary)), values));
    }

    private String getSelection(String[] primary) {
        String selection = "";
        for (String key : primary) {
            selection += key;
            selection += "=? AND ";
        }
        selection = selection.substring(0, selection.length() - 5);

        return selection;
    }

    private String[] getSelectionArgs(ArrayList<String> primary, ContentValues values) {
        List<String> args = new ArrayList<String>();
        for (String val : values.keySet()) {
            if (primary.contains(val)) {
                args.add("" + values.get(val));
            }
        }
        return args.toArray(new String[args.size()]);
    }
}
