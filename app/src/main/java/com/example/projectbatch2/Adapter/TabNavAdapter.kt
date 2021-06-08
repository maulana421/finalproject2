package com.example.projectbatch2.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.projectbatch2.LoginFragment
import com.example.projectbatch2.SignupFragment

class TabNavAdapter(Sup : FragmentManager): FragmentPagerAdapter(Sup,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> SignupFragment()
            else -> LoginFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Register"
            else -> "Login"
        }
    }

}
