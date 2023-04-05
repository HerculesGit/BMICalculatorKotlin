package com.herco.bmicalculatorkotlin.home

import com.herco.bmicalculatorkotlin.dto.BmiResultModel
import com.herco.bmicalculatorkotlin.shared.enums.Gender

interface HomeViewContract {

    interface IHomeView {
        fun calculateSuccess(resultModel: BmiResultModel)
    }

    interface IHomePresenter {
        fun onGenderClicked(gender: Int = Gender.MALE.gender)

        fun onHeightChanged(height: String)

        fun onWeightChanged(weight: Int)

        fun onAgeChanged(age: Int)

        fun calculateBmi()
    }
}