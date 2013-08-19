package net.onpu_tamago.android.systemdrawableviewerplus;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

public class PreviewFragment extends Fragment {

	private static final String TAG = "[Preview]SystemDrawableViewerPlus";
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		mView = inflater.inflate(R.layout.fragment_preview, container, false);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle bundle = getArguments();
		if (bundle != null) {
			int iconid = bundle.getInt(MainFragment.EXTRA_ICONID, 0);
			Drawable icon = getResources().getDrawable(iconid);
			Log.d(TAG, bundle.getString(MainFragment.EXTRA_ICONNAME));
			Log.d(TAG, "MinWidth:" + icon.getMinimumWidth());
			Log.d(TAG, "MinHeight:" + icon.getMinimumHeight());
			Log.d(TAG, "IntrinsicWidth:" + icon.getIntrinsicWidth());
			Log.d(TAG, "IntrinsicHeight:" + icon.getIntrinsicHeight());
			AQuery $ = new AQuery(mView);
			$.id(R.id.out_iconsize).text(R.string.wording_size,
					icon.getMinimumWidth(), icon.getMinimumHeight());
			$.id(R.id.out_preview1).background(iconid);
			$.id(R.id.out_preview2).background(iconid);
			$.id(R.id.out_preview3).background(iconid);
			$.id(R.id.out_preview4).image(iconid);
			$.id(R.id.out_preview6).image(iconid);
			$.id(R.id.out_preview5).image(iconid);
		}
	}
}
