import SwiftUI
import data

@main
struct iOSApp: App {
    
    init() {
        DataModuleKt.doInitKoin()
    }
    
    @StateObject var authanticationState = AuthanticationState()
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .environmentObject(authanticationState)
		}
	}
}
