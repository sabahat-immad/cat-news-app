package saba.qazi.catnews.newslist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import saba.qazi.catnews.R
import saba.qazi.catnews.newslist.data.News


class NewsListRecyclerViewAdapter(
    private val values: List<News>
) : RecyclerView.Adapter<NewsListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.newslist_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        if(item.type != "advert") {
            holder.storyHeadline.text = item.headline
            holder.dateModified.text = item.modifiedDate?.substring(0, 10)
            holder.storyDetail.text = item.teaserText
            holder.catImage.setImageResource(R.mipmap.cat_img)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val storyHeadline: TextView = view.findViewById(R.id.story_headline)
        val dateModified: TextView = view.findViewById(R.id.time)
        val storyDetail: TextView = view.findViewById(R.id.story_text)
        val catImage : ImageView = view.findViewById(R.id.cat_image)

    }
}