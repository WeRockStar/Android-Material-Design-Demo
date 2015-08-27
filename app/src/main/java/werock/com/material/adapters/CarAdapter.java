package werock.com.material.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import werock.com.material.R;
import werock.com.material.domain.Car;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private List<Car> carList;
    private LayoutInflater layoutInflater;


    public CarAdapter(Context context, List<Car> carList) {
        this.carList = carList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("LOG", "onBindViewHolder");
        holder.imageCar.setImageResource(carList.get(position).getImage());
        holder.tvModel.setText(carList.get(position).getModels());
        holder.tvBrand.setText(carList.get(position).getBrand());
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("LOG", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.item_car, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public void addListItem(Car car, int position) {
        carList.add(car);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageCar;
        public TextView tvModel;
        public TextView tvBrand;

        public MyViewHolder(View view) {
            super(view);

            imageCar = (ImageView) view.findViewById(R.id.iv_car);
            tvModel = (TextView) view.findViewById(R.id.tv_model);
            tvBrand = (TextView) view.findViewById(R.id.tv_brand);
        }
    }
}
