package movieguideapplication;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter{


    private Video[] videos;
    private Context context;

    public RecyclerViewAdapter(Context context, Video[] videos){
        this.context = context; this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video, parent, false);
        RecyclerView.ViewHolder holder = new VideoTypeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VideoTypeViewHolder) holder).videoView.setVideoURI(Uri.parse(videos[position].getURL()));
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(((VideoTypeViewHolder) holder).videoView);
        ((VideoTypeViewHolder) holder).videoView.setMediaController(mediaController);
        ((VideoTypeViewHolder) holder).videoView.seekTo(100);

        ((VideoTypeViewHolder) holder).parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoView videoView = (VideoView) view.findViewById(R.id.video_view);
                videoView.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.length;
    }

        public class VideoTypeViewHolder extends RecyclerView.ViewHolder{

            VideoView videoView;
            RelativeLayout parentLayout;

            VideoTypeViewHolder(View itemView){
            super(itemView);
            videoView = (VideoView) itemView.findViewById(R.id.video_view);
            parentLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
        }
    }
}
