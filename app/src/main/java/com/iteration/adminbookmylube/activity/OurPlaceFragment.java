package com.iteration.adminbookmylube.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iteration.adminbookmylube.R;
import com.iteration.adminbookmylube.adapter.BookingListAdapter;
import com.iteration.adminbookmylube.model.Booking;
import com.iteration.adminbookmylube.model.BookingList;
import com.iteration.adminbookmylube.network.GetProductDataService;
import com.iteration.adminbookmylube.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurPlaceFragment extends Fragment {

    RecyclerView rvOurPlace;
    ArrayList<Booking> BookingListArray = new ArrayList<>();

    public OurPlaceFragment() {
        // Required empty public constructor
    }

    public static OurPlaceFragment newInstance(String param1, String param2) {
        OurPlaceFragment fragment = new OurPlaceFragment();
        Bundle args = new Bundle();
        args.putString("ARG_PARAM1", param1);
        args.putString("ARG_PARAM2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_our_place, container, false);

        GetProductDataService productDataService = RetrofitInstance.getRetrofitInstance().create(GetProductDataService.class);

        final TextView txtData = (TextView)view.findViewById(R.id.txtData);

        rvOurPlace = (RecyclerView)view.findViewById(R.id.rvOurPlace);
        rvOurPlace.setHasFixedSize(true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvOurPlace.setLayoutManager(manager);

        String date = AdminBookingActivity.tvBDate;
        Log.d("date",date);

        Call<BookingList> BookingListCall = productDataService.getAdminBookingData(date,"Our Place","Pending");
        BookingListCall.enqueue(new Callback<BookingList>() {
            @Override
            public void onResponse(Call<BookingList> call, Response<BookingList> response) {
                String status = response.body().getStatus();
                String message = response.body().getMessage();
                if (status.equals("1"))
                {
                    Log.d("message",""+message);
                    BookingListArray = response.body().getBookingList();
                    BookingListAdapter bookingListAdapter = new BookingListAdapter(getActivity(), BookingListArray);
                    rvOurPlace.setAdapter(bookingListAdapter);
                    rvOurPlace.setVisibility(View.VISIBLE);
                    txtData.setVisibility(View.GONE);
                }
                else
                {
                    Log.d("message",""+message);
                    rvOurPlace.setVisibility(View.GONE);
                    txtData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<BookingList> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
