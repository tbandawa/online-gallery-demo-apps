//
//  ProfileView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/28.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    
    @State private var showGalleries = true
    
    var body: some View {
        NavigationView {
            VStack {
                
                AsyncImage(url: URL(string: "http://localhost:8075/online-gallery-service/photos/1/image.jpg")) { image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .frame(width: 150, height: 150, alignment: .center)
                .clipShape(RoundedRectangle(cornerRadius: 75))
                
                Spacer()
                    .frame(maxWidth: .infinity, maxHeight: 30)
                Text("First Last")
                    .foregroundColor(Color("authTextColor"))
                    .font(
                        .system(size: 24)
                        .weight(.heavy)
                    )
                    .frame(maxWidth: .infinity)
                
                Spacer()
                    .frame(maxWidth: .infinity, maxHeight: 5)
                Text("user@email.com")
                    .foregroundColor(Color("authTextColor"))
                    .font(
                        .system(size: 20)
                        .weight(.medium)
                    )
                    .frame(maxWidth: .infinity)
                
                Spacer()
                    .frame(maxWidth: .infinity, maxHeight: 10)
                HStack(spacing: 0) {
                    
                    HStack {
                        Text("1")
                            .font(
                                .system(size: 18)
                                .weight(.heavy)
                            )
                        Text("Posts")
                            .font(
                                .system(size: 18)
                            )
                    }
                    .padding()
                    
                    Button(action: {
                        
                    }) {
                        HStack {
                            Image(systemName: "pencil")
                                .font(.system(size: 18))
                            Text("Edit")
                                .font(
                                    .system(size: 18)
                                )
                        }
                        .padding()
                        .foregroundColor(.white)
                        .background(Color.blue)
                        .cornerRadius(0)
                    }
                    Button(action: {
                        
                    }) {
                        HStack {
                            Image(systemName: "rectangle.portrait.and.arrow.forward")
                                .font(.system(size: 18))
                            Text("Logout")
                                .font(
                                    .system(size: 18)
                                )
                        }
                        .padding()
                        .foregroundColor(.white)
                        .background(Color.red)
                        .cornerRadius(0)
                    }
                }
                .frame(height: 40)
                .background(Color("profileControl"))
                .cornerRadius(20)
                
                
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .padding(16)
            .background(Color.white)
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text("Profile")
                        .foregroundColor(Color("toolBarTitle"))
                        .font(.largeTitle.bold())
                        .accessibilityAddTraits(.isHeader)
                }
            }
        }
    }
}

#Preview {
    ProfileView()
}
