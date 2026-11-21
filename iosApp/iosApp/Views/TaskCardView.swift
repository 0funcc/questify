import SwiftUI
import SharedLogic

struct TaskCardView: View {
    let task: SharedLogic.Task
    let repository: QuestRepository

    @State private var isClicked: Bool

    init(
        task: SharedLogic.Task,
        repository: QuestRepository
    ) {
        self.task = task
        self.repository = repository
        _isClicked = State(initialValue: task.isCompleted)
    }

    var body: some View {
        VStack(alignment: .leading) {
            HStack(alignment: .top, spacing: 12) {
                Button {
                    let newValue = !isClicked

                    repository.setTaskCompleted(
                        id: task.id,
                        completed: newValue
                    )

                    withAnimation {
                        isClicked = newValue
                    }
                } label: {
                    Image(
                        systemName: isClicked
                            ? "checkmark.circle.fill"
                            : "circle.dotted"
                    )
                    .contentTransition(
                        .symbolEffect(
                            .replace.magic(fallback: .downUp.byLayer),
                            options: .nonRepeating
                        )
                    )
                }
                .buttonStyle(.plain)
                .accessibilityLabel(
                    isClicked ? "Mark incomplete" : "Mark complete"
                )

                VStack(alignment: .leading, spacing: 4) {
                    Text(task.title)
                        .font(.body)
                        .fontWeight(.medium)
                        .strikethrough(isClicked)
                        .foregroundStyle(
                            isClicked ? .secondary : .primary
                        )

                    if let description = task.description_ {
                        Text(description)
                            .font(.subheadline)
                            .foregroundStyle(.secondary)
                    }
                }

                Spacer(minLength: 0)
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .cardBackground()
    }
}

extension View {
    @ViewBuilder
    func cardBackground(cornerRadius: CGFloat = 24) -> some View {
        if #available(iOS 26.0, *) {
            self.glassEffect(
                .regular,
                in: .rect(cornerRadius: cornerRadius)
            )
        } else {
            self.background(
                .ultraThinMaterial,
                in: RoundedRectangle(cornerRadius: cornerRadius)
            )
        }
    }
}

#Preview {
    let driver = DatabaseDriver().create()
    let repository = QuestRepository(driver: driver)

    TaskCardView(
        task: SharedLogic.Task(
            id: "1",
            questID: "1",
            title: "Finish CS assignment",
            description: "Check the docs",
            isCompleted: false
        ),
        repository: repository
    )
    .padding()
}
