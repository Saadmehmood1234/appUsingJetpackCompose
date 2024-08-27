package com.example.assignmentapp.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.assignmentapp.R
import com.example.assignmentapp.components.CountCard
import com.example.assignmentapp.components.SubjectCard
import com.example.assignmentapp.components.taskList
import com.example.assignmentapp.domain.model.Subject
import com.example.assignmentapp.domain.model.Task

@Composable
fun DashboardScreen(){
    val subjects= listOf(
        Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColors[0]),
        Subject(name = "Hindi", goalHours = 10f, colors = Subject.subjectCardColors[1]),
        Subject(name = "Mathematics", goalHours = 10f, colors = Subject.subjectCardColors[2]),
        Subject(name = "Geography", goalHours = 10f, colors = Subject.subjectCardColors[3]),
        Subject(name = "Urdu", goalHours = 10f, colors = Subject.subjectCardColors[4]),

        )
    val tasks= listOf(
        Task(
            title = "Prepare notes",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false
        ),
        Task(
            title = "Do Home Work",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            title = "Go TO GYM",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false
        ),
        Task(
            title = "Assignment",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            title = "Write Poems",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = true
        ),
    )
  Scaffold(
      topBar ={DashboardScreenTopBar()}
  ) {
paddingValues ->
      LazyColumn(
          modifier = Modifier
              .fillMaxSize()
              .padding(paddingValues)
      ) {
item {
    CountCardSection(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        subjectCount = 5,
        studiedHours = "10",
        goalHours = "15"
    )
}
          item {
              SubjectCardSection(
                  modifier = Modifier,
                  subjectList = subjects
              )
          }
          item {
              Button(
                  onClick = { /*TODO*/ },
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = 48.dp, vertical = 20.dp)
              )
              {
                  Text(text = "Start Study Session")
              }
          }
          taskList(
              sectionTitle = "UPCOMING TASKS",
              emptyListText = "You dont have any upcoming tasks.\n"+
              "Click the + button in subject screen to add new task.",
              tasks = tasks
          )
      }
  }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text ="StudySmart",
                style = MaterialTheme.typography.headlineMedium

            )
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount:Int,
    studiedHours:String,
    goalHours:String
){
    Row (modifier=modifier){
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count ="$subjectCount"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText ="Studied Hours" ,
            count = studiedHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText ="Goal Study Hours" ,
            count = goalHours
        )
    }
}


@Composable
private  fun SubjectCardSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptyListText:String="You don't have any subjects.\nClick the + button to add new subject."
){
    Column(modifier=modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text ="SUBJECT",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }

        }
        if(subjectList.isEmpty()){
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.img_books),
                contentDescription =emptyListText
            )
            Text(
                modifier = Modifier.fillMaxSize(),
                text =emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
         items(subjectList){
             subject->
             SubjectCard(
                 subjectName =subject.name ,
                 gradientColors = subject.colors,
                 onClick = {}

             )


         }
        }
    }
}