package werock.com.material.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.software.shell.fab.ActionButton;

import java.util.List;

import werock.com.material.CarActivity;
import werock.com.material.MainActivity;
import werock.com.material.R;
import werock.com.material.adapters.CarAdapter;
import werock.com.material.domain.Car;
import werock.com.material.interfaces.RecyclerViewOnClickListenerHack;

/**
 * Created by Kotchaphan Muangsan on 28/8/2558.
 */
public class CarFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener {

    protected RecyclerView recyclerView;
    protected List<Car> list;
    //private FloatingActionButton fab;
    // private ActionButton fab;
    protected FloatingActionMenu fab;

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

//                if(dy > 0){
//                    fab.hide();
//                }else{
//                    fab.show();
//                }

                if (dy > 0) {
                    fab.hideMenuButton(true);
                } else {
                    fab.showMenuButton(true);
                }

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                //GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

//                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
//                int[] aux = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
//                int max = 1;
//                for (int i = 0; i < aux.length; i++) {
//                    max = aux[i] > max ? aux[i] : max;
//                }

                CarAdapter carAdapter = (CarAdapter) recyclerView.getAdapter();
                //When Last Intem
                if (list.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    //if (list.size() == max) {
                    List<Car> listCar = ((MainActivity) getActivity()).getSetCarList(10);
                    for (int i = 0; i < listCar.size(); i++) {
                        carAdapter.addListItem(listCar.get(i), list.size());
                    }
                }
            }
        });


        //TODO onLongClick
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), recyclerView, this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        /*
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
         */

        /*
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        */
        //for 30 item
        //list = ((MainActivity) getActivity()).getSetCarList(30);
        CarAdapter carAdapter = new CarAdapter(getActivity(), list);
        //TODO onClick
        //carAdapter.setRecyclerViewOnClickListenerHack(this);
        recyclerView.setAdapter(carAdapter);
        //call method
        setFloatingActionButton();
        return view;
    }

    public void setFloatingActionButton() {

        //TODO Floating Action Button
        fab = (FloatingActionMenu) getActivity().findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean b) {
                //Toast.makeText(getActivity(), "Is Menu : " + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
            }
        });
        fab.showMenuButton(true);
        fab.setClosedOnTouchOutside(true);

        FloatingActionButton fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        FloatingActionButton fab3 = (FloatingActionButton) getActivity().findViewById(R.id.fab3);
        FloatingActionButton fab4 = (FloatingActionButton) getActivity().findViewById(R.id.fab4);
        FloatingActionButton fab5 = (FloatingActionButton) getActivity().findViewById(R.id.fab5);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);

        /*fab = (ActionButton) getActivity().findViewById(R.id.fab);
        fab.setButtonColor(getActivity().getResources().getColor(R.color.colorFAB));
        fab.setButtonColorPressed(getActivity().getResources().getColor(R.color.colorFABPressed));

        fab.setShowAnimation(ActionButton.Animations.ROLL_FROM_DOWN);
        fab.setHideAnimation(ActionButton.Animations.ROLL_TO_DOWN);

        fab.setImageResource(R.drawable.plus);

        float scale = getActivity().getResources().getDisplayMetrics().density;
        int shadow = (int) (3 * scale + 0.5);
        fab.setShadowRadius(shadow);

        fab.setOnClickListener(this);
        fab.playShowAnimation();
        */

        /*        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab); */
        /*
        fab.attachToRecyclerView(recyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrollUp() {

            }
        } */ /*, new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView _recyclerView, int dx, int dy) {
                super.onScrolled(_recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                //GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                /*
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                int[] aux = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                int max = 1;
                for (int i = 0; i < aux.length; i++) {
                    max = aux[i] > max ? aux[i] : max;
                }
                */
        /*
                CarAdapter carAdapter = (CarAdapter) recyclerView.getAdapter();
                //When Last Intem
                if (list.size() == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    //if (list.size() == max) {
                    List<Car> listCar = ((MainActivity) getActivity()).getSetCarList(10);
                    for (int i = 0; i < listCar.size(); i++) {
                        carAdapter.addListItem(listCar.get(i), list.size());
                    }
                }
            }
        });

        /*
        fab.setOnClickListener(this);
        fab.hide();
        fab.show();
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        */

    }

    @Override
    public void OnClickListener(View view, int position) {
        Intent intent = new Intent(getActivity(), CarActivity.class);
        intent.putExtra("car", list.get(position));
        getActivity().startActivity(intent);
//        Toast.makeText(getActivity(), "onClick Postion : " + position, Toast.LENGTH_SHORT).show();
//
//        CarAdapter adapter = (CarAdapter) recyclerView.getAdapter();
//        adapter.removeListItem(position);
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

    @Override
    public void onClick(View v) {
        String str = "";
        switch (v.getId()) {
            case R.id.fab1:
                str = "Fab 1";
                break;
            case R.id.fab2:
                str = "Fab 2";
                break;
            case R.id.fab3:
                str = "Fab 3";
                break;
            case R.id.fab4:
                str = "Fab 4";
                break;
            case R.id.fab5:
                str = "Fab 5";
                break;
        }
        Toast.makeText(getActivity(), "FAB Press : " + str, Toast.LENGTH_SHORT).show();
    }
}
