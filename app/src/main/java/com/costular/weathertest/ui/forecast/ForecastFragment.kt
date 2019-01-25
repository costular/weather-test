package com.costular.weathertest.ui.forecast

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.costular.weathertest.R
import com.costular.weathertest.domain.model.Response
import com.costular.weathertest.ui.MainActivity
import com.costular.weathertest.ui.WeatherApp
import com.costular.weathertest.ui.base.BaseFragment
import com.costular.weathertest.ui.util.ListMarginDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment : BaseFragment() {

    lateinit var viewModel: ForecastViewModel

    private val adapter: ForecastAdapter by lazy {
        ForecastAdapter()
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpViewModel()
        setUpRecycler()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        with (viewModel) {
            responseState.observe(this@ForecastFragment, Observer {
                onForecastLoaded(it!!)
            })

            isLoadingState.observe(this@ForecastFragment, Observer {
                showLoading(it!!)
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun onForecastLoaded(response: Response) {
        updateToolbarTitle(response.city.name)
        adapter.submitList(response.weatherForecasts)
    }

    private fun setUpRecycler() {
        with (recyclerForecast) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(ListMarginDecoration(resources))
            adapter = this@ForecastFragment.adapter
        }

    }

    private fun inject() {
        (activity?.application as WeatherApp).component.inject(this)
    }

    private fun updateToolbarTitle(title: String) {
        (activity as MainActivity).collapsingToolbar.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.loadWeatherByCityName(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })

        return super.onCreateOptionsMenu(menu, inflater)
    }
}