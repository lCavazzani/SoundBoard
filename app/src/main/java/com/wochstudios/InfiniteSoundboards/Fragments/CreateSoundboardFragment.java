package com.wochstudios.InfiniteSoundboards.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wochstudios.InfiniteSoundboards.controller.DatabaseController;
import com.wochstudios.InfiniteSoundboards.listeners.IDialogListener;
import com.wochstudios.InfiniteSoundboards.R;

public class CreateSoundboardFragment extends DialogFragment
{
	private View layout;
	private DatabaseController DC;
	private IDialogListener mListener;

    public CreateSoundboardFragment(){}
    
    public void setArguments(DatabaseController dc){
        this.DC = dc;
    }
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		try{
			mListener = (IDialogListener) activity;
		}catch (ClassCastException e){
			throw new ClassCastException(activity.toString()+" must implement Listener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new  AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		layout = inflater.inflate(R.layout.create_soundboard_layout, null);
		builder.setView(layout)
			.setPositiveButton("Create Soundboard", new Dialog.OnClickListener(){
				public void onClick(DialogInterface dialog, int id){
					EditText text = (EditText)layout.findViewById(R.id.SoundBoardTitle);
                    if(text.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(),"Cannot create InfiniteSoundboards without name!",Toast.LENGTH_SHORT).show();
                    }else {
                        DC.addSoundboardToDatabase(text.getText().toString());
                        mListener.onDialogPositiveClick(CreateSoundboardFragment.this);
                    }
				}
			})
			.setTitle("Create Soundboard");
		return builder.create();
	}

		
	
}
