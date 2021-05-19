package academy.bangkit.lanting.ui.profiles

import academy.bangkit.lanting.data.model.Profile

interface OnItemProfileClicked {
    fun setOnItemEditClicked(profile: Profile)
    fun setOnItemClicked(profile: Profile)
}