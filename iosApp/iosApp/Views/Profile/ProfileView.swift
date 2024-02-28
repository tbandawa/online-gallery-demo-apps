//
//  ProfileView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/28.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileView: View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Hello, Profile!")
            }
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text("Profile")
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
