//
//  AuthView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct AuthView: View {
    
    @State var currentTab: Int = 0
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor(Color("authBackground"))
    }
    
    var body: some View {
        
        VStack {
            VStack {
                TabBarView(currentTab: self.$currentTab)
                
                Spacer()
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 1)
                    .background(Color.black)
                            
                TabView(selection: self.$currentTab) {
                    LoginView().tag(0)
                    RegisterView().tag(1)
                }
                .background(Color("authBackground"))
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color("authBackground"))
            .cornerRadius(15)
            .shadow(color: Color.gray, radius: 4, x: 0.5, y: 1.5)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .padding(16)
        .background(Color.white)
        .cornerRadius(0)
        
    }
}

struct TabBarView: View {
    
    @Binding var currentTab: Int
    @Namespace var namespace
    
    var tabBarOptions: [String] = ["LOGIN", "REGISTER"]
    var body: some View {
        HStack(alignment: .center) {
            ForEach(Array(zip(self.tabBarOptions.indices, self.tabBarOptions)), id: \.0, content: {
                index, name in
                TabBarItem(
                    currentTab: self.$currentTab,
                    namespace: namespace.self,
                    tabBarItemName: name,
                    tab: index
                )
                
            })
            Spacer()
        }
        .frame(height: 45)
    }
}

struct TabBarItem: View {
    @Binding var currentTab: Int
    let namespace: Namespace.ID
    
    var tabBarItemName: String
    var tab: Int
    
    var body: some View {
        VStack {
            Spacer()
            if currentTab == tab {
                Text(tabBarItemName)
                    .foregroundColor(Color("authTabTextColorSelected"))
                    .font(
                        .system(size: 18)
                        .weight(.heavy)
                    )
                    .padding(16)
            } else {
                Text(tabBarItemName)
                    .foregroundColor(Color("authTabTextColor"))
                    .font(
                        .system(size: 18)
                        .weight(.medium)
                    )
                    .padding(16)
            }
        }
        .contentShape(Rectangle())
        .onTapGesture {
            self.currentTab = tab
        }
    }
}

#Preview {
    AuthView()
}
