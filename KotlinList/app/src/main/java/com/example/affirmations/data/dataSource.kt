package com.example.affirmations.data

import com.example.affirmations.model.affirmationModel
import com.example.kotlinlist.R

class dataSource {
    fun loadAffirmations() : List<affirmationModel>{
        return listOf<affirmationModel>(affirmationModel(R.string.affirmation1, R.drawable.image1),
            affirmationModel(R.string.affirmation2, R.drawable.image2),
            affirmationModel(R.string.affirmation3, R.drawable.image3),
            affirmationModel(R.string.affirmation4, R.drawable.image4),
            affirmationModel(R.string.affirmation5, R.drawable.image5),
        )
    }
}