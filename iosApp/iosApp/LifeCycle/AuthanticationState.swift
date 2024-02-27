//
//  AuthState.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/25.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit
import data

class AuthanticationState: ObservableObject {
    
    @Published var isLoading: Bool = false
    @Published var authState: AuthState? = nil
    @Published var error: Error? = nil
    
    private var authViewModel: AuthViewModel
    private var splashViewModel: SplashViewModel
    
    init() {
        authViewModel = KotlinDependencies.shared.getAuthViewModel()
        splashViewModel = KotlinDependencies.shared.getSplashViewModel()
        authViewModel.observeUserResource { result in
            switch result {
                case let user as ResourceStateSuccess<User>?:
                    self.isLoading = false
                    self.authState = AuthState(value: 1, complete: true)
                    self.error = nil
                case let error as ResourceStateError<Error>?:
                    self.isLoading = false
                    self.error = error?.data
                default:
                    let _ = print()
            }
        }
        splashViewModel.observeAuthState { result in
            self.authState = result
        }
        splashViewModel.getProfile()
    }
    
    func signInUser(username: String, password: String) {
        self.isLoading = true
        self.error = nil
        authViewModel.signInUser(username: username, password: password)
    }
    
    func signUpUser(firstname: String, lastname: String, username: String, email :String, password: String) {
        self.isLoading = true
        self.error = nil
        authViewModel.signUpUser(firstname: firstname, lastname: lastname, username: username, email: email, password: password)
    }
    
    func resetState() {
        self.isLoading = false
        self.error = nil
        authViewModel.resetState()
    }
    
    deinit {
        authViewModel.dispose()
    }
}
