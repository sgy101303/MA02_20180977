package ddwu.mobile.finalproject.ma02_20180977;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyBookmarkAdapter extends CursorAdapter {

    LayoutInflater inflater;
    int layout;

    public MyBookmarkAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View view = inflater.inflate(layout, parent, false); //부모에 연결할 필요가 없어서 false라고 함

        View view = inflater.inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder();
        view.setTag(holder);
        return view;
//        return inflater.inflate(layout, parent, false); //한번에 써도 됨
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if(holder.dutyName == null) {
            holder.dutyName = view.findViewById(R.id.hName);
            holder.dutyAddr = view.findViewById(R.id.tvAddr);
        }

        holder.dutyName.setText(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYNAME)));
        holder.dutyAddr.setText(cursor.getString(cursor.getColumnIndex(HospitalBookmarkDBHelper.COL_DUTYADDR)));

//        TextView tvContactName = view.findViewById(R.id.tvContactName);
//        TextView tvContactPhone = view.findViewById(R.id.tvContactPhone);

//        tvContactName.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_NAME)));
//        tvContactPhone.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_PHONE)));
    }


    static class ViewHolder {
        public ViewHolder() { //명시적으로
            dutyName = null;
            dutyAddr = null;
        }
        TextView dutyName;
        TextView dutyAddr;
    }
}
