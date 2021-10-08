package com.example.studyapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studyapplication.databinding.FragmentFirstBinding

private const val ALARM_MESSAGE = "give me your money"
private const val UNIQUE_MY_ACTION = "ru.yauhen.action.Yauhen"

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "Received Intent: " + intent?.getStringExtra("ru.yauhen.broadcast.Message"), Toast.LENGTH_LONG).show()
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        //intent filter + start Broadcast Receiver
        val intentFilter = IntentFilter()
        intentFilter.addAction(UNIQUE_MY_ACTION)
        context?.registerReceiver(receiver, intentFilter)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.sendIntent.setOnClickListener{
            val intent = Intent()
            intent.action = UNIQUE_MY_ACTION
            intent.putExtra("ru.yauhen.broadcast.Message", ALARM_MESSAGE)
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            context?.sendBroadcast(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}