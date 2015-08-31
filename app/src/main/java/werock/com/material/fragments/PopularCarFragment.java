package werock.com.material.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import werock.com.material.MainActivity;
import werock.com.material.R;
import werock.com.material.adapters.CarAdapter;
import werock.com.material.domain.Car;


public class PopularCarFragment extends CarFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            list = savedInstanceState.getParcelableArrayList("mList");
        }
        else{
            list = ((MainActivity) getActivity()).getCarsByCategory(4);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
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

                if (dy > 0) {
                    fab.hideMenuButton(true);
                } else {
                    fab.showMenuButton(true);
                }

                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                CarAdapter adapter = (CarAdapter) recyclerView.getAdapter();

                if (list.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10, 4);
                    ((MainActivity) getActivity()).getListCars().addAll(listAux);
                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), listAux.size());
                    }
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        CarAdapter adapter = new CarAdapter(getActivity(), list, true, false);
        adapter.setRecyclerViewOnClickListenerHack(this);
        recyclerView.setAdapter(adapter);

        setFloatingActionButton();

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
