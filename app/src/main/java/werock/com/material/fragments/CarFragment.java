package werock.com.material.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import werock.com.material.MainActivity;
import werock.com.material.R;
import werock.com.material.adapters.CarAdapter;
import werock.com.material.domain.Car;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class CarFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Car> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                CarAdapter carAdapter = (CarAdapter) recyclerView.getAdapter();

                if (list.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listCar = ((MainActivity) getActivity()).getSetCarList(10);
                    for (int i = 0; i < listCar.size(); i++) {
                        carAdapter.addListItem(listCar.get(i), list.size());
                    }
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = ((MainActivity) getActivity()).getSetCarList(10);
        CarAdapter carAdapter = new CarAdapter(getActivity(), list);
        recyclerView.setAdapter(carAdapter);

        return view;
    }
}
