package com.skipissue.maxway.presentation.adapter

object Suggest {
    val list = ArrayList<SuggestBasket>()

    fun getSuggest(): ArrayList<SuggestBasket> {
        if (list.size < 1) {

            list.add(
                SuggestBasket(
                    1,
                    "Tiramisu",
                    "Тирамису",
                    "Tiramusu",
                    "Savoyardi pechene, mascarpone, qahva\n",
                    "Печенье \"Савоярди\", маскарпоне, кофе\n",
                    "Savoyardi cookies, mascarpone, coffee",
                    "20000",
                    "dd3eb9ef-b9e6-48ac-824a-66cfd0f39913",
                    1
                )
            )
            list.add(
                SuggestBasket(
                    1,
                    "Brauni",
                    "Брауни",
                    "Brauni",
                    "Qora shokolad, yong'oq va shokoladli pechene\\n",
                    "Темный шоколад, грецкие орехи и шоколадный бисквит\\n",
                    "Dark chocolate, walnuts and chocolate sponge cake",
                    "20000",
                    "ebf7f8ee-0181-4294-9be8-f9241e3cff08",
                    1
                )
            )
            list.add(
                SuggestBasket(
                    1,
                    "San-sebastian",
                    "Сан-себастьян",
                    "Tiramusu",
                    "  Savoyardi pechene, mascarpone, qahva\n",
                    "Печенье \"Савоярди\", маскарпоне, кофе\n",
                    "  Savoyardi cookies, mascarpone, coffee",
                    "20000",
                    "1ae33c55-cbd8-4d0a-b9a6-6a7624beca32",
                    1
                )
            )


        }

        return list

    }
}