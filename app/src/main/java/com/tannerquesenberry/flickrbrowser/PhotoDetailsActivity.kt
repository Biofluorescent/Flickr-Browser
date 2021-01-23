package com.tannerquesenberry.flickrbrowser

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        activateToolbar(true)

        // Use appropriate getExtra function for the type of data being retrieved
        val photo = intent.getSerializableExtra(PHOTO_TRANSER) as Photo

        val photo_title = findViewById<TextView>(R.id.photo_title)
        val photo_author = findViewById<TextView>(R.id.photo_author)
        val photo_tags = findViewById<TextView>(R.id.photo_tags)

//        photo_title.text = photo.title
        photo_title.text = resources.getString(R.string.photo_title_text, photo.title)
        photo_author.text = photo.author
//        photo_author.text = resources.getString(R.string.photo_author_text, "my", "red", "car")
//        photo_tags.text = photo.tags
        photo_tags.text = resources.getString(R.string.photo_tags_text, photo.tags)

        val photo_image = findViewById<ImageView>(R.id.photo_image)
        // Picasso downloads the image in a background thread on its own
        Picasso.get().load(photo.link)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(photo_image)
    }
}