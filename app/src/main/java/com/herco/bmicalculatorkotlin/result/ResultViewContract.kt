package com.herco.bmicalculatorkotlin.result

import com.herco.bmicalculatorkotlin.dto.BmiResultModel

interface ResultViewContract {

    interface IResultView {
        fun setBmi(bmiModel: BmiResultModel)
    }

    interface IResultPresenter {
        fun loadData(bmiString: String)
    }
}