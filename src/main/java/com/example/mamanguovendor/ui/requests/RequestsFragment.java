package com.example.mamanguovendor.ui.requests;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mamanguovendor.R;
import com.google.android.material.button.MaterialButton;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestsFragment extends Fragment {

    private RequestsFragmentViewModel viewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestsFragment newInstance(String param1, String param2) {
        RequestsFragment fragment = new RequestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView firstName = container.findViewById(R.id.textView_request_name);

        return inflater.inflate(R.layout.fragment_requests, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        viewModel = ViewModelProviders.of(this)
                .get(RequestsFragmentViewModel.class);

        viewModel.getRequest().observe(this, requests -> {
            if (requests != null) {
                String firstName = requests.getFirstName();
                String lastName = requests.getLastName();
                String phoneNumber = requests.getPhoneNumber();
                String location = requests.getLocation();
                String requestList = requests.getDescription();
                Integer itotalCost = requests.getTotalCost();
                String totalCost = itotalCost.toString();

                TextView title = getActivity().findViewById(R.id.textView_request_title);
                TextView name = getActivity().findViewById(R.id.textView_request_name);
                TextView mobileNo = getActivity().findViewById(R.id.textView_request_phoneNumber);
                TextView locationName = getActivity().findViewById(R.id.textView_request_location);
                TextView request = getActivity().findViewById(R.id.textView_request_description);
                TextView totalcost = getActivity().findViewById(R.id.textView_request_totalCost);

                title.setText(firstName + " " + title.getText().toString());
                name.setText(name.getText().toString() + " " + firstName + " " + lastName);
                mobileNo.setText(mobileNo.getText() + " " + phoneNumber);
                locationName.setText(locationName.getText().toString() + " " + location);
                request.setText(requestList);
                totalcost.setText(totalcost.getText().toString() + " " + totalCost);

                getActivity().findViewById(R.id.layout_norequest).setVisibility(GONE);
                getActivity().findViewById(R.id.layout_request).setVisibility(View.VISIBLE);


                Toast.makeText(getContext(), requests.getFirstName(), Toast.LENGTH_LONG)
                        .show();
            } else {
                getActivity().findViewById(R.id.layout_request).setVisibility(GONE);
                getActivity().findViewById(R.id.layout_norequest).setVisibility(View.VISIBLE);
            }


        });

        MaterialButton cancel = getActivity().findViewById(R.id.material_text_button_cancel);

        cancel.setOnClickListener(v -> {
            String status = "Cancelled";
//            Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
            cancelRequest(status);
        });
    }

    private void cancelRequest(String status) {

        viewModel.cancelRequest(status).observe(this, requests -> {
            if (requests != null) {
                Toast.makeText(getActivity(), "Cancel Successful", Toast.LENGTH_SHORT).show();
//                getActivity().findViewById(R.id.layout_request).setVisibility(GONE);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
