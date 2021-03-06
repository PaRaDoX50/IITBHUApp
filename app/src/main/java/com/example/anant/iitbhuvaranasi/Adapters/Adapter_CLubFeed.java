package com.example.anant.iitbhuvaranasi.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.provider.CalendarContract;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anant.iitbhuvaranasi.Constants;
import com.example.anant.iitbhuvaranasi.Activities.Feedfragment_notifcation_Activity;
import com.example.anant.iitbhuvaranasi.Activities.IITBHUMapActivity;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltWorkshopSummaryPost;
import com.example.anant.iitbhuvaranasi.R;
import com.example.anant.iitbhuvaranasi.Models.SingleVerticalData;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.anant.iitbhuvaranasi.Activities.Feedfragment_notifcation_Activity.location2345;

public class Adapter_CLubFeed extends RecyclerView.Adapter<Adapter_CLubFeed.MyViewHolder1> {

    public  long starttime;

    List<BuiltWorkshopSummaryPost>  data;
    private Context context1;

    public Adapter_CLubFeed(List<BuiltWorkshopSummaryPost> data, Context context1) {
        this.data = data;
        this.context1 = context1;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_feed_layout_recyclerview, parent, false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, final int position) {


        holder.title.setText(data.get(position).getTitle());


/*
        Glide.with(context1)
                .load(data.get(position).getImage_event())
                .placeholder(R.drawable.background)
                .error(R.drawable.ic_error_outline_black_24dp)
                .fitCenter() // scale to fit entire image within ImageView
                .into(holder.image);
*/
        Glide.with(context1)
                .load(data.get(position).getClub().getLarge_image_url())
                .error(R.drawable.background)
                .thumbnail(.1f)
                .fitCenter() // scale to fit entire image within ImageView
                .into(holder.image);

//        String originalString = data.get(position).getDate();
//        String original = originalString.replace("T"," ");
//        String original1 = original.replace("Z","");
//
//        Date date2 = null;
//        try {
//            date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(original1);
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        final String newString = new SimpleDateFormat("E, dd MMM  hh:mm a").format(date2);
        String originalString = data.get(position).getDate();
        String timeString = data.get(position).getTime();
//        String original = originalString.replace("T", " ");
//        String original1 = original.replace("Z", "");
//
        Date date2 = null;
        Date time = null;
        Log.d("time", timeString+"abcd");
        Log.d("date", originalString);
        try {
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(originalString);
            time = new SimpleDateFormat("HH:mm:ss").parse(timeString);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String newString = new SimpleDateFormat("dd MMM, E").format(date2) +" "+ new SimpleDateFormat("hh mm, a").format(time);
        holder.date.setText(newString);
        // String time = new SimpleDateFormat("HH mm ss").format(date2);
        //starttime= Integer.valueOf(time);







        final Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(holder.image, "fullscreen");
        pairs[1] = new Pair<View, String>(holder.title, "feedtitle");
        pairs[2] = new Pair<View, String>(holder.date, "feed_date");
        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context1, Feedfragment_notifcation_Activity.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context1, pairs);
                Gson gson = new Gson();
                String json = gson.toJson(data.get(position));
                // ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mcontext, pair1);
                intent.putExtra("all",json);
                intent.putExtra("time",newString);
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("date", data.get(position).getDate());
                intent.putExtra("image", data.get(position).getClub().getLarge_image_url());
                context1.startActivity(intent);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context1, Feedfragment_notifcation_Activity.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context1, pairs);
                Gson gson = new Gson();
                String json = gson.toJson(data.get(position));
                // ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mcontext, pair1);
                intent.putExtra("all",json);
                intent.putExtra("time",newString);
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("date", data.get(position).getDate());
                intent.putExtra("image", data.get(position).getClub().getLarge_image_url());
                context1.startActivity(intent);
               /* Intent intent = new Intent(context1, Full_screen_imageActivity.class);
                 ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation((Activity) context1, pairs);
                intent.putExtra("image", data.get(position).getImage_event());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context1.startActivity(intent);*/
            }
        });

        //   final Pair<View, String> pair1 = Pair.create((View) holder.image, holder.image.getTransitionName());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context1, Feedfragment_notifcation_Activity.class);
                ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation((Activity) context1, pairs);
                Gson gson = new Gson();
                String json = gson.toJson(data.get(position));
                // ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mcontext, pair1);
                intent.putExtra("all",json);
                intent.putExtra("time",newString);
                intent.putExtra("title", data.get(position).getTitle());
                intent.putExtra("date", data.get(position).getDate());
                intent.putExtra("image", data.get(position).getClub().getLarge_image_url());
                context1.startActivity(intent);
            }
        });
        holder.shareEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "#IIT BHU App\n"+ data.get(position).getTitle().toString() + "\n" +" data.get(position).getDescription_event()".toString() + "\n\n" + "Date & Time : " + newString + "\nVenue : " + "data.get(position).getLocation()".toString() ;

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                context1.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        holder.setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEventToCalender(data.get(position).getTitle().toString(),"data.get(position).getDescription_event().toString()",
                        "data.get(position).getLocation().toString()",newString);
            }
        });


/*
        holder.mapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = 40.714728;
                double longitude = -73.998672;
                String label = "ABC Label";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapintent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                mcontext.startActivity(mapintent);
            }
        });*/
        holder.mapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location2345 = "data.get(position).getMap_location()";
                Intent intent = new Intent(context1, IITBHUMapActivity.class);
                context1.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title, date;
        public ImageButton shareEvent, setReminder, mapLocation;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.event_image);
            title = itemView.findViewById(R.id.event_title);
            shareEvent = itemView.findViewById(R.id.share_event_button2);
            date = itemView.findViewById(R.id.event_dates);
            setReminder = itemView.findViewById(R.id.calendar_setevent);
            mapLocation = itemView.findViewById(R.id.navigate_button);

        }
    }

    public void addEventToCalender(String title,String description,String location,String time) {
        SharedPreferences sharedPref = context1.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        String savedOption = sharedPref.getString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_ALWAYS_ASK);
        if (savedOption.equals(Constants.CALENDAR_DIALOG_YES)) {
            createAddToCalendarIntent(title,description,location,time);
        } else if (savedOption.equals(Constants.CALENDAR_DIALOG_ALWAYS_ASK)) {
            showAddEventToCalendarDialog(title,description,location,time);
        }
    }

    public void showAddEventToCalendarDialog(final String title, final String description, final String location,final String time) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context1);
        LayoutInflater layoutInflater = LayoutInflater.from(context1);
        View layout = layoutInflater.inflate(R.layout.calendar_dialog_checkbox, null);
        dialogBuilder.setView(layout);
        final CheckBox dontShowAgain = layout.findViewById(R.id.skip);

        dialogBuilder.setTitle("Add to Calendar")
                .setMessage("You will be notified about this event by IIT(BHU) App. Do you also want to add this event to your calendar?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Adapter_CLubFeed.this.createAddToCalendarIntent(title,description,location,time);
                        Adapter_CLubFeed.this.saveCalendarDialogPreference(dontShowAgain.isChecked(), true);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Adapter_CLubFeed.this.saveCalendarDialogPreference(dontShowAgain.isChecked(), false);
                    }
                })
                .create()
                .show();
    }

    public void createAddToCalendarIntent(String title,String description,String location,String time) {

        DateFormat formatter = new SimpleDateFormat("E, dd MMM  hh:mm a");
        long lnsTime = 0, lneTime = 0;
        Date dateObject = null;
        try {
            dateObject = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lnsTime = dateObject.getTime();
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", lnsTime);

        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY,0);
        intent.putExtra("endTime", lnsTime+60*60*1000*2);
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location);
        intent.putExtra("eventTimezone", "UTC/GMT +5:30");

        context1.startActivity(intent);
    }

    public void saveCalendarDialogPreference(boolean dontAskAgain, boolean yes) {
        SharedPreferences sharedPref =context1.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (!dontAskAgain) {
            editor.putString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_ALWAYS_ASK);
            editor.commit();
        } else {
            if (yes) {
                editor.putString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_YES);
                editor.commit();
            } else {
                editor.putString(Constants.CALENDAR_DIALOG, Constants.CALENDAR_DIALOG_NO);
                editor.commit();
            }
        }
    }


}
