package com.example.ryanhsueh.uiplayground.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryanhsueh.uiplayground.NewsContentActivity;
import com.example.ryanhsueh.uiplayground.R;
import com.example.ryanhsueh.uiplayground.Util;
import com.example.ryanhsueh.uiplayground.data.News;

import java.util.List;

public class NewsListFragment extends Fragment {

    private boolean mIsTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news_list,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().findViewById(R.id.layout_news_content) != null) {
            mIsTwoPane = true;
        } else {
            mIsTwoPane = false;
        }


        initRecyclerView();
    }

    private void initRecyclerView() {
        List<News> newsList = Util.createNews();
        NewsAdapter adapter = new NewsAdapter(newsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        RecyclerView recyclerView = getView().findViewById(R.id.recycler_news_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {

            View layoutNewsItem;
            TextView textTitle;
            TextView textContent;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                layoutNewsItem = itemView.findViewById(R.id.layout_news_item);
                textTitle = itemView.findViewById(R.id.text_news_title);
                textContent = itemView.findViewById(R.id.text_news_content);
            }
        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
            final ViewHolder holder = new ViewHolder(view);

            holder.layoutNewsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    News news = mNewsList.get(position);

                    if (mIsTwoPane) {
                        NewsContentFragment fragment =
                                (NewsContentFragment)getFragmentManager().findFragmentById(R.id.fragment_news_content);
                        fragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewsContentActivity.actionStart(getActivity(), news);
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            News news = mNewsList.get(position);
            viewHolder.textTitle.setText(news.getTitle());
            viewHolder.textContent.setText(news.getContent());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

    }

}
