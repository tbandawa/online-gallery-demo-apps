//
//  HomeView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/27.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {
    
    var body: some View {
        TabView {

            GalleriesView()
                .tabItem {
                    Image(systemName: "square.grid.2x2.fill")
                }
            
            CreateView()
                .tabItem {
                    Image(systemName: "camera.circle.fill")
                }
                
            
            ProfileView()
                .tabItem {
                    Image(systemName: "person.fill")
                }
            
        }
        .tint(Color("tabColor"))
    }
}

#Preview {
    HomeView()
}
