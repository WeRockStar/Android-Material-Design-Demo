package werock.com.material.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import werock.com.material.R;
import werock.com.material.domain.Car;
import werock.com.material.interfaces.RecyclerViewOnClickListenerHack;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private Context context;
    private List<Car> carList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Scale for pre lollipop
        this.scale = this.context.getResources().getDisplayMetrics().density;
        this.width = this.context.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("LOG", "onBindViewHolder");
        holder.imageCar.setImageResource(carList.get(position).getImage());
        holder.tvModel.setText(carList.get(position).getModels());
        holder.tvBrand.setText(carList.get(position).getBrand());

        try {
            //TODO Animation view
            YoYo.with(Techniques.SlideInDown)
                    .duration(1000)
                    .playOn(holder.itemView);

        } catch (Exception e) {

        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("LOG", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.item_car_card, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack hack) {
        recyclerViewOnClickListenerHack = hack;
    }

    public void addListItem(Car car, int position) {
        carList.add(car);
        notifyItemInserted(position);

    }

    public void removeListItem(int position) {
        carList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageCar;
        public TextView tvModel;
        public TextView tvBrand;

        public MyViewHolder(View view) {
            super(view);

            imageCar = (ImageView) view.findViewById(R.id.iv_car);
            tvModel = (TextView) view.findViewById(R.id.tv_model);
            tvBrand = (TextView) view.findViewById(R.id.tv_brand);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnClickListenerHack != null) {
                recyclerViewOnClickListenerHack.OnClickListener(v, getPosition());
            }
        }
    }
}
