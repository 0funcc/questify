import SwiftUI
import SharedLogic

struct TaskCardView: View {
    let task: SharedLogic.Task
    @State private var isClicked: Bool
    
    init(task: SharedLogic.Task) {
        self.task = task
        _isClicked = State(initialValue: task.isCompleted)
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack(alignment: .top) {
                Button {
                    withAnimation { isClicked.toggle() }
                } label: {
                    Image(systemName: isClicked ? "checkmark.circle.fill" : "circle.dotted")
                        .contentTransition(.symbolEffect(.replace.magic(fallback: .downUp.byLayer),
                                                         options: .nonRepeating))
                }
                VStack(alignment: .leading) {
                    Text(task.title)
                        .font(.body)
                        .fontWeight(.medium)
                    if let description = task.description_ {
                                Text(description)
                            .font(.subheadline)
                    }
                }
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
            self.glassEffect(.regular, in: .rect(cornerRadius: cornerRadius))
        } else {
            self.background(.ultraThinMaterial,
                            in: RoundedRectangle(cornerRadius: cornerRadius))
        }
    }
}

#Preview {
    TaskCardView(
        task: SharedLogic.Task(
            id: "1",
            title: "Finish CS assignment",
            description: "Check the docs",
            isCompleted: false
        )
    )
}
