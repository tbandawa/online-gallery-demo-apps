//
//  LoginView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoginView: View {
    
    @EnvironmentObject var authState: AuthState
    
    @State var username: String = ""
    @State var password: String = ""
    @State var isShowingPassword: Bool = false
    @State var isLoading: Bool = false
    @FocusState var isFieldFocus: FieldToFocus?
    
    enum FieldToFocus {
        case secureField, textField
    }
    
    var body: some View {
        VStack {
            
            Text("Log in to your account")
                .foregroundColor(Color("authTextColor"))
                .font(
                    .system(size: 18)
                    .weight(.heavy)
                )
                .frame(maxWidth: .infinity, alignment: .topLeading)
            
            Spacer()
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 25)
            TextField("Username", text: $username)
                .padding(10)
                .frame(height: 45)
                .background(Color("textfieldColor"))
                .cornerRadius(4)
                .overlay(
                    RoundedRectangle(cornerRadius: 4)
                        .stroke(Color("textfieldBorderColor"), lineWidth: 0.5)
                )
            
            Spacer()
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 25)
            HStack {
                Group {
                    if isShowingPassword {
                        TextField("Password", text: $password)
                            .focused($isFieldFocus, equals: .textField)
                            .background(Color("textfieldColor"))
                    }else {
                        SecureField("Password", text: $password)
                            .focused($isFieldFocus, equals: .secureField)
                            .background(Color("textfieldColor"))
                    }
                }
                .disableAutocorrection(true)
                .autocapitalization(.none)
                .padding()
                
                Button {
                    isShowingPassword.toggle()
                } label: {
                    if isShowingPassword {
                        Image("visibility-on")
                            .resizable()
                            .scaledToFit()
                            .foregroundColor(Color("iconColor"))
                            .frame(width: 25, height: 25)
                            .padding(.trailing, 5)
                    }else {
                        Image("visibility-off")
                            .resizable()
                            .scaledToFit()
                            .foregroundColor(Color("iconColor"))
                            .frame(width: 25, height: 25)
                            .padding(.trailing, 5)
                    }
                }
            }
            .onChange(of: isShowingPassword) { result in
                isFieldFocus = isShowingPassword ? .textField : .secureField
            }
            .frame(height: 45)
            .background(Color("textfieldColor"))
            .cornerRadius(4)
            .overlay(
                RoundedRectangle(cornerRadius: 4)
                    .stroke(Color("textfieldBorderColor"), lineWidth: 0.5)
            )
            
            Spacer()
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 25)
            Button {
                authState.signInUser(
                    username: username,
                    password: password
                )
            } label: {
                if ProcessInfo.isPreview {
                    Text("Login")
                        .frame(maxWidth: .infinity)
                } else {
                    if authState.isLoading {
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle(tint: .white))
                            .frame(maxWidth: .infinity)
                    } else {
                        Text("Login")
                            .frame(maxWidth: .infinity)
                    }
                }
            }
            .buttonStyle(.borderedProminent)
            .buttonBorderShape(.roundedRectangle(radius: 4))
            .tint(Color("buttonColor"))
                            
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .padding(16)
        .background(Color("authBackground"))
        .cornerRadius(0)
        
    }
}

#Preview {
    LoginView()
}
