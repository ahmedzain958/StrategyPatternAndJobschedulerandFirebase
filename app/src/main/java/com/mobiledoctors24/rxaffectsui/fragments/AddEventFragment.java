package com.mobiledoctors24.rxaffectsui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.mobiledoctors24.rxaffectsui.MainActivity;
import com.mobiledoctors24.rxaffectsui.R;
import com.mobiledoctors24.rxaffectsui.models.Event;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment {

    private boolean isEdit;

    private String docId;
    private FirebaseFirestore firebaseStoreDb;

    public AddEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        Button button = (Button) view.findViewById(R.id.add_event);
        Event event = null;
        if (getArguments() != null) {
            event = getArguments().getParcelable("event");
            ((TextView) view.findViewById(R.id.add_tv)).setText("Edit Event");
        }
        if (event != null) {
            ((TextView) view.findViewById(R.id.event_name_a)).setText(event.getName());
            ((TextView) view.findViewById(R.id.event_type_a)).setText(event.getType());
            ((TextView) view.findViewById(R.id.event_place_a)).setText(event.getPlace());
            ((TextView) view.findViewById(R.id.event_start_time_a)).setText(event.getStartTime());
            ((TextView) view.findViewById(R.id.event_end_time_a)).setText(event.getEndTime());

            button.setText("Edit Event");
            isEdit = true;
            docId = event.getId();
        }
        firebaseStoreDb = FirebaseFirestore.getInstance();
        button.setOnClickListener(v -> {
            if (isEdit) {
                editEvent(view);
            } else {
                addEvent(view);
            }
        });


        return view;
    }

    private void addEvent(View view) {
        Event event = createEventObj(view);
        addDocumentToCollection(event, view);
    }

    private void addDocumentToCollection(Event event, View view) {
        firebaseStoreDb.collection("events")
                .add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(),
                                "Event document has been added",
                                Toast.LENGTH_SHORT).show();
                        restUi(view);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),
                                "Event document could not be added",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Event createEventObj(View view) {
        Event event = new Event();
        event.setName(((TextView) view.findViewById(R.id.event_name_a)).getText().toString());
        event.setType(((TextView) view.findViewById(R.id.event_type_a)).getText().toString());
        event.setPlace(((TextView) view.findViewById(R.id.event_place_a)).getText().toString());
        event.setStartTime(((TextView) view.findViewById(R.id.event_start_time_a)).getText().toString());
        event.setEndTime(((TextView) view.findViewById(R.id.event_end_time_a)).getText().toString());
        return event;
    }

    private void editEvent(View view) {
        Event event = createEventObj(view);
        updateDocumentToCollection(event);
    }

    private void updateDocumentToCollection(Event event) {
        firebaseStoreDb.collection("events")
                .document(docId)
                .set(event, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Event document updated ");
                        Toast.makeText(getActivity(),
                                "Event document has been updated",
                                Toast.LENGTH_SHORT).show();
                        showEventScreen();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding event document", e);
                        Toast.makeText(getActivity(),
                                "Event document could not be added",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showEventScreen() {
        Intent i = new Intent();
        i.setClass(getActivity(), MainActivity.class);
        startActivity(i);
    }
    private void restUi(View view) {
        ((TextView) view
                .findViewById(R.id.event_name_a)).setText("");
        ((TextView) view
                .findViewById(R.id.event_type_a)).setText("");
        ((TextView) view
                .findViewById(R.id.event_place_a)).setText("");
        ((TextView) view
                .findViewById(R.id.event_start_time_a)).setText("");
        ((TextView) view
                .findViewById(R.id.event_end_time_a)).setText("");
    }
}
