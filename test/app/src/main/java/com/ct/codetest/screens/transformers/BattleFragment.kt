package com.ct.codetest.screens.transformers

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ct.codetest.R
import com.ct.codetest.models.transformers.Transformer
import com.ct.codetest.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_battle.*

class BattleFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_battle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arguments?.getSerializable("transformers") as? ArrayList<Transformer>
        var descepticons = list?.filter { it.team == "D" }
        var autobots = list?.filter { it.team == "A" }

        descepticons = descepticons?.sortedByDescending { it.rank }
        autobots = autobots?.sortedByDescending { it.rank }

        var battles = 0
        var result: Pair<Transformer, Transformer>? = Pair(Transformer(), Transformer())
        while ((battles < (autobots?.size ?: 0) && (battles < (descepticons?.size ?: 0)))) {
            val autobot = autobots?.get(battles)
            val descepticon = descepticons?.get(battles)
            result = autobot?.let { descepticon?.let { it1 -> fight(it, it1) } }
            if (result != null) {
                resultsText.text =
                    "${resultsText.text}\nBattle #${battles+1} " +
                            "\nWinner: \"${result.first.name}\" " +
                            "\nTeam: ${result.first.getTeamFormatted()}" +
                            "\nLoser: \"${result.second.name}\"\n"
            } else {
                resultsText.text =
                    "${resultsText.text}\nBattle #$battles \nTransformers Destroyed \n\"${autobot?.name}\" and \"${descepticon?.name}\"\n"
            }
            battles++
        }

        winnerText.text =
            "${winnerText.text}\nWinner is \"${result?.first?.name}\"\nTeam: ${result?.first?.getTeamFormatted()}\n"
        autobots?.forEachIndexed { index, transformer ->
            if (index >= battles) winnerText.text =
                "${winnerText.text}Survivor: ${transformer.name}\n"
        }
        descepticons?.forEachIndexed { index, transformer ->
            if (index >= battles) winnerText.text =
                "${winnerText.text}Survivor: ${transformer.name}\n"
        }
    }

    private fun calculateOverall(transformer: Transformer): Int {
        return transformer.strength + transformer.intelligence + transformer.speed + transformer.endurance + transformer.firepower
    }

    private fun fight(
        autobot: Transformer,
        descepticon: Transformer
    ): Pair<Transformer, Transformer>? {
        return when {
            autobot.name == "Optimus Prime" && descepticon.name == "Predaking" -> null
            autobot.name == "Optimus Prime" && descepticon.name != "Predaking" -> Pair(
                autobot,
                descepticon
            )
            autobot.name != "Optimus Prime" && descepticon.name == "Predaking" -> Pair(
                descepticon,
                autobot
            )
            autobot.name == "Optimus Prime" && descepticon.name == "Optimus Prime" -> null
            autobot.name == "Predaking" && descepticon.name == "Predaking" -> null
            autobot.courage <= descepticon.courage - 4 && autobot.strength <= descepticon.strength - 3 -> Pair(
                descepticon,
                autobot
            )
            descepticon.courage <= autobot.courage - 4 && descepticon.strength <= autobot.strength - 3 -> Pair(
                autobot,
                descepticon
            )
            autobot.skill - 3 >= descepticon.skill -> Pair(autobot, descepticon)
            descepticon.skill - 3 >= autobot.skill -> Pair(descepticon, autobot)
            calculateOverall(autobot) < calculateOverall(descepticon) -> Pair(descepticon, autobot)
            calculateOverall(autobot) > calculateOverall(descepticon) -> Pair(autobot, descepticon)
            else -> null
        }
    }
}