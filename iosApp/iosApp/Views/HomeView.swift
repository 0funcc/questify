import SwiftUI
import SharedLogic

struct HomeView: View {
    let demoTasks = [
        SharedLogic.Task(
            id: "1",
            title: "Finish CS assignment",
            description: "Complete MST129 questions",
            isCompleted: false
        ),
        SharedLogic.Task(
            id: "2",
            title: "Go for a run",
            description: "Run 5 km",
            isCompleted: true
        ),
        SharedLogic.Task(
            id: "3",
            title: "Study Swift",
            description: "Learn SwiftUI navigation",
            isCompleted: false
        )
    ]

    var body: some View {
        NavigationStack {
            ScrollView() {
                LazyVStack(alignment: .leading, spacing: 12) {
                    ForEach(demoTasks, id: \.id) { task in
                        TaskCardView(task: task)
                    }
                }
                .frame(maxWidth: .infinity)
                .padding()
            }
            .navigationTitle("Active Quests")
        }
    }
}

#Preview {
    HomeView()
}
