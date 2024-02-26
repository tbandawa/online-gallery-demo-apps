import SwiftUI
import data

@main
struct iOSApp: App {
    
    init() {
        DataModuleKt.doInitKoin()
    }
    
    @StateObject var authState = AuthState()
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .environmentObject(authState)
		}
	}
}
