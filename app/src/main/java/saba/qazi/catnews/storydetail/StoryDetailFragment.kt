package saba.qazi.catnews.storydetail

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_story_detail.*
import saba.qazi.catnews.R
import saba.qazi.catnews.storydetail.data.StoryDetail
import javax.inject.Inject

@AndroidEntryPoint
class StoryDetailFragment : Fragment() {

   private val args : StoryDetailFragmentArgs by navArgs()

    lateinit var viewModel : StoryDetailViewModel

    @Inject
    lateinit var viewModelFactory : StoryDetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_story_detail, container, false)
        val id = args.storyId

        setupViewModel()

        viewModel.getStoryDetails(id)

        observeStoryDetails()

        return view
    }




    companion object {

        @JvmStatic
        fun newInstance() =
            StoryDetailFragment()
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this, viewModelFactory).get(StoryDetailViewModel::class.java)
    }

    private fun observeStoryDetails() {
        viewModel.storyDetails.observe(this as LifecycleOwner) { storyDetails ->
            if (storyDetails.getOrNull() != null) {
                setupUI(storyDetails.getOrNull()!!)
            } else {
                showErrorToast()
            }
        }
    }



    private fun setupUI(storyDetail: StoryDetail) {
        story_headline_text.text = storyDetail.headline
        Glide
            .with(this)
            .load(storyDetail.heroImage.imageUrl)
            .placeholder(R.mipmap.cat_banner)
            .into(story_banner_image)

        for(content in storyDetail.contents){
            if(content.type == "paragraph"){
                // Create TextView programmatically.
                val textView = TextView(activity)
                textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                textView.gravity = Gravity.LEFT
                textView.text = content.text
                story_detail_items_container.addView(textView)
            }
            else if(content.type == "image"){
                val imageView = ImageView(activity)
                imageView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400)
                imageView.scaleType = ImageView.ScaleType.FIT_XY

                Glide
                    .with(this)
                    .load(content.url)
                    .placeholder(R.mipmap.cat_img)
                    .into(imageView)

                story_detail_items_container.addView(imageView)

            }
        }


    }

    private fun showErrorToast() {
        Toast.makeText(
            activity, "Oops, Something wrong...please check your internet" +
                    " connection and try again", Toast.LENGTH_SHORT
        ).show()
    }


}