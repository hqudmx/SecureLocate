package f_app.dmx.com.securelocate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SafeAdapter  extends BaseAdapter{

   int [] tv_id={R.drawable.building,R.drawable.manager,R.drawable.law,R.drawable.check};
    String [] texts={"安全施工","安全管理","法律维权","验收标准"};
    private Context context;
    public  SafeAdapter( Context context){
        this.context=context;

    }

    @Override
    public int getCount(){
        return 4;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view=View.inflate(context,R.layout.item_safe,null);
        ImageView ima=(ImageView)view.findViewById(R.id.safe_icon);
        TextView tv_safe=(TextView)view.findViewById(R.id.safe_textView);
        ima.setImageResource(tv_id[position]);
        tv_safe.setText(texts[position]);
        return view;
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
