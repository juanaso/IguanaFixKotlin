package challenge.juanaso.com.iguanafixkotlin.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import challenge.juanaso.com.iguanafixkotlin.databinding.UserItemBinding
import challenge.juanaso.com.iguanafixkotlin.model.User
import challenge.juanaso.com.iguanafixkotlin.viewmodel.ItemViewModel
import challenge.juanaso.com.iguanafixkotlin.R
import java.util.ArrayList
import javax.inject.Singleton


class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {
    private lateinit var users:List<User>
    private lateinit var usersFull:List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding: UserItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return if(::users.isInitialized) users.size else 0
    }

    fun updateUsers(users:List<User>){
        this.users = users
        this.usersFull = users
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = ItemViewModel()

        fun bind(user: User){
            viewModel.bind(user)
            binding.viewModel = viewModel
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = ArrayList<User>()
                if (charSequence == null || charSequence.isEmpty()){
                    filteredList.addAll(usersFull)
                }else{
                    val filterPattern = charSequence.toString().trim { it <= ' ' }
                    for(item in usersFull){
                        if (item.fullName!!.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                users = filterResults!!.values as  MutableList<User>
                notifyDataSetChanged()
            }
        }
    }
}