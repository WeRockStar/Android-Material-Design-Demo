package werock.com.material.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import werock.com.material.MainActivity;
import werock.com.material.R;
import werock.com.material.adapters.CarAdapter;
import werock.com.material.domain.Car;


public class LuxuryCarFragment extends CarFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList("mList");
        } else {
            list = ((MainActivity) getActivity()).getCarsByCategory(1);
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

                GridLayoutManager llm = (GridLayoutManager) recyclerView.getLayoutManager();
                CarAdapter adapter = (CarAdapter) recyclerView.getAdapter();

                if (list.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listAux = ((MainActivity) getActivity()).getSetCarList(10, 1);
                    ((MainActivity) getActivity()).getListCars().addAll(listAux);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), list.size());
                    }
                }
            }
        });

        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        CarAdapter adapter = new CarAdapter(getActivity(), list);
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
