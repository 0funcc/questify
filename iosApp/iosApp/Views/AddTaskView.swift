import SwiftUI
import SharedLogic

struct AddTaskView: View {
    @Environment(\.dismiss) private var dismiss
    
    @State private var title = ""
    @State private var description = ""
    
    private let repository = QuestRepository(
        driver: DatabaseDriver().create()
    )
    
    var body: some View {
        NavigationStack {
            Form {
                Section("Task Details") {
                    TextField("Task title", text: $title)
                    TextField(
                        "Description (optional)",
                        text: $description,
                        axis: .vertical
                    )
                    .lineLimit(3...5)
                }
                
                Section {
                    Button("Add Task") {
                        addTask()
                    }
                        .disabled(title.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty)
                    }
            }
            .navigationTitle("Add Task")
                        .navigationBarTitleDisplayMode(.inline)
                        .toolbar {
                            ToolbarItem(placement: .cancellationAction) {
                                Button("Cancel", systemImage: "xmark") {
                                    dismiss()
                                }
                            }
                        }
        }
    }
    
    private func addTask() {
        let cleanedTitle = title.trimmingCharacters(
            in: .whitespacesAndNewlines
        )

        let cleanedDescription = description.trimmingCharacters(
            in: .whitespacesAndNewlines
        )

        let task = repository.addTask(
            title: cleanedTitle,
            questID: nil,
            description: cleanedDescription.isEmpty ? nil : cleanedDescription
        )

        print("Created task: \(task.title), ID: \(task.id)")

        dismiss()
    }
}

#Preview {
    AddTaskView()
}
