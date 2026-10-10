import SharedLogic
import SwiftUI

struct HomeView: View {
    @State private var isShowingAddTask = false
    @State private var tasks: [SharedLogic.Task] = []

    private let repository = QuestRepository(
        driver: DatabaseDriver().create()
    )

    var body: some View {
        NavigationStack {
            ScrollView {
                LazyVStack(alignment: .leading, spacing: 12) {
                    if tasks.isEmpty {
                            ContentUnavailableView(
                                "No Active Tasks",
                                systemImage: "checkmark.circle",
                                description: Text("Add a task to get started.")
                            )
                            .frame(maxWidth: .infinity)
                            .padding(.top, 80)
                        } else {
                            ForEach(tasks, id: \.id) { task in
                                TaskCardView(
                                    task: task,
                                    repository: repository
                                )
                                .swipeActions(edge: .trailing, allowsFullSwipe: false) {
                                    Button(role: .destructive) {
                                        repository.deleteTask(id: task.id)
                                        loadTasks()
                                    } label: {
                                        Label("Delete", systemImage: "trash")
                                    }
                                }
                            }
                        }
                }
                .frame(maxWidth: .infinity)
                .padding()
            }
            .navigationTitle("Active Tasks")
            .toolbar {
                ToolbarItem(placement: .topBarTrailing) {
                    Button {
                        isShowingAddTask = true
                    } label: {
                        Image(systemName: "plus")
                    }
                    .accessibilityLabel("Add Task")
                }
            }
            .sheet(isPresented: $isShowingAddTask) {
                AddTaskView()
            }
            .onAppear {
                loadTasks()
            }
            .onChange(of: isShowingAddTask) { _, isShowing in
                if !isShowing {
                    loadTasks()
                }
            }
        }
    }

    private func loadTasks() {
        tasks = repository.getTasks()
    }
}

#Preview {
    HomeView()
}
