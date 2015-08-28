package werock.com.material.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.List;

import werock.com.material.MainActivity;
import werock.com.material.R;
import werock.com.material.adapters.CarAdapter;
import werock.com.material.domain.Car;
import werock.com.material.interfaces.RecyclerViewOnClickListenerHack;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class CarFragment extends Fragment implements RecyclerViewOnClickListenerHack {

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

                //When Last Intem
                if (list.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Car> listCar = ((MainActivity) getActivity()).getSetCarList(10);
                    for (int i = 0; i < listCar.size(); i++) {
                        carAdapter.addListItem(listCar.get(i), list.size());
                    }
                }
            }
        });
        //TODO onLongClick
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), recyclerView, this));

        /*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        */


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2 , GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        list = ((MainActivity) getActivity()).getSetCarList(30);
        CarAdapter carAdapter = new CarAdapter(getActivity(), list);

        //TODO onClick
        //carAdapter.setRecyclerViewOnClickListenerHack(this);
        recyclerView.setAdapter(carAdapter);

        return view;
    }

    @Override
    public void OnClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onClick Postion : " + position, Toast.LENGTH_SHORT).show();

        CarAdapter adapter = (CarAdapter) recyclerView.getAdapter();
        adapter.removeListItem(position);
    }

    @Override
    public void OnLongClickListener(View view, int position) {
        Toast.makeText(getActivity(), "onLongClick Position : " + position, Toast.LENGTH_SHORT).show();

        CarAdapter adapter = (CarAdapter) recyclerView.getAdapter();
        adapter.removeListItem(position);
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context context;
        private GestureDetector gestureDetector;
        private RecyclerViewOnClickListenerHack recyclerViewOnClickListenerHack;


        public RecyclerViewTouchListener(Context context, final RecyclerView recyclerView
                , RecyclerViewOnClickListenerHack listenerHack) {
            this.context = context;
            this.recyclerViewOnClickListenerHack = listenerHack;
            this.gestureDetector = new GestureDetector(this.context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (view != null && recyclerViewOnClickListenerHack != null) {
                        recyclerViewOnClickListenerHack.OnLongClickListener(view, recyclerView.getChildPosition(view));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View view = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if (view != null && recyclerViewOnClickListenerHack != null) {
                        recyclerViewOnClickListenerHack.OnLongClickListener(view, recyclerView.getChildPosition(view));
                    }

                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
