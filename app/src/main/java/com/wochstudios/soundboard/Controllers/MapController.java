package com.wochstudios.soundboard.Controllers;

import android.net.Uri;
import android.content.Context;
import android.content.ContentResolver;


import com.wochstudios.soundboard.utils.*;

import java.util.Set;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;

public class MapController
{
	private Context con;
	private MapUpdater mu;
	private MapCreator mc;
	private MapLoader ml;
	
	public MapController(Context c){
		this.con = c;
		mu = new MapUpdater(con);
		mc = new MapCreator();
		ml = new MapLoader();
	}
	
	public void createMap(){
		mc.createMapFile(con);
	}
	
	public void AddSoundToMap(String title, String Uri){
		mu.updateSoundMap(MapUpdater.ADD_CODE, title, Uri);
	}
	
	public void RemoveSoundFromMap(String title){
		//mu.updateSoundMap(MapUpdater.REMOVE_CODE, title, Uri);
		mu.removeSoundFromMap(title, this.getSoundMap());
	}
	
	public HashMap<String,String> getSoundMap(){
		try {
			InputStream f = con.openFileInput("filemap.txt");
			return ml.loadVaules(f);
		} catch (IOException e) {
			e.printStackTrace();
			return new HashMap<String,String>();
		}
	}
	
}