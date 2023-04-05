package com.herco.bmicalculatorkotlin.result

import com.herco.bmicalculatorkotlin.dto.BmiResultModel

class ResultPresenter(private val view: ResultViewContract.IResultView) :
    ResultViewContract.IResultPresenter {

    override fun loadData(bmiString: String) {
        view.setBmi(BmiResultModel.fromJson(bmiString))
    }
}