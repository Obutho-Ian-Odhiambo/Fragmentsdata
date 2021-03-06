package com.flight.fragmentsdata;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link first_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link first_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class first_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText studentid, username, password, email, department, session;
    private RadioButton gender;
    private Button submitbtn;
    private RadioGroup radioGroup;

    private Spinner spinner, spinner2;
    private static final String[] paths = {"Computer Science", "Global challenges", "IBT"};
    private static final String[] sess = {"Software Dev", "Machine Learning", "Computer Networks"};


    private OnFragmentInteractionListener mListener;

    public first_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment first_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static first_Fragment newInstance(String param1, String param2) {
        first_Fragment fragment = new first_Fragment();
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
        final View view = inflater.inflate(R.layout.fragment_first_, container, false);

        spinner = (Spinner)view.findViewById(R.id.departmentspinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner)view.findViewById(R.id.sessionspinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item,sess);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        studentid = (EditText) view.findViewById(R.id.idcard);
        email = (EditText) view.findViewById(R.id.email);
        radioGroup = (RadioGroup) view.findViewById(R.id.genderradiogroup);
        submitbtn = (Button) view.findViewById(R.id.button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.radioMale:
                        gender = view.findViewById(R.id.radioMale);

                    case R.id.radioFemale:
                        gender = view.findViewById(R.id.radioFemale);
                }
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernm = username.getText().toString();
                String passwrd = password.getText().toString();
                String studentcardid = studentid.getText().toString();
                String emailtxt = email.getText().toString();
                String stgender = gender.getText().toString();
                String stdepartment = spinner.getSelectedItem().toString();
                String stsession = spinner2.getSelectedItem().toString();

                Bundle bundle = new Bundle();
                bundle.putString("UserName", usernm);
                bundle.putString("Password", passwrd);
                bundle.putString("StudentId", studentcardid);
                bundle.putString("Studentemail", emailtxt);
                bundle.putString("StudentGender", stgender);
                bundle.putString("StudentDepart", stdepartment);
                bundle.putString("StudentSession", stsession);

                FragmentManager fragmentManager =  getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                second_fragment second_fragment = new second_fragment();
                second_fragment.setArguments(bundle);


                if (TextUtils.isEmpty(usernm) || TextUtils.isEmpty(passwrd) || TextUtils.isEmpty(studentcardid) || TextUtils.isEmpty(emailtxt)){
                    username.setError(" Field cannot be empty");
                    password.setError(" Field cannot be empty");
                    studentid.setError(" Field cannot be empty");
                    email.setError(" Field cannot be empty");
                } else {

                    fragmentTransaction.replace(R.id.frame_container, second_fragment);
                    fragmentTransaction.commit();
                }

            }
        });

        return view;
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
