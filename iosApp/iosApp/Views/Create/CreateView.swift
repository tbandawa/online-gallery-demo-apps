//
//  CreateView.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/28.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct CreateView: View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Hello, Create!")
            }
            .toolbar {
                ToolbarItem(placement: .principal) {
                    Text("New Gallery")
                        .foregroundColor(Color("toolBarTitle"))
                        .font(.largeTitle.bold())
                        .accessibilityAddTraits(.isHeader)
                }
             }
        }
    }
}

#Preview {
    CreateView()
}
