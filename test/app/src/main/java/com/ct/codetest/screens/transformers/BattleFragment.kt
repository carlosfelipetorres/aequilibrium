package com.ct.codetest.screens.transformers

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ct.codetest.R
import com.ct.codetest.models.transformers.Transformer
import com.ct.codetest.platform.BaseFragment

class BattleFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_battle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arguments?.getSerializable("transformers") as? ArrayList<Transformer>
        var descepticons = list?.filter { it.team == "D" }
        var autobots = list?.filter { it.team == "A" }

        descepticons = descepticons?.sortedByDescending { it.rank }
        autobots = autobots?.sortedByDescending { it.rank }

        var a = 0
        var d = 0
        var winner: Transformer? = Transformer()
        while ((a < (autobots?.size ?: 0) && (d < (descepticons?.size ?: 0)))) {
            val autobot = autobots?.get(a)
            val descepticon = descepticons?.get(d)
            winner = autobot?.let { descepticon?.let { it1 -> fight(it, it1) } }
            Log.e("-", "$winner")
            if (winner == autobot) d++ else a++
        }

        Log.e("-", "$a $d $winner")
    }

    private fun calculateOverall(transformer: Transformer): Int {
        return transformer.strength + transformer.intelligence + transformer.speed + transformer.endurance + transformer.firepower
    }

    private fun fight(autobot: Transformer, descepticon: Transformer): Transformer? {
        return when {
            autobot.name == "Optimus Prime" && descepticon.name == "Predaking" -> null
            autobot.name == "Optimus Prime" && descepticon.name != "Predaking" -> autobot
            autobot.name != "Optimus Prime" && descepticon.name == "Predaking" -> descepticon
            autobot.name == "Optimus Prime" && descepticon.name == "Optimus Prime" -> null
            autobot.name == "Predaking" && descepticon.name == "Predaking" -> null
            autobot.courage <= descepticon.courage - 4 && autobot.strength <= descepticon.strength - 3 -> descepticon
            descepticon.courage <= autobot.courage - 4 && descepticon.strength <= autobot.strength - 3 -> autobot
            autobot.skill - 3 >= descepticon.skill -> autobot
            descepticon.skill - 3 >= autobot.skill -> descepticon
            calculateOverall(autobot) < calculateOverall(descepticon) -> descepticon
            calculateOverall(autobot) > calculateOverall(descepticon) -> autobot
            else -> null
        }
    }
}