import SwiftUI
import SharedLogic

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        VStack {
            TabView {
                Tab("Home", systemImage: "house.fill") {
                    HomeView()
                }
                
                Tab("Settings", systemImage: "gearshape.fill") {
                    SettingsView()
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
