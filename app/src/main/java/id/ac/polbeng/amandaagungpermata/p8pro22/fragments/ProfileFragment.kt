package id.ac.polbeng.amandaagungpermata.p8pro22.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.ac.polbeng.amandaagungpermata.p8pro22.R
import id.ac.polbeng.amandaagungpermata.p8pro22.activities.EditProfile
import id.ac.polbeng.amandaagungpermata.p8pro22.activities.Login
import id.ac.polbeng.amandaagungpermata.p8pro22.helpers.SessionHandler
import id.ac.polbeng.amandaagungpermata.p8pro22.models.DefaultResponse
import id.ac.polbeng.amandaagungpermata.p8pro22.models.User
import id.ac.polbeng.amandaagungpermata.p8pro22.services.ServiceBuilder
import id.ac.polbeng.amandaagungpermata.p8pro22.services.UserServices
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ProfileFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ProfileFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val session = SessionHandler(requireContext())
            val user: User? = session.getUser()
            val titikDua = ": "

            if(user != null) {
                view.tvNama.text = titikDua + user.nama
                view.tvTanggalLahir.text = titikDua + user.tanggalLahir
                view.tvJenisKelamin.text = titikDua + user.jenisKelamin
                view.tvNomorHP.text = titikDua + user.nomorHP
                view.tvAlamat.text = titikDua + user.alamat
                view.tvEmail.text = titikDua + user.email
                view.tvWaktuSesi.text = titikDua + session.getExpireTime()
            }
            view.btnEditProfil.setOnClickListener {
                val intent = Intent(context, EditProfile::class.java)
                startActivity(intent)
            }
            view.btnHapusUser.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Hapus Akun")
                builder.setMessage("Apakah anda yakin menghapus akun?")
                builder.setIcon(R.drawable.ic_baseline_delete_forever_24)
                builder.setPositiveButton("Yes"){dialog, _ ->
                    val userService: UserServices = ServiceBuilder.buildService(UserServices::class.java)
                    val requestCall: Call<DefaultResponse> = userService.deleteUser(user?.id!!)
                    requestCall.enqueue(object: retrofit2.Callback<DefaultResponse>{
                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(context, "Error terjadi ketika sedang menghapus user: " + t.toString(),
                                Toast.LENGTH_LONG).show()
                        }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if(!response.body()?.error!!) {
                        val defaultResponse: DefaultResponse = response.body()!!
                        defaultResponse.let {
                            session.removeUser()
                            Toast.makeText(context, defaultResponse.message, Toast.LENGTH_LONG).show()
                            val intent = Intent(context,
                    Login::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }else{
                        Toast.makeText(context, "Gagal menghapus user: " + response.body()?.message,
                            Toast.LENGTH_LONG).show()
                    }
                }
                    })
                    dialog.dismiss()
                }

                builder.setNegativeButton("No"){dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }

        }
}