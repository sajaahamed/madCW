package com.example.madcw.Componnent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madcw.ViewModel.DegreeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DegreesScreen(viewModel: DegreeViewModel = viewModel()) {

    val degreeState = viewModel.degrees.collectAsState(initial = emptyList())
    val degrees = degreeState.value

    LaunchedEffect(Unit) {
        viewModel.fetchDegrees()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Degrees") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(degrees) { degree ->

                // Degree card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.toggleDegreeExpanded(degree.id)
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = degree.name,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // Subjects dropdown
                if (degree.isExpanded) {
                    degree.subjects.forEach { subject ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = "• ${subject.name}",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
