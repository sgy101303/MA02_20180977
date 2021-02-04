package ddwu.mobile.finalproject.ma02_20180977;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyReviewAdapter extends CursorAdapter {
    final String TAG ="MyReviewAdapter";
    LayoutInflater inflater;
    int layout;


    ContentResolver contentResolver;
    Cursor mCursor;

    public MyReviewAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View view = inflater.inflate(layout, parent, false); //부모에 연결할 필요가 없어서 false라고 함

        View view = inflater.inflate(layout, parent, false);
        MyReviewAdapter.ViewHolder holder = new MyReviewAdapter.ViewHolder();
        view.setTag(holder);
        return view;
//        return inflater.inflate(layout, parent, false); //한번에 써도 됨
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
//        MyReviewAdapter.ViewHolder holder = (MyReviewAdapter.ViewHolder) view.getTag();
        if(holder.date == null) {
            holder.date = view.findViewById(R.id.tvDate);
            holder.review = view.findViewById(R.id.tvReview);
            holder.imgReview = view.findViewById(R.id.imgReview);
            holder.tvReviewHname = view.findViewById(R.id.tvReviewHname);
        }


//        Bitmap bitmap;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        bitmap = BitmapFactory.decodeFile(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_IMG)), options);
//
//        int photoW = view.findViewById(R.id.imgReview).getWidth();
//        int photoH = view.findViewById(R.id.imgReview).getWidth();
//        // Determine how much to scale down the image
//        Log.d(TAG, "아 시발 정말 이미지 뷰 받아오기 " + "photoW    " + photoW + "     "  + photoH);
//        Log.d(TAG, "아 시발 정말 bitmap 뷰 받아오기 " + "bitmap    " + options.outWidth + "     "  + options.outHeight);
//        int scaleFactor = Math.min(options.outWidth/photoW, options.outWidth/photoH);
//
//
//        // Decode the image file into a Bitmap sized to fill the View
//        options.inJustDecodeBounds = false;
//        options.inSampleSize = scaleFactor;
//
//        bitmap = BitmapFactory.decodeFile(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_IMG)), options);
//        holder.imgReview.setImageBitmap(bitmap);
        if(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_IMG))==null)
            holder.imgReview.setImageResource(R.mipmap.review);
        else
            holder.imgReview.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_IMG))));
        holder.date.setText(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DATE)));
        holder.review.setText(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_REVIEW)));
        holder.tvReviewHname.setText(cursor.getString(cursor.getColumnIndex(HospitalReviewDBHelper.COL_DUTYNAME)));

//        TextView tvContactName = view.findViewById(R.id.tvContactName);
//        TextView tvContactPhone = view.findViewById(R.id.tvContactPhone);

//        tvContactName.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_NAME)));
//        tvContactPhone.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_PHONE)));
    }


    static class ViewHolder {
        public ViewHolder() { //명시적으로
            date = null;
            review = null;
            imgReview = null;
            tvReviewHname = null;
        }
        TextView date;
        TextView review;
        TextView tvReviewHname;
        ImageView imgReview;
    }
}
