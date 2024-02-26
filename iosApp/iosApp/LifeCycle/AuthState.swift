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

class AuthState: ObservableObject {
    
    @Published var isLoading: Bool = false
    @Published var user: User? = nil
    @Published var error: Error? = nil
    
    private var viewModel: AuthViewModel
    
    init() {
        viewModel = KotlinDependencies.shared.getAuthViewModel()
        viewModel.observeUserResource { result in
            switch result {
                case let user as ResourceStateSuccess<User>?:
                    self.isLoading = false
                    self.user = user?.data
                case let error as ResourceStateError<Error>?:
                    self.isLoading = false
                    self.error = error?.data
                default:
                    let _ = print()
            }
        }
    }
    
    func signInUser(username: String, password: String) {
        self.isLoading = true
        self.user = nil
        self.error = nil
        viewModel.signInUser(username: username, password: password)
    }
    
    func signUpUser(firstname: String, lastname: String, username: String, email :String, password: String) {
        self.isLoading = true
        self.user = nil
        self.error = nil
        viewModel.signUpUser(firstname: firstname, lastname: lastname, username: username, email: email, password: password)
    }
    
    func resetState() {
        viewModel.resetState()
    }
    
    deinit {
        viewModel.dispose()
    }
}
