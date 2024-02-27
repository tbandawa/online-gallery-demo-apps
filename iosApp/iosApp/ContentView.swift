import SwiftUI
import data

struct ContentView: View {
    
    @EnvironmentObject var authanticationState: AuthanticationState
    
    var body: some View {
        
        if let auth = authanticationState.authState {
            if auth.value == 1 {
                HomeView()
            }
            if auth.value == 2 {
                AuthView()
            }
        } else {
            AuthView()
        }
        
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        AuthView()
	}
}
