package net.onpu_tamago.android.systemdrawableviewerplus;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.androidquery.AQuery;

/**
 * メインアクティビティ
 * 
 * @author 知英
 * 
 */
public class MainFragment extends ListFragment {

	/**
	 * リソースを表示するためのアダプタクラス
	 * 
	 * @author 知英
	 * 
	 */
	public class ResourceListAdapter extends BaseAdapter {

		private ArrayList<NameValuePair> mList;

		private LayoutInflater mInflater;

		public ResourceListAdapter(Context context,
				ArrayList<NameValuePair> list) {
			this.mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.mList = list;
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_resources, null);
			}
			NameValuePair nv = mList.get(position);
			AQuery $ = new AQuery(convertView);
			$.id(R.id.out_iconimage).image(nv.value);
			$.id(R.id.out_iconname).text(nv.name);
			return convertView;
		}
	}

	/**
	 * 名前と値のペア
	 * 
	 * @author 知英
	 * 
	 */
	public class NameValuePair {
		public String name;
		public int value;

		public NameValuePair(String name, int value) {
			super();
			this.name = name;
			this.value = value;
		}

	}

	private static final String TAG = "[Main]SystemDrawableViewerPlus";
	public static final String EXTRA_ICONID = "id";
	public static final String EXTRA_ICONNAME = "name";
	private ResourceListAdapter mResourceListAdapter;
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.fragment_main, container, false);
		return mView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Field[] fields = android.R.drawable.class.getFields();
		ArrayList<NameValuePair> ids = new ArrayList<MainFragment.NameValuePair>();
		for (Field f : fields) {
			Log.d(TAG, f.getName());
			try {
				ids.add(new NameValuePair(f.getName(), f.getInt(null)));
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		mResourceListAdapter = new ResourceListAdapter(getActivity(), ids);
		setListAdapter(mResourceListAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		NameValuePair nv = (NameValuePair) getListAdapter().getItem(position);
		// データ作成
		Bundle bundle = new Bundle();
		bundle.putInt(EXTRA_ICONID, nv.value);
		bundle.putString(EXTRA_ICONNAME, nv.name);
		if(getActivity().findViewById(R.id.container) == null){
			// スマートフォンレイアウト
			Intent intent = new Intent(getActivity().getApplicationContext(), PreviewActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}else{
			// タブレットレイアウト
			PreviewFragment fragment = new PreviewFragment();
			fragment.setArguments(bundle);
			
			AQuery $ = new AQuery(getActivity());
			$.id(R.id.out_viewiconname).text(nv.name);

			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(R.id.container, fragment);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}
}
