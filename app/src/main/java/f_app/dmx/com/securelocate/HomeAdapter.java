package f_app.dmx.com.securelocate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeAdapter extends BaseAdapter {

    int[] imageId={R.drawable.alarm,R.drawable.safe,R.drawable.id,R.drawable.setting};
    String[] names={"安全定位","安全讲座","登录/注册","基本设置"};

    private Context context;


    public HomeAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount(){
        return 4;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View view=View.inflate(context,R.layout.item_home,null);
        ImageView iv_icon=(ImageView)view.findViewById(R.id.iv_icon);
        TextView tv_name=(TextView)view.findViewById(R.id.tv_name);

        iv_icon.setImageResource(imageId[position]);
        tv_name.setText(names[position]);
        return  view;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
