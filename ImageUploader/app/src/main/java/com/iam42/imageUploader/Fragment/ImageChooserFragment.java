package com.iam42.imageUploader.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.iam42.imageUploader.R;

/**
 * Created by a42 on 14-2-14.
 */
public class ImageChooserFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private Context mContext;
    private GridView mAlbum;

    private final int LOADER_USER_PHOTOS_EXTERNAL = 0;
    private final String LOADER_PHOTOS_BUCKETS_PARAM = "photos_buckets_param";
    public static final String[] PHOTOS_PROJECTION = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.MINI_THUMB_MAGIC, MediaStore.Images.Media.SIZE, MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
    public static final String PHOTOS_ORDER_BY = MediaStore.Images.Media.DATE_ADDED + " desc";
    public static final Uri MEDIA_STORE_CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    public ImageChooserFragment(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_image_chooser, container, false);
        mAlbum = (GridView) view.findViewById(R.id.album);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_USER_PHOTOS_EXTERNAL, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = null;

        switch (id) {
            case LOADER_USER_PHOTOS_EXTERNAL:
                String selection = null;
                String[] selectionArgs = null;

                if (null != args && args.containsKey(LOADER_PHOTOS_BUCKETS_PARAM)) {
                    selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
                    selectionArgs = new String[]{args.getString(LOADER_PHOTOS_BUCKETS_PARAM)};
                }

                cursorLoader = new CursorLoader(getActivity(),
                                                MEDIA_STORE_CONTENT_URI,
                                                PHOTOS_PROJECTION, selection, selectionArgs,
                                                PHOTOS_ORDER_BY);
                break;
        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        cursor.moveToFirst();
        Log.d("zhang", cursor.getString(0) + "");
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
